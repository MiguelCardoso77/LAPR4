package bootstrappers.bootstraping;

import core.application.controllers.AddProcessController;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.process.ProcessState;
import core.persistence.PersistenceContext;
import core.repositories.JobOpeningRepository;
import eapli.framework.actions.Action;

import java.util.List;

public class ProcessBootstrapper implements Action {
    final AddProcessController controller = new AddProcessController();
    final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();


    @Override
    public boolean execute() {
        List<JobOpening> jobOpenings = (List<JobOpening>) jobOpeningRepository.allJobOpenings();

        registerProcess(ProcessState.APPLICATION);
        registerProcess(ProcessState.ANALYSIS);
        registerProcess(ProcessState.INTERVIEWS);

        return true;
    }
    private void registerProcess(ProcessState processState) {
        controller.registerProcess(processState);
    }
}
