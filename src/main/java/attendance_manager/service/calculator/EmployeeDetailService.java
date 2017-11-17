package attendance_manager.service.calculator;

import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.model.EmployeeDetails;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeDetailService {

    EmployeeDetails calculateWhenTimeOffsDisposedFromOutdatedVacation(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod);

    EmployeeDetails calculateWhenTimeOffsDisposedFromIndateVacation(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod);

    EmployeeDetails calculateWhenTimeOffsDisposedBalanced(
            Long employeeId,
            String employeeName,
            LocalDate joined,
            LocalDate leaved,
            List<IndividualTimeOff> timeOffs,
            Double vacationPerMonth,
            Double validVacationPeriod);
}
