package attendance_manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@Entity
@Table(name = "employee")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee  extends User implements Serializable {

    @OneToOne
    private WorkingHoursScheme individualWorkingHoursScheme;


    public Employee() {
    }

    public Employee(String ssn, String username, String password, String name, String phone, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, Boolean approved, String dtype, LocalDate joiningDate, LocalDate leavingDate, List<Authority> grantedAuthorities, WorkingHoursScheme individualWorkingHoursScheme) {
        super(ssn, username, password, name, phone, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, approved, dtype, joiningDate, leavingDate, grantedAuthorities);
        this.individualWorkingHoursScheme = individualWorkingHoursScheme;
    }

    public WorkingHoursScheme getIndividualWorkingHoursScheme() {
        return individualWorkingHoursScheme;
    }

    public void setIndividualWorkingHoursScheme(WorkingHoursScheme individualWorkingHoursScheme) {
        this.individualWorkingHoursScheme = individualWorkingHoursScheme;
    }
}
