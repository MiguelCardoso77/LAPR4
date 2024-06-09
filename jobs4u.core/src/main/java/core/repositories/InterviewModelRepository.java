package core.repositories;

import core.domain.interviewModel.InterviewModel;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;
/**
 * Represents a repository for managing InterviewModel entities in the Jobs4U system.
 * This interface provides methods to find an InterviewModel by its ID and to retrieve all InterviewModels.
 * It extends the DomainRepository interface from the eapli framework.
 *
 * @author Diana Neves
 */
public interface InterviewModelRepository extends DomainRepository<Integer , InterviewModel> {
    /**
     * Returns all the InterviewModels in the repository.
     *
     * @return an iterable with all the InterviewModels
     */
    Iterable<InterviewModel> allInterviewModels();
    /**
     * Returns the InterviewModel with the given id.
     *
     * @param id the id of the InterviewModel
     * @return the InterviewModel with the given id
     */
    @Override
    Optional<InterviewModel> ofIdentity(Integer id);
}
