package core.application.controllers;

import core.domain.process.Process;
import core.domain.process.ProcessStatus;
import core.persistence.PersistenceContext;
import core.repositories.ProcessRepository;


public class ChangeProcessStatusController {

    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();

    public Process changeProcessStatus(ProcessStatus processStatus, Process process) {
        process.changeProcessStatus(processStatus);
        return processRepository.save(process);
    }
}
