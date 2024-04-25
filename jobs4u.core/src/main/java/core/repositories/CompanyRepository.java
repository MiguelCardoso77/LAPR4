package core.repositories;

import core.domain.company.Company;
import core.domain.company.CompanyNumber;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface CompanyRepository extends DomainRepository<CompanyNumber, Company> {
    default Optional<Company> findByCompanyNumber(final CompanyNumber number){
        return ofIdentity(number);
    }
    public Iterable<Company> allCompanies();
}
