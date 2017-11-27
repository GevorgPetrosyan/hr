package attendance_manager.slack.domain.util;

import attendance_manager.slack.domain.SlackTimeOffType;
import attendance_manager.slack.domain.VacationRequest;
import attendance_manager.slack.domain.VacationRequestParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 4:49 PM.
 */
public class DefaultVacationRequestParser implements VacationRequestParser {
    private final Pattern pattern = Pattern.compile("(.*)(\\d{4}-\\d{2}-\\d{2})( - )(\\d{4}-\\d{2}-\\d{2})");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Override
    public VacationRequest from(final String text) {
        Matcher matcher = pattern.matcher(text);

        try {
            if (matcher.matches()){
               return new VacationRequest(SlackTimeOffType.fromString(matcher.group(1)),LocalDate.parse(matcher.group(2), DATE_TIME_FORMATTER),LocalDate.parse(matcher.group(4), DATE_TIME_FORMATTER));
            }
        }catch (Exception e){

        }

        return new VacationRequest(null,null,null);
    }
}
