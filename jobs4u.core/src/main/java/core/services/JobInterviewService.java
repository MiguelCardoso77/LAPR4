package core.services;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.interview.JobInterviewBuilder;
import core.domain.interview.Score;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        jobInterviewBuilder.withAll(createdOn, time, score, result, application);
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

    public List<JobInterview> findInterviewsForApplication(Application application) {
        List<JobInterview> interviews = new ArrayList<>();

        for (JobInterview i : jobInterviewRepository.allJobInterviews()) {
            if (i.application().identity().equals(application.identity())) {
                interviews.add(i);
            }
        }

        return interviews;
    }

    /*public List<JobInterview> sortInterviewsDescending() {
        Comparator<JobInterview> byScoreDescending = Comparator.comparingInt(JobInterview::getScore)
                .reversed();

        List<JobInterview> sortedInterviews = jobInterviews.stream()
                .sorted(byScoreDescending)
                .collect(Collectors.toList());

        return sortedInterviews;
    }   return sortedInterviews;
    }*/
}
