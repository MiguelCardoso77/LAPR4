package persistence.jpa;

import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.repositories.CandidateRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaCandidateRepository extends JpaAutoTxRepository<Candidate, TelephoneNumber, TelephoneNumber> implements CandidateRepository {
    public JpaCandidateRepository(String puname) { super(puname, "telephoneNumber"); }

    public JpaCandidateRepository(TransactionalContext autoTx) { super(autoTx, "telephoneNumber"); }

    @Override
    public Iterable<Candidate> allCandidates() {
        return findAll();
    }
}
