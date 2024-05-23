package core.domain.interview.events;

import core.domain.interview.Score;

/**
 * Represents an event indicating the completion of the evaluation process for a job interview.
 * Contains information about the job interview ID and the new score assigned to the interview.
 *
 * @author 1220812@isep.ipp.pt
 */
public class EvaluationCompletedEvent {
    private final Integer jobInterviewID;
    private final Score newScore;

    /**
     * Constructs an EvaluationCompletedEvent with the specified job interview ID and new score.
     *
     * @param jobInterviewID The ID of the job interview for which the evaluation was completed.
     * @param newScore       The new score assigned to the job interview.
     */
    public EvaluationCompletedEvent(Integer jobInterviewID, Score newScore) {
        this.jobInterviewID = jobInterviewID;
        this.newScore = newScore;
    }
    /**
     * Retrieves the ID of the job interview associated with this event.
     *
     * @return The job interview ID.
     */
    public Integer getJobInterviewID() {
        return jobInterviewID;
    }
    /**
     * Retrieves the new score assigned to the job interview.
     *
     * @return The new score.
     */
    public Score getNewScore() {
        return newScore;
    }
}