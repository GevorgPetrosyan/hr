package attendance_manager.slack.domain;

public enum SlackTimeOffType {
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

    SlackTimeOffType(String name) {
        this.name = name;
    }

    public static SlackTimeOffType fromString(String name) {
        for (SlackTimeOffType timeOffType : SlackTimeOffType.values()) {
            if (timeOffType.name.equalsIgnoreCase(name.trim())) {
                return timeOffType;
            }
        }
        return null;
    }
}
