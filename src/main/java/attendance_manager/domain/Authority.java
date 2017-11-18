package attendance_manager.domain;

import attendance_manager.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */
@Entity
@Table(name = "authority")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Authority extends AbstractDomain implements GrantedAuthority {

    // region Instance Fields
    @Column(name = "role",
            nullable = false)
    private String role;
    // endregion

    // region Constructors
    public Authority() {
    }

    public Authority(String ssn, String role) {
        super(ssn);
        this.role = role;
    }

    // endregion

    // region Transient methods
    public void correctStrings() {
        this.role = StringUtils.correctWhiteSpaces(role);
    }
    // endregion

    // region Getters and Setters
    @Override
    public String getAuthority() {
        return "ROLE_" + role;
    }

    public void setAuthority(String role) {
        this.role = role;
    }
    // endregion

    // region Overrides
    @Override
    public String toString() {
        return "Authority [" +
                "id: " + id + "\t" +
                "title: " + role +
                "]";
    }
    // endregion
}