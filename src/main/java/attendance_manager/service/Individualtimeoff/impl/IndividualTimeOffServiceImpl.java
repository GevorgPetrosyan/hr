package attendance_manager.service.Individualtimeoff.impl;

import attendance_manager.domain.IndividualTimeOff;
import attendance_manager.repository.IndividualTimeOffRepository;
import attendance_manager.service.Individualtimeoff.IndividualTimeOffService;
import attendance_manager.service.Individualtimeoff.dto.IndividualTimeOffDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;

/**
 * @author sergey, created on 11/17/17.
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class IndividualTimeOffServiceImpl implements IndividualTimeOffService {

    @Autowired
    private IndividualTimeOffRepository individualTimeOffRepository;

    @Transactional
    @Override
    public Long create(final IndividualTimeOffDTO individualTimeOffDTO) {
        Assert.notNull(individualTimeOffDTO, "Argument individualTimeOffDTO should not be null");
        log.debug("Requested to create individual time off for dto '{}'", individualTimeOffDTO);
        final IndividualTimeOff individualTimeOff = new IndividualTimeOff();
        individualTimeOffDTO.updateDomainEntityPlainProperties(individualTimeOff);
        final IndividualTimeOff fromDB = individualTimeOffRepository.save(individualTimeOff);
        log.debug("Successfully created individual time off '{}' for dto", fromDB, individualTimeOff);
        return fromDB.getId();
    }

    @Override
    public IndividualTimeOff getById(final Long id) {
        Assert.notNull(id, "Argument id should not be null");
        log.debug("Requested to get individual time off by id '{}'", id);
        final IndividualTimeOff fromDB = individualTimeOffRepository.findOne(id);
        assertIndividualTimeOff(fromDB, id);
        log.debug("Successfully retrieved IndividualTimeOff '{}' by id '{}'", fromDB, id);
        return fromDB;
    }

    @Transactional
    @Override
    public void update(final Long id, final IndividualTimeOffDTO individualTimeOffDTO) {
        Assert.notNull(id, "Argument id should not be null");
        Assert.notNull(individualTimeOffDTO, "Argument individualTimeOffDTO should not be null");
        log.debug("Requested to update IndividualTimeOff by id '{}', dto '{}'", id, individualTimeOffDTO);
        final IndividualTimeOff fromDB = individualTimeOffRepository.findOne(id);
        assertIndividualTimeOff(fromDB, id);
        individualTimeOffDTO.updateDomainEntityPlainProperties(fromDB);
        log.debug("Successfully updated individualTimeOffDTO '{}'", fromDB);
    }

    @Transactional
    @Override
    public void delete(final Long id) {
        Assert.notNull(id, "Argument id should not be null");
        log.debug("Requested to delete individual time off with id '{}'", id);
        final IndividualTimeOff fromDB = individualTimeOffRepository.findOne(id);
        individualTimeOffRepository.delete(fromDB);
        log.debug("Successfully deleted individual time off '{}'", fromDB);
    }

    private void assertIndividualTimeOff(final IndividualTimeOff fromDB, final Long id) {
        if (fromDB == null) {
            log.error("IndividualTimeOff with id '{}' was not found", id);
            throw new EntityNotFoundException(String.format("IndividualTimeOff with id %d was not found", id));
        }
    }
}
