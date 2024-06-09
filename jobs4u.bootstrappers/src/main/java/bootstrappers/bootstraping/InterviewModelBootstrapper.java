package bootstrappers.bootstraping;

import core.domain.interviewModel.InterviewModel;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstraps interview model data.
 * This bootstrapper registers sample interview models using the InterviewModelRepository.
 * Requires the InterviewModelRepository to execute.
 * This class is an Action to be used in the bootstrapping process.
 *
 * @author Miguel Cardoso
 */
public class InterviewModelBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewModelBootstrapper.class);
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();

    /**
     * Executes the bootstrapping process for interview model data.
     * Registers sample interview models using the InterviewModelRepository.
     * @return true if bootstrapping is successful, false otherwise
     */
    @Override
    public boolean execute() {
        registerInterviewModel("jobs4u.core/src/main/resources/templates/interview/interview1.txt");
        registerInterviewModel("jobs4u.core/src/main/resources/templates/interview/interviewModel2.txt");
        registerInterviewModel("jobs4u.core/src/main/resources/templates/interview/interviewModel3.txt");

        return true;
    }

    /**
     * Registers an interview model using the InterviewModelRepository.
     * @param interviewModelString the interview model to be registered
     */
    private void registerInterviewModel(String interviewModelString) {
        InterviewModel interviewModel = new InterviewModel(interviewModelString);
        interviewModelRepository.save(interviewModel);
        LOGGER.debug("»»» {}", interviewModel);
    }
}
