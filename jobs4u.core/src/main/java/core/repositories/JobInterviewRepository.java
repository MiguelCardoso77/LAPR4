package core.repositories;

import core.domain.interview.JobInterview;
import eapli.framework.domain.repositories.DomainRepository;

public interface JobInterviewRepository extends DomainRepository<Integer, JobInterview> {
    Iterable<JobInterview> allJobInterviews();
}
