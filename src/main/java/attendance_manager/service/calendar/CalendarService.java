package attendance_manager.service.calendar;

import attendance_manager.domain.TimeOffType;
import attendance_manager.domain.types.VacationDisposeType;
import attendance_manager.service.calculator.EmployeeDetailsDTO;

import java.util.List;

/**
 * @author Marta Ginosyan
 */
public interface CalendarService {

    List<EmployeeDetailsDTO> getEmployeesDetails(VacationDisposeType disposeType);

    List<EmployeeDetailsDTO> getEmployeesDetailsByType(TimeOffType timeOffType, VacationDisposeType disposeType);

    List<VacationDisposeType> getVacationDisposeTypes();

}
