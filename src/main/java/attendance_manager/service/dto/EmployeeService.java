package attendance_manager.service.dto;

import attendance_manager.domain.Employee;

import java.util.List;

/**
 * @author Marta Ginosyan
 */
public interface EmployeeService {

    Employee saveOrUpdate(Employee employee);

    List<Employee> findAll();

}
