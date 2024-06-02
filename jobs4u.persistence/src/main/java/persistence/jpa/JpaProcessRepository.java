package persistence.jpa;

import core.domain.process.Process;
import core.repositories.ProcessRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaProcessRepository extends JpaAutoTxRepository<Process, Integer, Integer> implements ProcessRepository {
    public JpaProcessRepository(String persistenceUnitName) {
        super(persistenceUnitName, "idProcess");
    }

    public JpaProcessRepository(TransactionalContext tx) {
        super(tx, "idProcess");
    }

    @Override
    public Iterable<Process> allProcesses() {
        return null;
    }

}
