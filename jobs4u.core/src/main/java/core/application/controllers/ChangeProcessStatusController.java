package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.domain.process.ProcessStatus;
import core.persistence.PersistenceContext;
import core.repositories.ProcessRepository;
import core.services.ProcessService;


public class ChangeProcessStatusController {
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    final ChangeProcessStateController changeProcessStateController = new ChangeProcessStateController();
    final ProcessService processService = new ProcessService();
    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();

    public Process changeProcessStatus(ProcessStatus processStatus, Process process) {
        processService.changeProcessStatus(processStatus, process);
        return processRepository.save(process);
    }

    public JobOpening selectJobOpening(){
        return selectJobOpeningController.selectJobOpening();
    }

    public Process changeProcessState(ProcessState processState, Process process) {
        return changeProcessStateController.changeProcessState(processState, process);
    }
}
