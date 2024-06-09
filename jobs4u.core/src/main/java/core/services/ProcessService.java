package core.services;

import core.domain.process.Process;
import core.domain.process.ProcessBuilder;
import core.domain.process.ProcessState;
import core.domain.process.ProcessStatus;
import core.persistence.PersistenceContext;
import core.repositories.ProcessRepository;
import jakarta.transaction.Transactional;

/**
 * Service class for managing processes.
 *
 * @author Diana Neves
 */
public class ProcessService {

    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();

    /**
     * Registers a new process with the given state and an initial status of OPEN.
     *
     * @param processState the initial state of the process.
     * @return the saved process.
     */
    @Transactional
    public Process registerProcess(ProcessState processState){
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.withAll(processState, ProcessStatus.OPEN);
        Process process = processBuilder.build();
        return processRepository.save(process);
    }

    /**
     * Finds a process by its ID.
     *
     * @param id the ID of the process to find.
     * @return the process with the given ID, or null if not found.
     */
    public Process findProcessByID(Integer id){
        Iterable<Process> processes = processRepository.allProcesses();
        for (Process process : processes){
            if (process.identity().equals(id)){
                return process;
            }
        }
        return null;
    }

    /**
     * Retrieves all processes.
     *
     * @return an iterable collection of all processes.
     */
    public Iterable<Process> allProcesses() { return processRepository.findAll(); }

    /**
     * Changes the status of a process.
     *
     * @param processStatus the new status for the process.
     * @param process the process whose status is to be changed.
     */
    public void changeProcessStatus(ProcessStatus processStatus, Process process){
        process.changeProcessStatus(processStatus);
    }

    /**
     * Changes the state of the process.
     *
     * @param processState the new state for the process.
     * @param process the process whose state is to be changed.
     */
    public void changeProcessState(ProcessState processState, Process process){
        process.changeProcessState(processState);
    }
}
