package core.services;

import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.interview.JobInterviewBuilder;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class JobInterviewService {
    private final JobInterviewRepository jobInterviewRepository = PersistenceContext.repositories().jobInterviews();

    @Transactional
    public JobInterview registerJobInterview(int id, Calendar createdOn, int time, int score, String result,
                                             Application application, InterviewModel interviewModel) {
        JobInterviewBuilder jobInterviewBuilder = new JobInterviewBuilder();
        jobInterviewBuilder.withAll(id, createdOn, time, score, result, application, interviewModel);
        JobInterview jobInterview = jobInterviewBuilder.build();
        return jobInterviewRepository.save(jobInterview);
    }

    public Iterable<JobInterview> allJobInterviews() {
        return jobInterviewRepository.allJobInterviews();
    }
}
