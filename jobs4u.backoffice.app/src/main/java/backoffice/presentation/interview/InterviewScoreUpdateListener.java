package backoffice.presentation.interview;

import core.domain.interview.events.EvaluationCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * This class is a listener for the EvaluationCompletedEvent.
 * It reacts when an EvaluationCompletedEvent is published, printing a message to the console.
 *
 * @author 1220812@isep.ipp.pt
 */
@Component
public class InterviewScoreUpdateListener {

    /**
     * This method is triggered when an EvaluationCompletedEvent is published.
     * It prints a message to the console with the ID of the job interview and the new score.
     *
     * @param event The EvaluationCompletedEvent that was published.
     */

    @EventListener
    public void onEvaluationCompletedEvent(EvaluationCompletedEvent event) {
        System.out.println("Evaluation completed for interview with ID " + event.getJobInterviewID() + ". New score: " + event.getNewScore());
    }
}
