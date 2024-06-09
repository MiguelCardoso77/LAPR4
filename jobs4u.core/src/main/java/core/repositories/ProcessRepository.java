package core.repositories;

import core.domain.process.Process;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;
/**
 * Represents a repository for managing Process entities in the Jobs4U system.
 * This interface provides methods to find a process by its ID and to retrieve all processes.
 * It extends the DomainRepository interface from the eapli framework.
 *
 * @author Diana Neves
 */
public interface ProcessRepository extends DomainRepository<Integer, Process> {
    /**
     * Find a process by its ID.
     *
     * @param id the ID of the process to find
     * @return an {@link Optional} containing the process if found, or empty if not found
     */
    @Override
    Optional<Process> ofIdentity(Integer id);
    /**
     * Retrieve all processes.
     *
     * @return an iterable collection of all processes
     */
    Iterable<Process> allProcesses();
}
