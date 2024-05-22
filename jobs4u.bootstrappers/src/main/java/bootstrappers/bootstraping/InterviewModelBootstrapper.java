package bootstrappers.bootstraping;

import core.domain.interview.InterviewModel;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import eapli.framework.actions.Action;

public class InterviewModelBootstrapper implements Action {
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();

    @Override
    public boolean execute() {
        registerInterviewModel("jobs4u.core/src/main/resources/interviewModels/interviewModel1.txt");
        registerInterviewModel("jobs4u.core/src/main/resources/interviewModels/interviewModel2.txt");
        registerInterviewModel("jobs4u.core/src/main/resources/interviewModels/interviewModel3.txt");

        return true;
    }

    private void registerInterviewModel(String interviewModelString) {
        InterviewModel interviewModel = new InterviewModel(interviewModelString);
        interviewModelRepository.save(interviewModel);
    }
}
