package attendance_manager.domain;

import attendance_manager.converter.LocalDateAttributeConverter;
import attendance_manager.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends AbstractDomain implements UserDetails {

    // region Instance Fields
    @Column(name = "username", unique = true)
    @Email
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @JsonIgnoreProperties
    @Column(name = "non_expired")
    private Boolean accountNonExpired;

    @JsonIgnoreProperties
    @Column(name = "non_locked")
    private Boolean accountNonLocked;

    @JsonIgnoreProperties
    @Column(name = "credentials_non_expired")
    private Boolean credentialsNonExpired;

    @JsonIgnoreProperties
    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "approved")
    private Boolean approved;

    @Column(insertable = false, updatable = false)
    private String dtype;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(name = "leaving_date")
    private LocalDate leavingDate;


    @ManyToMany
    private List<Authority> grantedAuthorities;

    // endregion

    // region Constructors
    public User() {
    }

    public User(String ssn, String username, String password, String name, String phone, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, Boolean approved, String dtype, LocalDate joiningDate, LocalDate leavingDate, List<Authority> grantedAuthorities) {
        super(ssn);
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.approved = approved;
        this.dtype = dtype;
        this.joiningDate = joiningDate;
        this.leavingDate = leavingDate;
        this.grantedAuthorities = grantedAuthorities;
    }

    // endregion

    // region Transient methods
    public void correctStrings() {
        this.username = StringUtils.correctWhiteSpaces(username);
    }

    public void setRole(Authority grantedAuthority) {
        if (grantedAuthorities == null) grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);
    }
    // endregion

    // region Getters and Setters
    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(List<Authority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    // endregion

    // region Overrides
    @Override
    public String toString() {
        return "User [" +
                "id: " + id + "\t" +
                "username: " + username +
                "]";
    }
    // endregion
}
