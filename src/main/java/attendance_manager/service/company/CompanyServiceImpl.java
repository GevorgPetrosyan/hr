package attendance_manager.service.company;

import attendance_manager.domain.CompanyConfig;
import attendance_manager.domain.types.VacationDisposeType;
import attendance_manager.repository.CompanyConfigRepository;
import attendance_manager.repository.TimeOffTypeRepository;
import attendance_manager.repository.WorkingHoursSchemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * @author Marta Ginosyan
 */

@Service
@Transactional(readOnly = true)
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    TimeOffTypeRepository timeOffTypeRepository;

    @Autowired
    WorkingHoursSchemeRepository workingHoursSchemeRepository;

    @Autowired
    CompanyConfigRepository companyConfigRepository;

    @Override
    public CompanyConfigDTO getCompanyService() {
        CompanyConfigDTO companyConfigDTO = new CompanyConfigDTO();
        companyConfigDTO.setWorkingHoursSchemes(workingHoursSchemeRepository.findAll());
        companyConfigDTO.setTimeOffTypes(timeOffTypeRepository.findAll());
        companyConfigDTO.setVacationDisposeTypes(Arrays.asList(VacationDisposeType.values()));
        List<CompanyConfig> companyConfigs = companyConfigRepository.findAll();
        CompanyConfig companyConfig;
        if (companyConfigs.isEmpty()) companyConfig = new CompanyConfig();
        else companyConfig = companyConfigs.get(0);
        companyConfigDTO.setCompanyConfig(companyConfig);

        return companyConfigDTO;
    }

    @Override
    public void saveCompanyConfig(CompanyConfigDTO companyConfigDTO) {
        Assert.notNull(companyConfigDTO);
        Assert.notNull(companyConfigDTO.getTimeOffTypes());
        Assert.notNull(companyConfigDTO.getWorkingHoursSchemes());
        Assert.notNull(companyConfigDTO.getCompanyConfig());

        timeOffTypeRepository.save(companyConfigDTO.getTimeOffTypes());
        workingHoursSchemeRepository.save(companyConfigDTO.getWorkingHoursSchemes());
        companyConfigRepository.save(companyConfigDTO.getCompanyConfig());
    }
}
