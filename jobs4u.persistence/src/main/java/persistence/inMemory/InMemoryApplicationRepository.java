package persistence.inMemory;

import core.domain.application.Application;
import core.repositories.ApplicationRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

/**
 * @author 1220812@isep.ipp.pt
 */

public class InMemoryApplicationRepository extends InMemoryDomainRepository<Application, Integer> implements ApplicationRepository {

    /**
     * Static block to initialize the in-memory data store.
     */
    static {
        InMemoryInitializer.init();
    }
    /**
     * Retrieve all applications.
     *
     * @return an iterable collection of all applications
     */
    @Override
    public Iterable<Application> allApplications() {
        return match(e -> true);
    }

}