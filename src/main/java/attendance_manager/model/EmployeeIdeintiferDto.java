package attendance_manager.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class EmployeeIdeintiferDto {

    private String name;

    private String joined;

    private String leaved;

    private String username;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getJoined() {
        return joined;
    }

    public void setJoined(final String joined) {
        this.joined = joined;
    }

    public String getLeaved() {
        return leaved;
    }

    public void setLeaved(final String leaved) {
        this.leaved = leaved;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        final EmployeeIdeintiferDto that = (EmployeeIdeintiferDto) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(joined, that.joined)
                .append(leaved, that.leaved)
                .append(username, that.username)
                .append(password, that.password)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(joined)
                .append(leaved)
                .append(username)
                .append(password)
                .toHashCode();
    }

}
