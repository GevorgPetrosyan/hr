package attendance_manager.slack.domain.util;

import attendance_manager.slack.domain.VacationRequest;
import attendance_manager.slack.domain.VacationRequestParser;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 5:06 PM.
 */
public class YesNoParser  {

    private static final Set<String> YES_NO_VALUES;

    static {
        final Set<String> yesNoValues = new HashSet<>();
        yesNoValues.add("y");
        yesNoValues.add("yes");
        yesNoValues.add("n");
        yesNoValues.add("no");
        YES_NO_VALUES = Collections.unmodifiableSet(yesNoValues);
    }
}
