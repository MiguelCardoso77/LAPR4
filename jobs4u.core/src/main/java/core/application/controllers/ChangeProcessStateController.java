package core.application.controllers;

import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.persistence.PersistenceContext;
import core.repositories.ProcessRepository;

public class ChangeProcessStateController {
    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();

    public Process changeProcessStatus(ProcessState processState, Process process) {
        process.changeProcessState(processState);
        return processRepository.save(process);
    }
}
