package attendance_manager.model;

public class EmployeeCSVDto {

    //region Properties
    private String name;

    private String reason;

    private String start;

    private String end;

    private String disposed;

    private String joined;

    private String leaved;

    private String username;

    private String password;
    //endregion

    //regoin Accessors
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(final String reason) {
        this.reason = reason;
    }

    public String getStart() {
        return start;
    }

    public void setStart(final String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(final String end) {
        this.end = end;
    }

    public String getDisposed() {
        return disposed;
    }

    public void setDisposed(final String disposed) {
        this.disposed = disposed;
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
    //endregion
}
