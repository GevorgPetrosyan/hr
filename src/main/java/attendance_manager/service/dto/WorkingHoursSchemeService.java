package attendance_manager.service.dto;

import attendance_manager.domain.WorkingHoursScheme;

/**
 * @author Marta Ginosyan
 */
public interface WorkingHoursSchemeService {

    WorkingHoursScheme saveOrUpdateWorkingHoursScheme(WorkingHoursScheme workingHoursScheme);
}
