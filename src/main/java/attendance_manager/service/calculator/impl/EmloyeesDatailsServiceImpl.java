package attendance_manager.service.calculator.impl;

import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.domain.TimeOffType;
import attendance_manager.domain.User;
import attendance_manager.service.calculator.EmployeeDetailsDTO;
import attendance_manager.repository.IndividualTimeOffRepository;
import attendance_manager.repository.TimeOffTypeRepository;
import attendance_manager.repository.UserRepository;
import attendance_manager.service.calculator.EmployeesDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@Transactional(readOnly = true)
@Slf4j
public class EmloyeesDatailsServiceImpl implements EmployeesDetailsService {

    @Autowired
    TimeOffTypeRepository timeOffTypeRepository;

    @Autowired
    IndividualTimeOffRepository individualTimeOffRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public EmployeeDetailsDTO calculateWhenTimeOffsDisposedFromOutdatedVacation(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        EmployeeDetailsDTO employeeDetailsDTO = calculateIndependentData(
                employeeId,
                employeeName,
                joined,
                leaved,
                timeOffs,
                vacationPerMonth,
                validVacationPeriod);

        calculateDisposedFrmOutdatedVacation(employeeDetailsDTO, joined, leaved, vacationPerMonth, validVacationPeriod);
        return employeeDetailsDTO;
    }

    @Override
    public EmployeeDetailsDTO calculateWhenTimeOffsDisposedFromIndateVacation(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        EmployeeDetailsDTO employeeDetailsDTO = calculateIndependentData(
                employeeId,
                employeeName,
                joined,
                leaved,
                timeOffs,
                vacationPerMonth,
                validVacationPeriod);

        calculateDisposedFrmIndatedVacation(employeeDetailsDTO, joined, leaved, vacationPerMonth, validVacationPeriod);
        return employeeDetailsDTO;
    }

    @Override
    public EmployeeDetailsDTO calculateWhenTimeOffsDisposedBalanced(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        return null;
    }

    private EmployeeDetailsDTO calculateIndependentData(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        Map<TimeOffType, Long> overallTimeOff = summarizeTimeOff(timeOffs);
        Map<TimeOffType, Long> overallDisposedTimeOff = summarizeDisposedTimeOff(timeOffs);
        Double overAllDisposableVacationTaken = calculateOverAllDisposableVacationTaken(overallTimeOff);
        Double overallVacationFromOutdated = calculateOverallVacationFromOutdated(joined, leaved, vacationPerMonth, validVacationPeriod);
        Double overallVacationFromIndate = calculateOverallVacationFromIndate(joined, leaved, vacationPerMonth, validVacationPeriod);
        Double overallVacationGranted = calculateOverallVacationGranted(joined, leaved, vacationPerMonth, validVacationPeriod);
        List<IndividualTimeOff> approvedIndividualTimeOffs = excludeNotApprovedTimeOffs(timeOffs);
        Map<TimeOffType, Long> summaryTimeOffs = summaryTimeOffs(employeeDetailsDTO);

        employeeDetailsDTO.setEmployeeId(employeeId);
        employeeDetailsDTO.setEmployeeName(employeeName);
        employeeDetailsDTO.setJoinDate(joined);
        employeeDetailsDTO.setLeaveDate(leaved);
        employeeDetailsDTO.setOverallTimeOff(overallTimeOff);
        employeeDetailsDTO.setOverallDisposedTimeOff(overallDisposedTimeOff);
        employeeDetailsDTO.setOverallVacationFromOutdated(overallVacationFromOutdated);
        employeeDetailsDTO.setOverallVacationFromIndate(overallVacationFromIndate);
        employeeDetailsDTO.setOverallVacationGranted(overallVacationGranted);
        employeeDetailsDTO.setOverallDisposableVacationTaken(overAllDisposableVacationTaken);
        employeeDetailsDTO.setOverallApprovedTimeOffs(approvedIndividualTimeOffs);
        employeeDetailsDTO.setTimeOffSummary(summaryTimeOffs);
        return employeeDetailsDTO;
    }

    private Map<TimeOffType, Long> summaryTimeOffs(EmployeeDetailsDTO employeeDetailsDTO) {

        User employee = userRepository.findOne(employeeDetailsDTO.getEmployeeId());
        Map<TimeOffType, Long> summary = new HashMap<>();
        final long[] temp = {0L};

        List<TimeOffType> timeOffTypes = timeOffTypeRepository.findAll();
        if (timeOffTypes == null) return null;
        timeOffTypes.forEach(timeOffType -> Optional.ofNullable(individualTimeOffRepository.findAllByApprovedTrueAndReasonAndUser(timeOffType, employee))
                .ifPresent(individualTimeOffs -> individualTimeOffs
                        .forEach(individualTimeOff -> {
                            temp[0] += calculateDays(individualTimeOff.getStart(), individualTimeOff.getEnd());
                            summary.put(timeOffType, temp[0]);
                        })));

        return summary;
    }

