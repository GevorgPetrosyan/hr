package attendance_manager.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@Entity
@Table(name = "employee")
public class Employee  extends User implements Serializable {

    @OneToOne
    private WorkingHoursScheme individualWorkingHoursScheme;


    public Employee() {
    }

    public Employee(String username,
            String password,
            Boolean enabled,
            List<Authority> grantedAuthorities,
            String ssn,
            WorkingHoursScheme individualWorkingHoursScheme) {
        super(username, password, enabled, grantedAuthorities, ssn);
        this.individualWorkingHoursScheme = individualWorkingHoursScheme;
    }

    public WorkingHoursScheme getIndividualWorkingHoursScheme() {
        return individualWorkingHoursScheme;
    }

    public void setIndividualWorkingHoursScheme(WorkingHoursScheme individualWorkingHoursScheme) {
        this.individualWorkingHoursScheme = individualWorkingHoursScheme;
    }
}
