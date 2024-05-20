package core.repositories;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.repositories.DomainRepository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


import java.util.Optional;

public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {


    Iterable<JobOpening> allJobOpenings();

}