package persistence.jpa;

import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.repositories.CandidateRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * JPA implementation of the CandidateRepository interface.
 * This repository provides access to Candidate entities using JPA for persistence.
 *
 * @author Miguel Cardoso
 */
public class JpaCandidateRepository extends JpaAutoTxRepository<Candidate, TelephoneNumber, TelephoneNumber> implements CandidateRepository {

    /**
     * Constructs a new JpaCandidateRepository with the given persistence unit name.
     *
     * @param puname the name of the persistence unit.
     */
    public JpaCandidateRepository(String puname) {
        super(puname, "telephoneNumber");
    }

    /**
     * Constructs a new JpaCandidateRepository with the given transactional context.
     *
     * @param autoTx the transactional context to use for database operations.
     */
    public JpaCandidateRepository(TransactionalContext autoTx) {
        super(autoTx, "telephoneNumber");
    }

    /**
     * Retrieves all Candidate entities.
     *
     * @return an iterable collection of all Candidate entities.
     */
    @Override
    public Iterable<Candidate> allCandidates() {
        return findAll();
    }

    /**
     * Retrieves the Candidate associated with the given telephone number.
     *
     * @param telephoneNumber the telephone number of the candidate.
     * @return an {@link Optional} containing the candidate if found, or empty if not found.
     */
    @Override
    public Optional<Candidate> findByTelephoneNumber(final TelephoneNumber telephoneNumber) {
        final Map<String, Object> params = new HashMap<>();
        params.put("telephoneNumber", telephoneNumber);
        return matchOne("e.telephoneNumber=:telephoneNumber", params);
    }
}
