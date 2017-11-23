package attendance_manager.service.calculator;

import attendance_manager.domain.IndividualTimeOff;

import java.time.LocalDate;
import java.util.List;

public interface EmployeesDetailsService {

    EmployeeDetailsDTO calculateWhenTimeOffsDisposedFromOutdatedVacation(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod);

    EmployeeDetailsDTO calculateWhenTimeOffsDisposedFromIndateVacation(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod);

    EmployeeDetailsDTO calculateWhenTimeOffsDisposedBalanced(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod);
}
