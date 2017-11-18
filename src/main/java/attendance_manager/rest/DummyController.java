package attendance_manager.rest;

import attendance_manager.domain.types.VacationDisposeType;
import attendance_manager.service.calculator.EmployeeDetailsDTO;
import attendance_manager.service.calendar.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dummy")
public class DummyController  {

    @Autowired
    CalendarService calendarService;

    @RequestMapping(value = "/employees/disposed_fromOutDated", method = RequestMethod.GET)
    public List<EmployeeDetailsDTO> getEmployeesDataFromOutDated(){
        return calendarService.getEmployeesDetails(VacationDisposeType.FROM_OUTDATED_VACATION);
    }

    @RequestMapping(value = "/employees/disposed_fromInDated", method = RequestMethod.GET)
    public List<EmployeeDetailsDTO> getEmployeesDataFromIndate(){
        return calendarService.getEmployeesDetails(VacationDisposeType.FROM_INDATE_VACATION);
    }




}
