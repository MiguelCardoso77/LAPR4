package core.repositories;

import core.domain.application.Application;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * Represents a repository for managing Application entities in the Jobs4U system.
 * This interface provides methods to find an application by its ID and to retrieve all applications.
 * It extends the DomainRepository interface from the eapli framework.
 *
 * @author 1220812@isep.ipp.pt
 */
public interface ApplicationRepository extends DomainRepository<Integer ,Application> {
    /**
     * Find an application by its ID.
     *
     * @param id the ID of the application to find
     * @return an {@link Optional} containing the application if found, or empty if not found
     */
    @Override
    Optional<Application> ofIdentity(Integer id);

    /**
     * Retrieve all applications.
     *
     * @return an iterable collection of all applications
     */
    Iterable<Application> allApplications();
}