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
 * @author 1220812@isep.ipp.pt
 */

public class JpaCompanyRepository extends JpaAutoTxRepository<Company, Integer, Integer> implements CompanyRepository{
    /**
     * Constructor with a transactional context.
     *
     * @param autoTx the transactional context to use for database operations
     */
    public JpaCompanyRepository(TransactionalContext autoTx) {
        super(autoTx, "companyNumber");
    }
    /**
     * Constructor with a persistence unit name.
     *
     * @param puname the name of the persistence unit
     */
    public JpaCompanyRepository(final String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "companyNumber");
    }
    /**
     * Find a company by its ID.
     *
     * @param id the ID of the company to find
     * @return an {@link Optional} containing the company if found, or empty if not found
     */
    @Override
    public Optional<Company> findByCompanyID(final Integer id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("company number", id);
        return matchOne("e.companyId=:companyId", params);
    }
    /**
     * Retrieve all companies.
     *
     * @return an iterable collection of all companies
     */
    @Override
    public Iterable<Company> allCompanies() {
        return findAll();
    }
}
