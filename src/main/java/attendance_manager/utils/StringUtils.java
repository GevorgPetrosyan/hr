package attendance_manager.utils;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */
public class StringUtils {

    public static final String EMPTY = "";

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    public static String nullToEmpty(String input) {
        return input == null ? "" : input;
    }

    public static boolean isNullOrEmpty(String... args) {
        for (String arg : args) {
            if (isNullOrEmpty(arg)) return true;
        }
        return false;
    }

    public static String correctWhiteSpaces(String input) {
        if (isNullOrEmpty(input)) return input;
        return input.replaceAll("\\s{2,}", " ").trim();
    }


}
