package attendance_manager.service.company;

import attendance_manager.domain.CompanyConfig;
import attendance_manager.domain.TimeOffType;
import attendance_manager.domain.WorkingHoursScheme;
import attendance_manager.domain.types.VacationDisposeType;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

public class CompanyConfigDTO {

    private CompanyConfig companyConfig;
    private List<WorkingHoursScheme> workingHoursSchemes;
    private List<TimeOffType> timeOffTypes;
    private List<VacationDisposeType> vacationDisposeTypes;


    public CompanyConfig getCompanyConfig() {
        return companyConfig;
    }

    public void setCompanyConfig(CompanyConfig companyConfig) {
        this.companyConfig = companyConfig;
    }

    public List<WorkingHoursScheme> getWorkingHoursSchemes() {
        return workingHoursSchemes;
    }

    public void setWorkingHoursSchemes(List<WorkingHoursScheme> workingHoursSchemes) {
        this.workingHoursSchemes = workingHoursSchemes;
    }

    public List<TimeOffType> getTimeOffTypes() {
        return timeOffTypes;
    }

    public void setTimeOffTypes(List<TimeOffType> timeOffTypes) {
        this.timeOffTypes = timeOffTypes;
    }

    public List<VacationDisposeType> getVacationDisposeTypes() {
        return vacationDisposeTypes;
    }

    public void setVacationDisposeTypes(List<VacationDisposeType> vacationDisposeTypes) {
        this.vacationDisposeTypes = vacationDisposeTypes;
    }
}
