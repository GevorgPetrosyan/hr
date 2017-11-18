package attendance_manager.model;

public class EmployeeAttendanceDto {

    private String reason;

    private String start;

    private String end;

    private String disposed;

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
}
