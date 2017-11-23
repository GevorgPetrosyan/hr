package attendance_manager.service.dto.impl;

import attendance_manager.domain.TimeOffType;
import attendance_manager.repository.TimeOffTypeRepository;
import attendance_manager.service.dto.TimeOffTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author Marta Ginosyan
 */

@Service
@Transactional(readOnly = true)
@Slf4j
public class TimeOffTypeServiceImpl implements TimeOffTypeService {

    @Autowired
    TimeOffTypeRepository timeOffTypeRepository;

    @Override
    public TimeOffType saveOrUpdateTimeOffType(TimeOffType timeOffType) {
        Assert.notNull(timeOffType);
        return timeOffTypeRepository.save(timeOffType);
    }

    @Override
    public TimeOffType getByName(final String title) {
        Assert.notNull(title);
        return timeOffTypeRepository.findByTitle(title);
    }
}
