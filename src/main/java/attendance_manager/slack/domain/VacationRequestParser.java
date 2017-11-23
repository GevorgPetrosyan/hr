package attendance_manager.slack.domain;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 4:48 PM.
 */
public interface VacationRequestParser {

    VacationRequest from(final String text);
}
