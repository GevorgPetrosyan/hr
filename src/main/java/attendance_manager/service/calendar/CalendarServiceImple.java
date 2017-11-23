package attendance_manager.service.calendar;


import attendance_manager.domain.CompanyConfig;
import attendance_manager.domain.Employee;
import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.domain.TimeOffType;
import attendance_manager.domain.types.VacationDisposeType;
import attendance_manager.repository.CompanyConfigRepository;
import attendance_manager.repository.EmployeeRepository;
import attendance_manager.repository.IndividualTimeOffRepository;
import attendance_manager.service.calculator.EmployeeDetailsDTO;
import attendance_manager.service.calculator.EmployeesDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Marta Ginosyan
 */

@Service
@Transactional(readOnly = true)
@Slf4j
public class CalendarServiceImple implements CalendarService {

    @Autowired
    EmployeesDetailsService employeesDetailsService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    IndividualTimeOffRepository individualTimeOffRepository;

    @Autowired
    CompanyConfigRepository companyConfigRepository;

    @Override
    public List<EmployeeDetailsDTO> getEmployeesDetails(VacationDisposeType disposeType) {
        Assert.notNull(disposeType);
        List<EmployeeDetailsDTO> employeeDetails = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAllByDtypeAndDtypeNotNull("employee");
        if (employees.isEmpty()) return null;
        List<CompanyConfig> companyConfigs = companyConfigRepository.findAll();
        if (companyConfigs.isEmpty()) return null;
        CompanyConfig companyConfig = companyConfigs.get(0);
        employees.forEach(employee -> {
            List<IndividualTimeOff> individualTimeOffs = individualTimeOffRepository.findAllByApprovedTrueAndUser(employee);
            initEmployeeDetails(
                    employeeDetails,
                    employee,
                    disposeType,
                    individualTimeOffs,
                    companyConfig
            );
        });
        return employeeDetails;
    }

    @Override
    public List<EmployeeDetailsDTO> getEmployeesDetailsByType(TimeOffType timeOffType, VacationDisposeType disposeType) {
        Assert.notNull(timeOffType);
        Assert.notNull(disposeType);
        List<EmployeeDetailsDTO> employeeDetails = new ArrayList<>();
        List<Employee> employees = employeeRepository.findAllByDtypeAndDtypeNotNull("employee");
        if (employees.isEmpty()) return null;
        List<CompanyConfig> companyConfigs = companyConfigRepository.findAll();
        if (companyConfigs.isEmpty()) return null;
        CompanyConfig companyConfig = companyConfigs.get(0);
        employees.forEach(employee -> {
            List<IndividualTimeOff> individualTimeOffs = individualTimeOffRepository.findAllByApprovedTrueAndReasonAndUser(timeOffType, employee);
            initEmployeeDetails(
                    employeeDetails,
                    employee,
                    disposeType,
                    individualTimeOffs,
                    companyConfig
            );
        });
        return employeeDetails;
    }

    @Override
    public List<VacationDisposeType> getVacationDisposeTypes() {
        return Arrays.asList(VacationDisposeType.values());
    }

    private void initEmployeeDetails(
            List<EmployeeDetailsDTO> employeeDetails,
            Employee employee,
            VacationDisposeType disposeType,
            List<IndividualTimeOff> individualTimeOffs,
            CompanyConfig companyConfig) {
        switch (disposeType.name()) {
            case "FROM_OUTDATED_VACATION":
                employeeDetails.add(employeesDetailsService.calculateWhenTimeOffsDisposedFromOutdatedVacation(
                        employee.getId(),
                        employee.getName(),
                        employee.getJoiningDate(),
                        employee.getLeavingDate(),
                        individualTimeOffs,
                        companyConfig.getVacationPerMonth(),
                        companyConfig.getValidVacationPeriod()));
                break;
            case "FROM_INDATE_VACATION":
                employeeDetails.add(employeesDetailsService.calculateWhenTimeOffsDisposedFromIndateVacation(
                        employee.getId(),
                        employee.getName(),
                        employee.getJoiningDate(),
                        employee.getLeavingDate(),
                        individualTimeOffs,
                        companyConfig.getVacationPerMonth(),
                        companyConfig.getValidVacationPeriod()));
                break;
        }
    }
}
