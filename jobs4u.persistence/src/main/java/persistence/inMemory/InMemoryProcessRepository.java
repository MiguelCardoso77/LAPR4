package persistence.inMemory;

import core.domain.process.Process;
import core.repositories.ProcessRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryProcessRepository extends InMemoryDomainRepository<Process, Integer> implements ProcessRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Process> allProcesses() {
        return match(e -> true);
    }
}
