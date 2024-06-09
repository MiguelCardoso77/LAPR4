package core.services;

import core.domain.interviewModel.InterviewModel;
import core.domain.interviewModel.InterviewModelBuilder;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service class for managing InterviewModel entities.
 * This service provides methods to retrieve all interview models,
 * find interview models by their ID, and register new interview models.
 *
 * @author Diana Neves
 */
@Service
public class InterviewModelService {
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();

    /**
     * Retrieves all interview models.
     *
     * @return an iterable collection of allInterviewModel entities
     */
    public Iterable<InterviewModel> allInterviewModels() {
        return interviewModelRepository.allInterviewModels();
    }

    /**
     * Finds an interview model by its ID.
     *
     * @param id the ID of the interview model
     * @return the InterviewModel with the specified ID
     * @throws IllegalArgumentException if no interview model with the specified ID is found
     */
    public InterviewModel findById(int id) {
        return interviewModelRepository.ofIdentity(id).orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Registers a new interview model.
     *
     * @param model the model description
     * @return the newly registered InterviewModel
     */
    @Transactional
    public InterviewModel registerInterviewModel(String model) {
        InterviewModelBuilder interviewModelBuilder = new InterviewModelBuilder();
        interviewModelBuilder.withoutId(model);
        InterviewModel interviewModel = interviewModelBuilder.build();
        return interviewModelRepository.save(interviewModel);
    }
}
