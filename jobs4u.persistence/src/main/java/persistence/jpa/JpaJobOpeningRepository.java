package persistence.jpa;

import core.jobOpening.domain.JobOpening;
import core.jobOpening.domain.JobReference;
import core.jobOpening.repositories.JobOpeningRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

class JpaJobOpeningRepository extends JpaAutoTxRepository<JobOpening, JobReference, JobReference> implements JobOpeningRepository {

    public JpaJobOpeningRepository(final TransactionalContext autoTx) {
        super(autoTx, "jobReference");
    }

    public JpaJobOpeningRepository(final String puname) {
        super(puname, "jobReference");
    }

    @Override
    public Iterable<JobOpening> allJobOpenings() {
        return findAll();
    }

}
