package core.repositories;

import core.domain.interview.JobInterview;
import eapli.framework.domain.repositories.DomainRepository;

/**
 * Repository interface for managing {@link JobInterview} entities.
 * Extends {@link DomainRepository} with Integer as the identifier type
 * and {@link JobInterview} as the entity type.
 *
 * @author Diana Neves
 */
public interface JobInterviewRepository extends DomainRepository<Integer, JobInterview> {

    /**
     * Retrieves all job interviews from the repository.
     *
     * @return an iterable containing all job interviews in the repository
     */
    Iterable<JobInterview> allJobInterviews();
}
