package core.repositories;

import core.domain.company.Company;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 * A repository for companies.
 *
 * @author Diogo Ribeiro
 */
public interface CompanyRepository extends DomainRepository<Integer, Company> {

    /**
     * Find a company by its ID.
     *
     * @param id the ID of the company to find
     * @return an {@link Optional} containing the company if found, or empty if not found
     */
    Optional<Company> findByCompanyID(Integer id);

    /**
     * Retrieve all companies.
     *
     * @return an iterable collection of all companies
     */
    Iterable<Company> allCompanies();
}
