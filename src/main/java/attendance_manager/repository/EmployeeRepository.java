package attendance_manager.repository;

import attendance_manager.domain.Employee;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

public interface EmployeeRepository extends BaseRepository<Employee> {

    List<Employee> findAllByDtypeAndDtypeNotNull(String dtype);
}
