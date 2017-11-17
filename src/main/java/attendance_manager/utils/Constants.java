package attendance_manager.utils;

import java.time.format.DateTimeFormatter;

/**
 * @author Marta Ginosyan
 * Date: 11/1/17
 */
public class Constants {

    public static final int VERIFICATION_TOKEN_EXPIRATION_MINUTES = 365 * 24 * 60;

    public static final int PASSWORD_RESET_TOKEN_EXPIRATION_MINUTES = 365 * 24 * 60;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-dd-MM");

}
