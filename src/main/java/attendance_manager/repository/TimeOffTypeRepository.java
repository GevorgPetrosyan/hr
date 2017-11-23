package attendance_manager.repository;

import attendance_manager.domain.TimeOffType;

/**
 * @author Marta Ginosyan
 */
public interface TimeOffTypeRepository extends BaseRepository<TimeOffType> {

    TimeOffType findByTitle(final String title);

}
