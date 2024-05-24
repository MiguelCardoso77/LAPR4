package core.services;

import core.domain.process.Process;
import core.domain.process.ProcessBuilder;
import core.domain.process.ProcessState;
import core.domain.process.ProcessStatus;
import core.persistence.PersistenceContext;
import core.repositories.ProcessRepository;
import jakarta.transaction.Transactional;

public class ProcessService {

    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();

    @Transactional
    public Process registerProcess(ProcessState processState){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.withAll(processState, ProcessStatus.OPEN);
        Process process = processBuilder.build();
        return processRepository.save(process);
    }

    public Process findProcessByID(Integer id){
        Iterable<Process> processes = processRepository.allProcesses();
        for (Process process : processes){
            if (process.identity().equals(id)){
                return process;
            }
        }
        return null;
    }

    public Iterable<Process> allProcesses() { return processRepository.findAll(); }
}
