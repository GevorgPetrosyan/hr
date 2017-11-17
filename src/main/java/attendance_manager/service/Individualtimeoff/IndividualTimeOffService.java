package attendance_manager.service.Individualtimeoff;

import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.service.Individualtimeoff.dto.IndividualTimeOffDTO;

import javax.validation.constraints.NotNull;

/**
 * @author sergey, created on 11/17/17.
 */
public interface IndividualTimeOffService {

    Long create(@NotNull final IndividualTimeOffDTO individualTimeOffDTO);

    IndividualTimeOff getById(@NotNull final Long id);

    void update(@NotNull final Long id, @NotNull final IndividualTimeOffDTO individualTimeOffDTO);

    void delete(@NotNull final Long id);
}
