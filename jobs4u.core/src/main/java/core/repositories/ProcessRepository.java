package core.repositories;

import core.domain.process.Process;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ProcessRepository extends DomainRepository<Integer, Process> {

    @Override
    Optional<Process> ofIdentity(Integer id);

    Iterable<Process> allProcesses();
}
