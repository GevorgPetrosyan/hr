package attendance_manager.rest;

import attendance_manager.domain.Employee;
import attendance_manager.domain.types.VacationDisposeType;
import attendance_manager.service.calculator.EmployeeDetailsDTO;
import attendance_manager.service.calendar.CalendarService;
import attendance_manager.service.dto.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dummy")
//@PreAuthorize("hasAnyAuthority('ROLE_EMPLOYEE')")
public class DummyController  {

    @Autowired
    CalendarService calendarService;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/employees/disposed_fromOutDated", method = RequestMethod.GET)
    public List<EmployeeDetailsDTO> getEmployeesDataFromOutDated(){
        return calendarService.getEmployeesDetails(VacationDisposeType.FROM_OUTDATED_VACATION);
    }

    @RequestMapping(value = "/employees/disposed_fromInDated", method = RequestMethod.GET)
    public List<EmployeeDetailsDTO> getEmployeesDataFromIndate(){
        return calendarService.getEmployeesDetails(VacationDisposeType.FROM_INDATE_VACATION);
    }

    @RequestMapping(value = "/all_employees", method = RequestMethod.GET)
    public List<Employee> employees(){
        return employeeService.findAll();
    }




}
