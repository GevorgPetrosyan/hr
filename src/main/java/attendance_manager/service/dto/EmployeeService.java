package attendance_manager.service.dto;

import attendance_manager.domain.Employee;

/**
 * @author Marta Ginosyan
 */
public interface EmployeeService {

    Employee saveOrUpdate(Employee employee);

}
