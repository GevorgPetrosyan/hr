package attendance_manager.slack.domain.conversationstep;

import attendance_manager.slack.domain.VacationRequest;
import attendance_manager.slack.domain.VacationRequestParser;
import attendance_manager.slack.domain.answer.Answer;
import attendance_manager.slack.domain.conversation.ConversationStep;
import attendance_manager.slack.domain.question.Question;
import attendance_manager.slack.domain.util.DefaultVacationRequestParser;

/**
 * Created by Aram Martirosyan.
 * Company: SFL LLC.
 * Date: 11/18/17.
 * Time: 4:30 PM.
 */
public class ConversationInitialStep implements ConversationStep<ConversationInitialStep.DateAndTypeAnswer> {

    private final Question question;

    private int retryCount;
    public ConversationInitialStep() {
        this.question = new ConversationInitialStepQuestion();
    }

    @Override
    public ConversationStep nextStep(final DateAndTypeAnswer answer) {
//        if (!answer.isStartDatePresent() && !answer.isEndDatePresent()) {
//            return null;
//        }
//        if (!answer.isStartDatePresent()) {
//            return null;
//        }
//        if (!answer.isEndDatePresent()) {
//            return null;
//        }
//        if (!answer.isTypePresent()) {
//            return null;
//        }
        if (!answer.isStartDatePresent() || !answer.isEndDatePresent() || !answer.isTypePresent()){
            if (retryCount >= 5){
                return new FinishStep("Please contact with hr manager.");
            }
            retryCount++;
            return ConversationInitialStep.this;
        }
        return new ConfirmationStep(String.format("You mean %s %s - %s?", answer.type(), answer.start(), answer.end())) {
            @Override
            public ConversationStep nextStep(final ConfirmationStepAnswer answer) {
                return answer.isAccepted() ? new FinishStep (): ConversationInitialStep.this;
            }
        };
    }

    @Override
    public Question getQuestion() {
        return question;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private static class ConversationInitialStepQuestion implements Question {

        private final VacationRequestParser vacationRequestParser;

        private ConversationInitialStepQuestion() {
            this.vacationRequestParser = new DefaultVacationRequestParser();
        }

        @Override
        public String getText() {
            return "Please provide info about vacation.(e.g. DayOff 2017-11-30 - 2017-12-01)";
        }

        @Override
        public DateAndTypeAnswer answer(final String answer) {
            return new DateAndTypeAnswer(vacationRequestParser.from(answer));
        }
    }

    static class DateAndTypeAnswer implements Answer {

        private final VacationRequest vacationRequest;

        private DateAndTypeAnswer(final VacationRequest vacationRequest) {
            this.vacationRequest = vacationRequest;
        }

        boolean isStartDatePresent() {
            return vacationRequest.getStartDate() != null;
        }

        boolean isEndDatePresent() {
            return vacationRequest.getEndDate() != null;
        }

        boolean isTypePresent() {
            return vacationRequest.getType() != null;
        }

        String type() {
            return vacationRequest.getType().name();
        }

        String start() {
            return vacationRequest.getStartDate().toString();
        }

        String end() {
            return vacationRequest.getEndDate().toString();
        }
    }
}
