package attendance_manager.service.timeofftype;

import attendance_manager.domain.TimeOffType;
import attendance_manager.utils.AbstractBaseEntityDTO;

/**
 * @author sergey, created on 11/18/17.
 */
public class TimeOffTypeDTO extends AbstractBaseEntityDTO<TimeOffType> {

    private String title;

    private Boolean isPaid;

    private Boolean disposableFromVacation;

    private Boolean isValid;

    @Override
    public void updateDomainEntityPlainProperties(final TimeOffType domainEntity) {
        domainEntity.setDisposableFromVacation(disposableFromVacation);
        domainEntity.setPaid(isPaid);
        domainEntity.setDisposableFromVacation(disposableFromVacation);
        domainEntity.setValid(isValid);
    }
}
