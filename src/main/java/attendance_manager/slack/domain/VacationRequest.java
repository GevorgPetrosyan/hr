package attendance_manager.slack.domain;

import java.time.LocalDate;

public class VacationRequest {

    private SlackTimeOffType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private String fullName;

    public SlackTimeOffType getType() {
        return type;
    }

    public void setType(SlackTimeOffType type) {
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
