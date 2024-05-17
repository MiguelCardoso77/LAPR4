package core.services;

import core.domain.application.Application;
import core.domain.interview.InterviewModel;
import core.domain.interview.JobInterview;
import core.domain.interview.JobInterviewBuilder;
import core.domain.jobOpening.JobReference;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class JobInterviewService {
    private final JobInterviewRepository jobInterviewRepository = PersistenceContext.repositories().jobInterviews();

    @Transactional
    public JobInterview registerJobInterview(Calendar createdOn, int time, int score, String result,
                                             Application application) {
        JobInterviewBuilder jobInterviewBuilder = new JobInterviewBuilder();
        jobInterviewBuilder.withAll(createdOn, time, score, result, application, null);
        JobInterview jobInterview = jobInterviewBuilder.build();
        return jobInterviewRepository.save(jobInterview);
    }

    public Iterable<JobInterview> allJobInterviews() {
        return jobInterviewRepository.allJobInterviews();
    }


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
}
