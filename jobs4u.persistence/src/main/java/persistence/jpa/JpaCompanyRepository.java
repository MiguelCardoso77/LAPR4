package persistence.jpa;

import core.domain.company.Company;
import core.repositories.CompanyRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import infrastructure.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * JPA implementation of the CompanyRepository interface.
 * This repository provides access to Company entities using JPA for persistence.
 *
 * @author Diogo Ribeiro
 */
public class JpaCompanyRepository extends JpaAutoTxRepository<Company, Integer, Integer> implements CompanyRepository {

    /**
     * Constructs a new JpaCompanyRepository with the given transactional context.
     *
     * @param autoTx the transactional context to use for database operations.
     */
    public JpaCompanyRepository(TransactionalContext autoTx) {
        super(autoTx, "companyNumber");
    }

    /**
     * Constructs a new JpaCompanyRepository with the given persistence unit name.
     *
     * @param puname the name of the persistence unit.
     */
    public JpaCompanyRepository(final String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "companyNumber");
    }

    /**
     * Finds a company by its ID.
     *
     * @param id the ID of the company to find.
     * @return an {@link Optional} containing the company if found, or empty if not found.
     */
    @Override
    public Optional<Company> findByCompanyID(final Integer id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("companyId", id);
        return matchOne("e.companyId=:companyId", params);
    }

    /**
     * Retrieves all companies.
     *
     * @return an iterable collection of all companies.
     */
    @Override
    public Iterable<Company> allCompanies() {
        return findAll();
    }

}
