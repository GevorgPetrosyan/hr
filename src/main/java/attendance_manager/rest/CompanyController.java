package attendance_manager.rest;

import attendance_manager.service.company.CompanyConfigDTO;
import attendance_manager.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marta Ginosyan
 * Team: Lime
 * Project: greetz3-hr
 * Date: 11/18/17
 */
@RestController("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @RequestMapping(method = RequestMethod.GET)
    public CompanyConfigDTO getCompanyConfigs() {
        final CompanyConfigDTO companyConfig = this.companyService.getCompanyService();
        return companyConfig;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object createCompanyConfig(@RequestBody final CompanyConfigDTO companyConfigDTO) {
        companyService.saveCompanyConfig(companyConfigDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
