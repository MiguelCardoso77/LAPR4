package core.jobOpening.repositories;

import core.jobOpening.domain.JobOpening;
import core.jobOpening.domain.JobReference;
import eapli.framework.domain.repositories.DomainRepository;

public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {
    Iterable<JobOpening> allJobOpenings();
}
