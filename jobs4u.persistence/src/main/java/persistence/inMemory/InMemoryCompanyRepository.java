package persistence.inMemory;

import core.domain.company.Company;
import core.repositories.CompanyRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

/**
 * @author 1220812@isep.ipp.pt
 */

public class InMemoryCompanyRepository extends InMemoryDomainRepository<Company, Integer> implements CompanyRepository {

    /**
     * Static block to initialize the in-memory data store.
     */

    static {
        InMemoryInitializer.init();
    }

    /**
     * Find a company by its ID.
     *
     * @param companyID the ID of the company to find
     * @return an {@link Optional} containing the company if found, or empty if not found
     */

    @Override
    public Optional<Company> findByCompanyID(final Integer companyID){
        return Optional.of(data().get(companyID));
    }
    /**
     * Retrieve all companies.
     *
     * @return an iterable collection of all companies
     */
    @Override
    public Iterable<Company> allCompanies(){
        return match(e -> true);
    }
}
