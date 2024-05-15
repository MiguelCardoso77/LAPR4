package core.repositories;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {
    @Override
    Optional<JobOpening> ofIdentity(JobReference id);

    Iterable<JobOpening> allJobOpenings();

}
