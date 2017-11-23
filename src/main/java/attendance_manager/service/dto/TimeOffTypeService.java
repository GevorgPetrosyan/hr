package attendance_manager.service.dto;

import attendance_manager.domain.TimeOffType;

/**
 * @author Marta Ginosyan
 */
public interface TimeOffTypeService {

    TimeOffType saveOrUpdateTimeOffType(TimeOffType timeOffType);

    TimeOffType getByName(final String title);

}
