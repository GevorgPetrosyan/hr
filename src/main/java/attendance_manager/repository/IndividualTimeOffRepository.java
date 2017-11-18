package attendance_manager.repository;

import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.domain.TimeOffType;
import attendance_manager.domain.User;

import java.util.List;

/**
 * @author sergey, created on 11/17/17.
 */
public interface IndividualTimeOffRepository extends BaseRepository<IndividualTimeOff> {

    List<IndividualTimeOff> findAllByApprovedTrueAndReasonAndUser(TimeOffType timeOffType, User user);

    List<IndividualTimeOff> findAllByApprovedTrueAndUser(User user);
}
