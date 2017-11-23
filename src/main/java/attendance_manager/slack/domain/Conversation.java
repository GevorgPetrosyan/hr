package attendance_manager.slack.domain;

import attendance_manager.slack.domain.answer.Answer;
import attendance_manager.slack.domain.conversation.ConversationStep;
import attendance_manager.slack.domain.conversationstep.ConversationInitialStep;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 3:26 PM.
 */
public class Conversation {

    private ConversationStep conversationStep;

    private Conversation() {
        this.conversationStep = new ConversationInitialStep();
    }

    public static Conversation start(final String message) {
        return new Conversation();
    }

    public void accept(final String message) {
        final Answer answer = conversationStep.getQuestion().answer(message);
        conversationStep = conversationStep.nextStep(answer);
    }

    public boolean isFinished() {
        return conversationStep.isFinished();
    }

    public String reply() {
        return conversationStep.getQuestion().getText();
    }
}
