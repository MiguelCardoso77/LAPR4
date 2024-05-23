package core.services;

import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.domain.interview.events.EvaluationCompletedEvent;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;
import eapli.framework.validations.Preconditions;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;

/**
 * Service to update the Job interview score
 *
 * @author 1220812@isep.ipp.pt
 */

public class InterviewScoreUpdateService {
    private final JobInterviewRepository repository = PersistenceContext.repositories().jobInterviews();
    private final ApplicationEventPublisher eventPublisher;

    public InterviewScoreUpdateService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    /**
     * Updates the job interview score.
     *
     * @param jobInterviewID The id of the interview to be updated.
     * @param newScore The new score to be set.
     */
    @Transactional
    public void interviewScoreUpdate(Integer jobInterviewID, Score newScore){
        Preconditions.nonNull(newScore, "Score cannot be null");
        JobInterview jobInterview = repository.ofIdentity(jobInterviewID).orElseThrow(() -> new IllegalArgumentException("Invalid interview id"));
        jobInterview.updateScore(newScore);
        repository.save(jobInterview);
        EvaluationCompletedEvent event = new EvaluationCompletedEvent(jobInterviewID, newScore);
        eventPublisher.publishEvent(event);
    }
}
