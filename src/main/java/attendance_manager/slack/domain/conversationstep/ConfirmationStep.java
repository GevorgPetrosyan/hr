package attendance_manager.slack.domain.conversationstep;

import attendance_manager.slack.domain.answer.Answer;
import attendance_manager.slack.domain.conversation.ConversationStep;
import attendance_manager.slack.domain.question.Question;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 5:04 PM.
 */
public abstract class ConfirmationStep implements ConversationStep<ConfirmationStep.ConfirmationStepAnswer> {


    private ConfirmationStepQuestion question;

    public ConfirmationStep(final String message) {
        this.question = new ConfirmationStepQuestion(message);
    }


    public abstract ConversationStep nextStep(final ConfirmationStepAnswer answer);

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private static class ConfirmationStepQuestion implements Question {

        private final String message;

        private ConfirmationStepQuestion(final String message) {
            this.message = message;
        }

        @Override
        public String getText() {
            return message;
        }

        @Override
        public ConfirmationStepAnswer answer(final String answer) {
            return new ConfirmationStep.ConfirmationStepAnswer("yes".equalsIgnoreCase(answer));
        }
    }

    static class ConfirmationStepAnswer implements Answer {

        private final boolean accepted;

        ConfirmationStepAnswer(final boolean accepted) {
            this.accepted = accepted;
        }

        public boolean isAccepted() {
            return accepted;
        }
    }
}
