package core.repositories;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.repositories.DomainRepository;

public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {
    Iterable<JobOpening> allJobOpenings();

}