    private List<IndividualTimeOff> excludeNotApprovedTimeOffs(List<IndividualTimeOff> individualTimeOffs) {
        Assert.notNull(individualTimeOffs);
        ListIterator iterator = individualTimeOffs.listIterator();
        while (iterator.hasNext()) {
            IndividualTimeOff individualTimeOff = (IndividualTimeOff) iterator.next();
            if (!individualTimeOff.getApproved()) {
                iterator.remove();
            }
        }
        return individualTimeOffs;
    }

    private Map<TimeOffType, Long> summarizeTimeOff(List<IndividualTimeOff> timeOffs) {
        Map<TimeOffType, Long> summarizedTimeOffs = new HashMap<>();
        timeOffs.forEach(individualTimeOff -> {
            if (!individualTimeOff.getApproved()) return;
            summarizedTimeOffs.put(individualTimeOff.getReason(),
                    ChronoUnit.DAYS.between(individualTimeOff.getStart(), individualTimeOff.getEnd()));

        });
        return summarizedTimeOffs;
    }

    private Map<TimeOffType, Long> summarizeDisposedTimeOff(List<IndividualTimeOff> timeOffs) {
        Map<TimeOffType, Long> summarizedTimeOffs = new HashMap<>();
        timeOffs.stream()
                .filter(IndividualTimeOff::getDisposed)
                .filter(IndividualTimeOff::getApproved)
                .forEach(individualTimeOff -> {
                    if (!individualTimeOff.getApproved()) return;
                    summarizedTimeOffs.put(individualTimeOff.getReason(),
                            ChronoUnit.DAYS.between(individualTimeOff.getStart(), individualTimeOff.getEnd()));
                });
        return summarizedTimeOffs;
    }

    private Double calculateOverAllDisposableVacationTaken(Map<TimeOffType, Long> timeOffs) {
        Long overAllDisposableVacationTaken = 0L;

        for (Map.Entry pair : timeOffs.entrySet()) {
            if (((TimeOffType) pair.getKey()).getDisposableFromVacation())
                overAllDisposableVacationTaken += (Long) pair.getValue();
        }
        return Double.parseDouble(overAllDisposableVacationTaken.toString());
    }

    private Double calculateOverallVacationFromOutdated(LocalDate startDate,
            LocalDate endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        if (endDate == null) endDate = LocalDate.now();
        Long workDurationMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        if (workDurationMonths <= validVacationPeriod) return 0D;
        Double workDurationFromOutDatedMonths = workDurationMonths - validVacationPeriod;
        return workDurationFromOutDatedMonths * vacationPerMonth;
    }

    private Double calculateOverallVacationFromIndate(LocalDate startDate,
            LocalDate endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        if (endDate == null) endDate = LocalDate.now();
        Long workDurationMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        if (workDurationMonths > validVacationPeriod) {
            return validVacationPeriod * vacationPerMonth;
        } else {
            return workDurationMonths * vacationPerMonth;
        }
    }

    private Double calculateOverallVacationGranted(LocalDate startDate,
            LocalDate endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        if (endDate == null) endDate = LocalDate.now();
        Long workDurationMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        return workDurationMonths * vacationPerMonth;
    }

    private EmployeeDetailsDTO calculateDisposedFrmOutdatedVacation(
            EmployeeDetailsDTO employeeDetailsDTO,
            LocalDate startDate,
            LocalDate endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        if (endDate == null) endDate = LocalDate.now();
        Long workDurationMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        Double overallVacation = Double.parseDouble(workDurationMonths.toString()) * vacationPerMonth;
        Double vacationsTaken = Double.parseDouble(calculateDays(employeeDetailsDTO.getOverallDisposedTimeOff()
                .values()).toString());

        if (workDurationMonths < validVacationPeriod)
            employeeDetailsDTO = calculateVacationWhenWorkDuraionIsSmall(employeeDetailsDTO, overallVacation, vacationsTaken);

        LocalDate outDatePeriodStart = startDate;
        LocalDate outdatePeriodEnd = startDate.plusMonths(Long.parseLong(validVacationPeriod.toString()));
        Long outDatedDaysCount = ChronoUnit.DAYS.between(outDatePeriodStart, outdatePeriodEnd);

        LocalDate indatePeriodStart = outdatePeriodEnd.plusDays(1);
        LocalDate indatePeriodEnd = endDate;
        Long indateDaysCount = ChronoUnit.DAYS.between(indatePeriodStart, indatePeriodEnd);

        Double vacationGrantedFromOutDate = Double.parseDouble(outDatedDaysCount.toString()) * vacationPerMonth;
        Double vacationGrantedFromInDate = Double.parseDouble(indateDaysCount.toString()) * vacationPerMonth;


        if (vacationsTaken > vacationGrantedFromOutDate) {
            employeeDetailsDTO.setVacationLeftFromIndate(vacationsTaken - vacationGrantedFromOutDate);
            employeeDetailsDTO.setVacationLeftFromOutdated(0D);
            employeeDetailsDTO.setVacationInAdvance(0D);
        } else if (vacationsTaken < vacationGrantedFromOutDate) {
            employeeDetailsDTO.setVacationLeftFromIndate(vacationGrantedFromInDate);
            employeeDetailsDTO.setVacationLeftFromOutdated(vacationGrantedFromOutDate - vacationsTaken);
            employeeDetailsDTO.setVacationInAdvance(0D);
        } else {
            employeeDetailsDTO.setVacationLeftFromIndate(vacationGrantedFromInDate);
            employeeDetailsDTO.setVacationLeftFromOutdated(0D);
            employeeDetailsDTO.setVacationInAdvance(0D);
        }

        return employeeDetailsDTO;
    }


