package attendance_manager.rest;

import attendance_manager.csv.EmployeeCSVParser;
import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.model.EmployeeAttendanceDto;
import attendance_manager.model.EmployeeIdeintiferDto;
import attendance_manager.service.Individualtimeoff.IndividualTimeOffService;
import attendance_manager.service.dto.TimeOffTypeService;
import attendance_manager.service.mail.EmailSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Heartbeat {

    @Autowired
    private EmailSendingService emailSendingService;

    @Autowired
    private IndividualTimeOffService individualTimeOffService;

    @Autowired
    private TimeOffTypeService timeOffTypeService;

    @GetMapping(value = "/heartbeat")
    @ResponseBody
    public String heartBeat() {
        return "ALIVE";
    }

    @GetMapping(value = "/init")
    @ResponseBody
    public String init() {
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
                    individualTimeOff.setStart(LocalDateTime.of(LocalDate.parse(value.getStart()), LocalTime.MIDNIGHT));
                    individualTimeOff.setEnd(LocalDateTime.of(LocalDate.parse(value.getEnd()), LocalTime.MIDNIGHT));
                    individualTimeOff.setReason(timeOffTypeService.getByName(value.getReason()));
                    individualTimeOff.setDisposed(Boolean.valueOf(value.getDisposed()));
                    individualTimeOffService.create(individualTimeOff, k.getUsername());
                });
            });
        });

        return "Initialized";
    }
}
