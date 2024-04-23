package persistence.jpa;

import core.domain.application.Application;
import core.domain.application.IdApplication;
import core.repositories.ApplicationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaApplicationRepository extends JpaAutoTxRepository<Application, IdApplication, IdApplication> implements ApplicationRepository {
    public JpaApplicationRepository(final TransactionalContext autoTx) {super(autoTx,"idApplication");}
    public JpaApplicationRepository(final String puname) {super(puname, "idApplication"); }
    @Override
    public Iterable<Application> allApplications() {
        return findAll();
    }
}
