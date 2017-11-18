package attendance_manager.utils;

import attendance_manager.csv.EmployeeCSVParser;
import attendance_manager.domain.*;
import attendance_manager.domain.types.VacationDisposeType;
import attendance_manager.model.EmployeeAttendanceDto;
import attendance_manager.model.EmployeeIdeintiferDto;
import attendance_manager.repository.*;
import attendance_manager.service.Individualtimeoff.IndividualTimeOffService;
import attendance_manager.service.dto.TimeOffTypeService;
import com.google.common.collect.Lists;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataLoader {

    public static void createCompanyData(
            CompanyConfigRepository companyConfigRepository,
            TimeOffTypeRepository timeOffTypeRepository,
            WorkingHoursSchemeRepository workingHoursSchemeRepository){

        TimeOffType slackTimeOffType1 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Vacation", true, true, true));
        TimeOffType slackTimeOffType2 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Day Off", false, true, true));
        TimeOffType slackTimeOffType3 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Medical Leave", false, true, true));
        TimeOffType slackTimeOffType4 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Missed Key", true, false, true));
        TimeOffType slackTimeOffType5 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Maternity Leave", false, false, true));
        TimeOffType slackTimeOffType6 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Business Trip", true, false, true));
        TimeOffType slackTimeOffType7 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Unpaid Day Off", false, false, true));
        TimeOffType slackTimeOffType8 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Working From Home", true, false, true));
        TimeOffType slackTimeOffType9 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Holiday Time Off", true, false, false));
        TimeOffType slackTimeOffType10 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Sick Leave", false, true, false));
        TimeOffType slackTimeOffType11 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Weekend Paid Work", true, false, false));

        WorkingHoursScheme workingHoursScheme1 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Fixed In-Out", new Short("8"), LocalTime.of(10, 0), LocalTime.of(19, 0), true));
        WorkingHoursScheme workingHoursScheme2 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Fixed Daily Hours", new Short("8"), null, null, false));
        WorkingHoursScheme workingHoursScheme3 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Fixed Weekly Hours", new Short("40"), null, null, false));
        WorkingHoursScheme workingHoursScheme4 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Fixed Monthly Hours", new Short("173"), null, null, false));
        WorkingHoursScheme workingHoursScheme5 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Free Hours", null, null, null, false));

        CompanyConfig companyConfig  = companyConfigRepository.save(new CompanyConfig(
                BusKeyGen.nextKey(),
                VacationDisposeType.FROM_INDATE_VACATION.name(),
                VacationDisposeType.FROM_OUTDATED_VACATION.name(),
                workingHoursScheme1,
                1.67,
                18D,
                LocalTime.of(13, 0),
               1D,
                true,
                false
        ));
    }

    public static void createUserData(UserRepository userRepository, AuthorityRepository authorityRepository, WorkingHoursSchemeRepository workingHoursSchemeRepository){

        WorkingHoursScheme workingHoursScheme = workingHoursSchemeRepository.findByTitle("Fixed In-Out");
        Authority authority = authorityRepository.save(new Authority(BusKeyGen.nextKey(), "EMPLOYEE"));
        Employee alfred = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "alfred.kaghyan@sflpro.com",
                "123456",
                "Alfred Kaghyan",
                null,
                true,
                true,
                true,
                true,
                true,
                "employee",
                LocalDate.of(2014, 1,10),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme));

        Employee hayk = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "hayk.mkrtchyan@sflpro.com",
                "123456",
                "Hayk Mkrtchyan",
                null,
                true,
                true,
                true,
                true,
                true,
                "employee",
                LocalDate.of(2012, 11,19),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme));

        Employee ani = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "ani.aghababyan@sflpro.com",
                "123456",
                "Ani Aghababyan",
                null,
                true,
                true,
                true,
                true,
                true,
                "employee",
                LocalDate.of(2010, 1,12),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme));

        Employee tatevik = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "tatevik.mailyan@sflpro.com",
                "123456",
                "Tatevik Mailyan",
                null,
                true,
                true,
                true,
                true,
                true,
                "employee",
                LocalDate.of(2015, 6,1),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme));

        Employee varduhi = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "varduhi.petrosyan@sflpro.com",
                "123456",
                "Varduhi Petrosyan",
                null,
                true,
                true,
                true,
                true,
                true,
                "employee",
                LocalDate.of(2011, 12,27),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme));

        Employee lilit = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "lilit.mkrtchyan@sflpro.com",
                "123456",
                "Lilit Mkrtchyan",
                null,
                true,
                true,
                true,
                true,
                true,
                "employee",
                LocalDate.of(2014, 6,23),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme));

    }

    public static void loadIndividualTimeOffs(TimeOffTypeService timeOffTypeService, IndividualTimeOffService individualTimeOffService){
        final List<Map<EmployeeIdeintiferDto, List<EmployeeAttendanceDto>>> reports = new ArrayList<>();
        reports.add(EmployeeCSVParser.generate("alfred.csv"));
        reports.add(EmployeeCSVParser.generate("ani.csv"));
        reports.add(EmployeeCSVParser.generate("hayk.csv"));
        reports.add(EmployeeCSVParser.generate("lilit.csv"));
        reports.add(EmployeeCSVParser.generate("tatev.csv"));
        reports.add(EmployeeCSVParser.generate("varduhi.csv"));

        reports.forEach(report -> {
            report.forEach((k, v) -> {
                v.forEach(value -> {
                    final IndividualTimeOff individualTimeOff = new IndividualTimeOff();
                    individualTimeOff.setStart(LocalDateTime.of(LocalDate.parse(value.getStart()), LocalTime.MIN));
                    individualTimeOff.setEnd(LocalDateTime.of(LocalDate.parse(value.getEnd()), LocalTime.MAX));
                    individualTimeOff.setReason(timeOffTypeService.getByName(value.getReason()));
                    individualTimeOff.setDisposed(Boolean.valueOf(value.getDisposed()));
                    individualTimeOff.setApproved(true);
                    individualTimeOff.setSsn(BusKeyGen.nextKey());
                    individualTimeOffService.create(individualTimeOff, k.getUsername());
                });
            });
        });

    }

}
