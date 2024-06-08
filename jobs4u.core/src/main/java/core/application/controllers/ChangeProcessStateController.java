package core.application.controllers;

import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.persistence.PersistenceContext;
import core.repositories.ProcessRepository;
import core.services.ProcessService;

public class ChangeProcessStateController {
    final ProcessService processService = new ProcessService();
    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();

    public Process changeProcessState(ProcessState processState, Process process) {
        processService.changeProcessState(processState, process);
        return processRepository.save(process);
    }
}
