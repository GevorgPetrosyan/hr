package attendance_manager.listener;

import attendance_manager.repository.*;
import attendance_manager.service.Individualtimeoff.IndividualTimeOffService;
import attendance_manager.service.dto.TimeOffTypeService;
import attendance_manager.utils.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener {

    private static boolean DATA_LOADED = false;

    private UserRepository userRepository;
    private CompanyConfigRepository companyConfigRepository;
    private TimeOffTypeRepository timeOffTypeRepository;
    private AuthorityRepository authorityRepository;
    private WorkingHoursSchemeRepository workingHoursSchemeRepository;
    private TimeOffTypeService timeOffTypeService;
    private IndividualTimeOffService individualTimeOffService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCompanyConfigRepository(CompanyConfigRepository companyConfigRepository ) {
        this.companyConfigRepository = companyConfigRepository;
    }

    @Autowired
    public void setTimeOffTypeRepository(TimeOffTypeRepository timeOffTypeRepository ) {
        this.timeOffTypeRepository = timeOffTypeRepository;
    }

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository ) {
        this.authorityRepository = authorityRepository;
    }

    @Autowired
    public void setWorkingHoursSchemeRepository(WorkingHoursSchemeRepository workingHoursSchemeRepository ) {
        this.workingHoursSchemeRepository = workingHoursSchemeRepository;
    }

    @Autowired
    public void setTimeOffTypeService(TimeOffTypeService timeOffTypeService) {
        this.timeOffTypeService = timeOffTypeService;
    }

    @Autowired
    public void setIndividualTimeOffService(IndividualTimeOffService individualTimeOffService) {
        this.individualTimeOffService = individualTimeOffService;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        if (DATA_LOADED) {
            return;
        }
        DataLoader.createCompanyData(companyConfigRepository, timeOffTypeRepository, workingHoursSchemeRepository);
        DataLoader.createUserData(userRepository, authorityRepository, workingHoursSchemeRepository);
        DataLoader.loadIndividualTimeOffs(timeOffTypeService, individualTimeOffService);
        DATA_LOADED = true;

    }


}
