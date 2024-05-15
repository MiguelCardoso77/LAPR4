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
        JobOpening jobReference = jobOpenings.get(0);
        JobOpening jobReference1 = jobOpenings.get(1);

        ProcessState processState = ProcessState.APPLICATION;
        ProcessState processState1 = ProcessState.ANALYSIS;


        registerProcess(processState, jobReference);
        registerProcess(processState1, jobReference1);

        return true;
    }

    private void registerProcess(ProcessState processState, JobOpening jobReference){
        controller.registerProcess(processState,jobReference);
    }
}
