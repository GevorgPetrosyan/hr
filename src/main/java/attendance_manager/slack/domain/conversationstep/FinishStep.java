package attendance_manager.slack.domain.conversationstep;

import attendance_manager.slack.domain.answer.Answer;
import attendance_manager.slack.domain.conversation.ConversationStep;
import attendance_manager.slack.domain.question.Question;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 5:36 PM.
 */
public class FinishStep implements ConversationStep {

    private final Question question;

    FinishStep() {
        this.question = new FinishStepQuestion();
    }

    @Override
    public ConversationStep nextStep(final Answer answer) {
        return FinishStep.this;
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    private static class FinishStepQuestion implements Question {

        private FinishStepQuestion() {
        }

        @Override
        public String getText() {
            return "Well done, see you.";
        }

        @Override
        public Answer answer(final String answer) {
            return null;
        }
    }

}
