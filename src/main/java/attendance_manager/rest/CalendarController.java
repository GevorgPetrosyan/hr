package attendance_manager.rest;

import attendance_manager.domain.TimeOffType;
import attendance_manager.domain.types.VacationDisposeType;
import attendance_manager.service.calculator.EmployeeDetailsDTO;
import attendance_manager.service.calendar.CalendarService;
import attendance_manager.service.dto.TimeOffTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Marta Ginosyan
 * Team: Lime
 * Project: greetz3-hr
 * Date: 11/18/17
 */
@RestController("/calendar")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private TimeOffTypeService timeOffTypeService;

    @RequestMapping(value = "/vacationDisposeTypes", method = RequestMethod.GET)
    public List<VacationDisposeType> getVacationDisposeTypes() {
        return calendarService.getVacationDisposeTypes();
    }

    @RequestMapping(value = "/employee-details")
    public List<EmployeeDetailsDTO> getEmployeeDetails(@RequestParam final String timeOffTypeTitle, @RequestParam final VacationDisposeType vacationDisposeType) {
        if (timeOffTypeTitle != null) {
            TimeOffType timeOffType = timeOffTypeService.getByName(timeOffTypeTitle);
            return calendarService.getEmployeesDetailsByType(timeOffType, vacationDisposeType);
        }
        return calendarService.getEmployeesDetails(vacationDisposeType);
    }

}