    private EmployeeDetailsDTO calculateDisposedFrmIndatedVacation(
            EmployeeDetailsDTO employeeDetailsDTO,
            LocalDate startDate,
            LocalDate endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        if (endDate == null) endDate = LocalDate.now();
        Long workDurationMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        Double overallVacation = Double.parseDouble(workDurationMonths.toString()) * vacationPerMonth;
        Double vacationsTaken = Double.parseDouble(calculateDays(employeeDetailsDTO.getOverallDisposedTimeOff()
                .values()).toString());

        if (workDurationMonths < validVacationPeriod)
            employeeDetailsDTO = calculateVacationWhenWorkDuraionIsSmall(employeeDetailsDTO, overallVacation, vacationsTaken);

        LocalDate outDatePeriodStart = startDate;
        LocalDate outdatePeriodEnd = startDate.plusMonths(Long.parseLong(validVacationPeriod.toString()));
        Long outDatedDaysCount = ChronoUnit.DAYS.between(outDatePeriodStart, outdatePeriodEnd);

        LocalDate indatePeriodStart = outdatePeriodEnd.plusDays(1);
        LocalDate indatePeriodEnd = endDate;
        Long indateDaysCount = ChronoUnit.DAYS.between(indatePeriodStart, indatePeriodEnd);

        Double vacationGrantedFromOutDate = Double.parseDouble(outDatedDaysCount.toString()) * vacationPerMonth;
        Double vacationGrantedFromInDate = Double.parseDouble(indateDaysCount.toString()) * vacationPerMonth;


        if (vacationsTaken > vacationGrantedFromInDate) {
            employeeDetailsDTO.setVacationLeftFromIndate(0D);
            employeeDetailsDTO.setVacationLeftFromOutdated(vacationsTaken - vacationGrantedFromInDate);
            employeeDetailsDTO.setVacationInAdvance(0D);
        } else if (vacationsTaken < vacationGrantedFromInDate) {
            employeeDetailsDTO.setVacationLeftFromIndate(vacationGrantedFromInDate - vacationsTaken);
            employeeDetailsDTO.setVacationLeftFromOutdated(vacationGrantedFromOutDate);
            employeeDetailsDTO.setVacationInAdvance(0D);
        } else {
            employeeDetailsDTO.setVacationLeftFromIndate(0D);
            employeeDetailsDTO.setVacationLeftFromOutdated(vacationGrantedFromOutDate);
            employeeDetailsDTO.setVacationInAdvance(0D);
        }

        return employeeDetailsDTO;
    }

    private EmployeeDetailsDTO calculateVacationWhenWorkDuraionIsSmall(EmployeeDetailsDTO employeeDetailsDTO,
            Double overallVacation,
            Double vacationsTaken) {
        if (overallVacation > vacationsTaken) {
            Double difference = overallVacation - vacationsTaken;
            employeeDetailsDTO.setVacationLeftFromIndate(difference);
            employeeDetailsDTO.setVacationLeftFromOutdated(0D);
            employeeDetailsDTO.setVacationInAdvance(0D);
        } else if (vacationsTaken > overallVacation) {
            Double difference = vacationsTaken - overallVacation;
            employeeDetailsDTO.setVacationLeftFromIndate(0D);
            employeeDetailsDTO.setVacationLeftFromOutdated(0D);
            employeeDetailsDTO.setVacationInAdvance(difference);
        } else {
            employeeDetailsDTO.setVacationLeftFromIndate(0D);
            employeeDetailsDTO.setVacationLeftFromOutdated(0D);
            employeeDetailsDTO.setVacationInAdvance(0D);
        }
        return employeeDetailsDTO;
    }

    private EmployeeDetailsDTO calculateDisposedFrmBalancedVacation(
            EmployeeDetailsDTO employeeDetailsDTO,
            LocalDate startDate,
            LocalDate endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        return null;
    }

    public boolean dateIsBetweenIncludingEndPoints(final LocalDate startDate,
            final LocalDate endDate,
            final LocalDate date) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    private Long calculateDays(Collection<Long> days) {
        Long sum = 0L;
        for (Long value : days) {
            sum += value;
        }
        return sum;
    }

    private Long calculateDays(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MONTHS.between(start, end);
    }
}
