package attendance_manager.slack.domain;

import java.time.LocalDate;

public class VacationRequest {

    private TimeOffType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String fullName;

    public TimeOffType getType() {
        return type;
    }

    public void setType(TimeOffType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "VacationRequest{" +
                "type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
