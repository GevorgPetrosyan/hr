package attendance_manager.slack.domain.question;

import attendance_manager.slack.domain.answer.Answer;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 3:06 PM.
 */
public interface Question <A extends Answer>{

    String getText();

    A answer(String answer);
}
