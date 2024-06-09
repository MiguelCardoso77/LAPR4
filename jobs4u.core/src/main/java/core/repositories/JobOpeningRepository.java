package core.repositories;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * The repository for JobOpenings
 *
 * @author Miguel Cardoso
 */
public interface JobOpeningRepository extends DomainRepository<JobReference, JobOpening> {

    /**
     * Method that returns all the job openings
     *
     * @return an iterable with all the job openings
     */
    Iterable<JobOpening> allJobOpenings();
}