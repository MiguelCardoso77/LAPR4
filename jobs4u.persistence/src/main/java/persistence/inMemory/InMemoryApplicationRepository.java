package persistence.inMemory;

import core.domain.application.Application;
import core.domain.application.IdApplication;
import core.repositories.ApplicationRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryApplicationRepository extends InMemoryDomainRepository<Application, IdApplication> implements ApplicationRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Application> allApplications() {
        return match(e -> true);
    }

}