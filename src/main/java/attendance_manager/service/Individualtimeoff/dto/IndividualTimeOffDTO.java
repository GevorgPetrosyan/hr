package attendance_manager.service.Individualtimeoff.dto;

import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.domain.TimeOffType;
import attendance_manager.utils.AbstractBaseEntityDTO;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author sergey, created on 11/17/17.
 */
@Data
public class IndividualTimeOffDTO extends AbstractBaseEntityDTO<IndividualTimeOff> {

    private Long employeeId;
    private String title;
    private String comment;
    private String startDate;
    private String endDate;


    @Override
    public void updateDomainEntityPlainProperties(final IndividualTimeOff domainEntity) {
        domainEntity.setComment(comment);
        domainEntity.setStart(LocalDateTime.parse(startDate));
        domainEntity.setEnd(LocalDateTime.parse(endDate));
        final TimeOffType reason = new TimeOffType();
        reason.setTitle(title);
        domainEntity.setReason(reason);
    }
}
