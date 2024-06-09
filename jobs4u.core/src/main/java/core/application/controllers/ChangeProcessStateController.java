package core.application.controllers;

import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.persistence.PersistenceContext;
import core.repositories.ProcessRepository;
import core.services.ProcessService;
/**
 * Controller for managing the state of a Process in the Jobs4U system.
 * This class provides a method to change the state of a Process.
 * It uses the ProcessService and ProcessRepository from the core services and repositories.
 *
 * @author Diana Neves
 */
public class ChangeProcessStateController {
    final ProcessService processService = new ProcessService();
    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();
    /**
     * Changes the state of a Process.
     *
     * @param processState the new state of the Process
     * @param process the Process for which the state is to be changed
     * @return the updated Process
     */
    public Process changeProcessState(ProcessState processState, Process process) {
        processService.changeProcessState(processState, process);
        return processRepository.save(process);
    }
}
