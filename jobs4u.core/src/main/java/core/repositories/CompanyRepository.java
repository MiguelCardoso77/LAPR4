package core.repositories;

import core.domain.company.Company;
import core.domain.company.CompanyName;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface CompanyRepository extends DomainRepository<CompanyName, Company> {
    default Optional<Company> findByCompanyName(final CompanyName name){
        return ofIdentity(name);
    }

    public Iterable<Company> allCompanies();
}
