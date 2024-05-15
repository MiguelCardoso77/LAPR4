package persistence.jpa;

import core.domain.interview.JobInterview;
import core.repositories.JobInterviewRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaJobInterviewRepository extends JpaAutoTxRepository<JobInterview, Integer, Integer> implements JobInterviewRepository {

    public JpaJobInterviewRepository(String persistenceUnitName) {
        super(persistenceUnitName, "id");
    }

    public JpaJobInterviewRepository(TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    @Override
    public Iterable<JobInterview> allJobInterviews() {
        return findAll();
    }
}
