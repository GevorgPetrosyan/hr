package attendance_manager.slack.domain;

import java.time.LocalDate;

public class VacationRequest {

    private final TimeOffType type;

    private final LocalDate startDate;

    private final LocalDate endDate;

    public VacationRequest(final TimeOffType type, final LocalDate startDate, final LocalDate endDate) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TimeOffType getType() {
        return type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
