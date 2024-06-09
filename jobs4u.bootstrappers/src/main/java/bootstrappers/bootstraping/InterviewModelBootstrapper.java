package bootstrappers.bootstraping;

import core.domain.interviewModel.InterviewModel;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InterviewModelBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewModelBootstrapper.class);

    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();

    @Override
    public boolean execute() {
        registerInterviewModel("jobs4u.core/src/main/resources/templates/interview/interview1.txt");
        registerInterviewModel("jobs4u.core/src/main/resources/templates/interview/interviewModel2.txt");
        registerInterviewModel("jobs4u.core/src/main/resources/templates/interview/interviewModel3.txt");

        return true;
    }

    private void registerInterviewModel(String interviewModelString) {
        InterviewModel interviewModel = new InterviewModel(interviewModelString);
        interviewModelRepository.save(interviewModel);
        LOGGER.debug("»»» {}", interviewModel);
    }
}
