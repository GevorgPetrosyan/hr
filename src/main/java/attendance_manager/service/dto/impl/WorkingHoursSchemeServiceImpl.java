package attendance_manager.service.dto.impl;

import attendance_manager.domain.WorkingHoursScheme;
import attendance_manager.repository.WorkingHoursSchemeRepository;
import attendance_manager.service.dto.WorkingHoursSchemeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
@Slf4j
public class WorkingHoursSchemeServiceImpl implements WorkingHoursSchemeService {

    @Autowired
    WorkingHoursSchemeRepository workingHoursSchemeRepository;

    @Override
    public WorkingHoursScheme saveOrUpdateWorkingHoursScheme(WorkingHoursScheme workingHoursScheme) {
        Assert.notNull(workingHoursScheme);
        return workingHoursSchemeRepository.save(workingHoursScheme);
    }
}
