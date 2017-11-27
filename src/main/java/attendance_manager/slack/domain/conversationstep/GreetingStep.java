package attendance_manager.slack.domain.conversationstep;

import attendance_manager.slack.domain.conversation.ConversationStep;
import attendance_manager.slack.domain.question.Question;

public class GreetingStep implements ConversationStep<ConfirmationStep.ConfirmationStepAnswer> {
    private final Question question;

    public GreetingStep() {
        super();
        this.question = new GreetingStepQuestion();
    }

    @Override
    public ConversationStep nextStep(ConfirmationStep.ConfirmationStepAnswer answer) {
        if (answer.isAccepted()){
            return new ConversationInitialStep();
        }
        return new FinishStep();
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private static class GreetingStepQuestion implements Question {

        private GreetingStepQuestion() {
        }

        @Override
        public String getText() {
            return "Hello, I am hr, Would you like to go vacation?";
        }

        @Override
        public ConfirmationStep.ConfirmationStepAnswer answer(final String answer) {
            return new ConfirmationStep.ConfirmationStepAnswer("yes".equalsIgnoreCase(answer));
        }
    }
}
