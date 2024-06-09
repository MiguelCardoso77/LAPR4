package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.domain.process.ProcessStatus;
import core.persistence.PersistenceContext;
import core.repositories.ProcessRepository;
import core.services.ProcessService;
/**
 * Controller for managing the status of a Process in the Jobs4U system.
 * This class provides methods to change the status of a Process, select a JobOpening, and change the state of a Process.
 * It uses the ProcessService and ProcessRepository from the core services and repositories.
 *
 * @author Diana Neves
 */
public class ChangeProcessStatusController {
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    final ChangeProcessStateController changeProcessStateController = new ChangeProcessStateController();
    final ProcessService processService = new ProcessService();
    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();
    /**
     * Changes the status of a Process.
     *
     * @param processStatus the new status of the Process
     * @param process the Process for which the status is to be changed
     * @return the updated Process
     */
    public Process changeProcessStatus(ProcessStatus processStatus, Process process) {
        processService.changeProcessStatus(processStatus, process);
        return processRepository.save(process);
    }
    /**
     * Selects a JobOpening.
     *
     * @return the selected JobOpening
     */
    public JobOpening selectJobOpening(){
        return selectJobOpeningController.selectJobOpening();
    }
    /**
     * Changes the state of a Process.
     *
     * @param processState the new state of the Process
     * @param process the Process for which the state is to be changed
     * @return the updated Process
     */
    public Process changeProcessState(ProcessState processState, Process process) {
        return changeProcessStateController.changeProcessState(processState, process);
    }
}
