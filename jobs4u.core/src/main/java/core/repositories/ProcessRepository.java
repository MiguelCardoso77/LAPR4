package core.repositories;

import core.domain.process.Process;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * The repository for Processes
 *
 * @author Diana Neves
 */
public interface ProcessRepository extends DomainRepository<Integer, Process> {

    /**
     * Finds a process by its identity
     *
     * @param id the identity of the process
     * @return the process
     */
    @Override
    Optional<Process> ofIdentity(Integer id);

    /**
     * Returns all processes
     *
     * @return all processes
     */
    Iterable<Process> allProcesses();
}
