package core.services;

import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.interview.JobInterviewBuilder;
import core.domain.interview.Score;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Calendar;
/**
 * Service class for handling job interview related operations.
 *
 * @author Diana Neves
 */
@Service
public class JobInterviewService {
    private final JobInterviewRepository jobInterviewRepository = PersistenceContext.repositories().jobInterviews();

    /**
     * Registers a new job interview with the provided details.
     *
     * @param createdOn   The creation date of the job interview.
     * @param time        The duration of the interview.
     * @param score       The score of the interview.
     * @param result      The result of the interview.
     * @param application The application associated with the interview.
     * @return The registered job interview.
     */
    @Transactional
    public JobInterview registerJobInterview(Calendar createdOn, int time, int score, String result,
                                             Application application) {
        JobInterviewBuilder jobInterviewBuilder = new JobInterviewBuilder();
        jobInterviewBuilder.withAll(createdOn, time, score, result, application, null);
        JobInterview jobInterview = jobInterviewBuilder.build();
        return jobInterviewRepository.save(jobInterview);
    }

    /**
     * Retrieves all job interviews.
     *
     * @return An iterable collection of all job interviews.
     */
    public Iterable<JobInterview> allJobInterviews() {
        return jobInterviewRepository.allJobInterviews();
    }

    /**
     * Updates the interview model for a specific job interview identified by its ID.
     *
     * @param interviewModelToChange The new interview model to be set.
     * @param id                     The ID of the job interview to be updated.
     * @return The updated job interview, or null if no job interview with the given ID is found.
     */
    @Transactional
    public JobInterview updateInterviewModel(InterviewModel interviewModelToChange, Integer id) {
        JobInterview jobInterview = jobInterviewRepository.ofIdentity(id).orElse(null);
        if (jobInterview != null) {
            jobInterview.updateInterviewModel(interviewModelToChange);
            jobInterviewRepository.save(jobInterview);
            return jobInterview;
        }
        return null;
    }
    /**
     * Updates the score for a specific job interview identified by its ID.
     *
     * @param newScore          The new score to be set.
     * @param interviewID       The ID of the job interview to be updated.
     * @return The updated job interview, or null if no job interview with the given ID is found.
     */
    @Transactional
    public JobInterview updateInterviewScore(Score newScore, Integer interviewID){
        JobInterview jobInterview = jobInterviewRepository.ofIdentity(interviewID).orElse(null);
        if(jobInterview != null){
            jobInterview.updateScore(newScore);
            jobInterviewRepository.save(jobInterview);
            return jobInterview;
        }
        return null;
    }
}
