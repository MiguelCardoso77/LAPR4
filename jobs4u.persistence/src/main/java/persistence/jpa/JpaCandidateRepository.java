package persistence.jpa;

import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.repositories.CandidateRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaCandidateRepository extends JpaAutoTxRepository<Candidate, TelephoneNumber, TelephoneNumber> implements CandidateRepository {
    public JpaCandidateRepository(String puname) { super(puname, "telephoneNumber"); }

    public JpaCandidateRepository(TransactionalContext autoTx) { super(autoTx, "telephoneNumber"); }

    @Override
    public Iterable<Candidate> allCandidates() {
        return findAll();
    }

    /**
     * Retrieves the Candidate associated to the telephone number passed by parameter
     *
     * @param telephoneNumber candidate´s telephone number
     * @return candidate
     */

    @Override
    public Optional<Candidate> findByTelephoneNumber(final TelephoneNumber telephoneNumber){
        final Map<String, Object> params = new HashMap<>();
        params.put("telephoneNumber", telephoneNumber);
        return matchOne("e.telephoneNumber=:telephoneNumber", params);
    }
}
