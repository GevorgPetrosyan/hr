package attendance_manager.service.dto.impl;

import attendance_manager.domain.Employee;
import attendance_manager.repository.EmployeeRepository;
import attendance_manager.service.dto.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Marta Ginosyan
 */

@Service
@Transactional(readOnly = true)
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee saveOrUpdate(Employee employee) {
        Assert.notNull(employee);
        return employeeRepository.save(employee);
    }
}
