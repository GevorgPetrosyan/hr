package attendance_manager.slack.domain.conversation;

import attendance_manager.slack.domain.answer.Answer;
import attendance_manager.slack.domain.question.Question;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 3:05 PM.
 */
public interface ConversationStep<A extends  Answer> {

    ConversationStep nextStep(final A answer);

    Question getQuestion();

    boolean isFinished();
}
