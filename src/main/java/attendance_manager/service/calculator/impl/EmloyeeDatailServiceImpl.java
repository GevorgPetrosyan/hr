package attendance_manager.service.calculator.impl;

import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.domain.TimeOffType;
import attendance_manager.model.EmployeeDetails;
import attendance_manager.service.calculator.EmployeeDetailService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EmloyeeDatailServiceImpl implements EmployeeDetailService {

    @Override
    public EmployeeDetails calculateWhenTimeOffsDisposedFromOutdatedVacation(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        EmployeeDetails employeeDetails = calculateIndependentData(
                employeeId,
                employeeName,
                joined,
                leaved,
                timeOffs,
                vacationPerMonth,
                validVacationPeriod);

        calculateDisposedFrmOutdatedVacation(employeeDetails, joined, leaved, vacationPerMonth, validVacationPeriod);
        return employeeDetails;
    }

    @Override
    public EmployeeDetails calculateWhenTimeOffsDisposedFromIndateVacation(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        EmployeeDetails employeeDetails = calculateIndependentData(
                employeeId,
                employeeName,
                joined,
                leaved,
                timeOffs,
                vacationPerMonth,
                validVacationPeriod);

        calculateDisposedFrmIndatedVacation(employeeDetails, joined, leaved, vacationPerMonth, validVacationPeriod);
        return employeeDetails;
    }

    @Override
    public EmployeeDetails calculateWhenTimeOffsDisposedBalanced(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        return null;
    }

    private EmployeeDetails calculateIndependentData(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        EmployeeDetails employeeDetails = new EmployeeDetails();
        Map<TimeOffType, Long> overallTimeOff = summarizeTimeOff(timeOffs);
        Map<TimeOffType, Long> overallDisposedTimeOff = summarizeDisposedTimeOff(timeOffs);
        Double overAllDisposableVacationTaken = calculateOverAllDisposableVacationTaken(overallTimeOff);
        Double overallVacationFromOutdated = calculateOverallVacationFromOutdated(joined, leaved, vacationPerMonth, validVacationPeriod);
        Double overallVacationFromIndate = calculateOverallVacationFromIndate(joined, leaved, vacationPerMonth, validVacationPeriod);
        Double overallVacationGranted = calculateOverallVacationGranted(joined, leaved, vacationPerMonth, validVacationPeriod);

        employeeDetails.setEmployeeId(employeeId);
        employeeDetails.setEmployeeName(employeeName);
        employeeDetails.setJoinDate(joined);
        employeeDetails.setLeaveDate(leaved);
        employeeDetails.setOverallTimeOff(overallTimeOff);
        employeeDetails.setOverallDisposedTimeOff(overallDisposedTimeOff);
        employeeDetails.setOverallVacationFromOutdated(overallVacationFromOutdated);
        employeeDetails.setOverallVacationFromIndate(overallVacationFromIndate);
        employeeDetails.setOverallVacationGranted(overallVacationGranted);
        employeeDetails.setOverallDisposableVacationTaken(overAllDisposableVacationTaken);
        return employeeDetails;
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

    private EmployeeDetails calculateDisposedFrmOutdatedVacation(
            EmployeeDetails employeeDetails,
            LocalDate startDate,
            LocalDate endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        if (endDate == null) endDate = LocalDate.now();
        Long workDurationMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        Double overallVacation = Double.parseDouble(workDurationMonths.toString()) * vacationPerMonth;
        Double vacationsTaken = Double.parseDouble(calculateDays(employeeDetails.getOverallDisposedTimeOff()
                .values()).toString());

        if (workDurationMonths < validVacationPeriod)
            employeeDetails = calculateVacationWhenWorkDuraionIsSmall(employeeDetails, overallVacation, vacationsTaken);

        LocalDate outDatePeriodStart = startDate;
        LocalDate outdatePeriodEnd = startDate.plusMonths(Long.parseLong(validVacationPeriod.toString()));
        Long outDatedDaysCount = ChronoUnit.DAYS.between(outDatePeriodStart, outdatePeriodEnd);

        LocalDate indatePeriodStart = outdatePeriodEnd.plusDays(1);
        LocalDate indatePeriodEnd = endDate;
        Long indateDaysCount = ChronoUnit.DAYS.between(indatePeriodStart, indatePeriodEnd);

        Double vacationGrantedFromOutDate = Double.parseDouble(outDatedDaysCount.toString()) * vacationPerMonth;
        Double vacationGrantedFromInDate = Double.parseDouble(indateDaysCount.toString()) * vacationPerMonth;


        if (vacationsTaken > vacationGrantedFromOutDate) {
            employeeDetails.setVacationLeftFromIndate(vacationsTaken - vacationGrantedFromOutDate);
            employeeDetails.setVacationLeftFromOutdated(0D);
            employeeDetails.setVacationInAdvance(0D);
        } else if (vacationsTaken < vacationGrantedFromOutDate) {
            employeeDetails.setVacationLeftFromIndate(vacationGrantedFromInDate);
            employeeDetails.setVacationLeftFromOutdated(vacationGrantedFromOutDate - vacationsTaken);
            employeeDetails.setVacationInAdvance(0D);
        } else {
            employeeDetails.setVacationLeftFromIndate(vacationGrantedFromInDate);
            employeeDetails.setVacationLeftFromOutdated(0D);
            employeeDetails.setVacationInAdvance(0D);
        }

        return employeeDetails;
    }


    private EmployeeDetails calculateDisposedFrmIndatedVacation(
            EmployeeDetails employeeDetails,
            LocalDate startDate,
            LocalDate endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {

        if (endDate == null) endDate = LocalDate.now();
        Long workDurationMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        Double overallVacation = Double.parseDouble(workDurationMonths.toString()) * vacationPerMonth;
        Double vacationsTaken = Double.parseDouble(calculateDays(employeeDetails.getOverallDisposedTimeOff()
                .values()).toString());

        if (workDurationMonths < validVacationPeriod)
            employeeDetails = calculateVacationWhenWorkDuraionIsSmall(employeeDetails, overallVacation, vacationsTaken);

        LocalDate outDatePeriodStart = startDate;
        LocalDate outdatePeriodEnd = startDate.plusMonths(Long.parseLong(validVacationPeriod.toString()));
        Long outDatedDaysCount = ChronoUnit.DAYS.between(outDatePeriodStart, outdatePeriodEnd);

        LocalDate indatePeriodStart = outdatePeriodEnd.plusDays(1);
        LocalDate indatePeriodEnd = endDate;
        Long indateDaysCount = ChronoUnit.DAYS.between(indatePeriodStart, indatePeriodEnd);

        Double vacationGrantedFromOutDate = Double.parseDouble(outDatedDaysCount.toString()) * vacationPerMonth;
        Double vacationGrantedFromInDate = Double.parseDouble(indateDaysCount.toString()) * vacationPerMonth;


        if (vacationsTaken > vacationGrantedFromInDate) {
            employeeDetails.setVacationLeftFromIndate(0D);
            employeeDetails.setVacationLeftFromOutdated(vacationsTaken - vacationGrantedFromInDate);
            employeeDetails.setVacationInAdvance(0D);
        } else if (vacationsTaken < vacationGrantedFromInDate) {
            employeeDetails.setVacationLeftFromIndate(vacationGrantedFromInDate - vacationsTaken);
            employeeDetails.setVacationLeftFromOutdated(vacationGrantedFromOutDate);
            employeeDetails.setVacationInAdvance(0D);
        } else {
            employeeDetails.setVacationLeftFromIndate(0D);
            employeeDetails.setVacationLeftFromOutdated(vacationGrantedFromOutDate);
            employeeDetails.setVacationInAdvance(0D);
        }

        return employeeDetails;
    }

    private EmployeeDetails calculateVacationWhenWorkDuraionIsSmall(EmployeeDetails employeeDetails,
            Double overallVacation,
            Double vacationsTaken) {
        if (overallVacation > vacationsTaken) {
            Double difference = overallVacation - vacationsTaken;
            employeeDetails.setVacationLeftFromIndate(difference);
            employeeDetails.setVacationLeftFromOutdated(0D);
            employeeDetails.setVacationInAdvance(0D);
        } else if (vacationsTaken > overallVacation) {
            Double difference = vacationsTaken - overallVacation;
            employeeDetails.setVacationLeftFromIndate(0D);
            employeeDetails.setVacationLeftFromOutdated(0D);
            employeeDetails.setVacationInAdvance(difference);
        } else {
            employeeDetails.setVacationLeftFromIndate(0D);
            employeeDetails.setVacationLeftFromOutdated(0D);
            employeeDetails.setVacationInAdvance(0D);
        }
        return employeeDetails;
    }

    private EmployeeDetails calculateDisposedFrmBalancedVacation(
            EmployeeDetails employeeDetails,
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
}
