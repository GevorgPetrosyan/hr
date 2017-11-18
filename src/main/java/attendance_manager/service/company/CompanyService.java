package attendance_manager.service.company;

/**
 * @author Marta Ginosyan
 */

public interface CompanyService {

    CompanyConfigDTO getCompanyService();

    void saveCompanyConfig(CompanyConfigDTO companyConfigDTO);
}
