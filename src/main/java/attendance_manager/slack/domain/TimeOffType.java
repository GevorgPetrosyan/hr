package attendance_manager.slack.domain;

public enum TimeOffType {
    DAY_OFF("DayOff"),
    FROM_HOME("Working from home"),
    VACATION("Vacation"),
    MATERNITY_LEAVE("Maternity leave"),
    MEDICAL_LEAVE("Medical leave"),
    UNPAID_TIME_OFF("Unpaid time off"),
    BUSINESS_TRIP("Business trip");

    private String name;

    public String getName() {
        return name;
    }

    TimeOffType(String name) {
        this.name = name;
    }

    public static TimeOffType fromString(String name) {
        for (TimeOffType timeOffType : TimeOffType.values()) {
            if (timeOffType.name.equalsIgnoreCase(name.trim())) {
                return timeOffType;
            }
        }
        return null;
    }
}
