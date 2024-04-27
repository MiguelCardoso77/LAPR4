package core.repositories;

import core.domain.company.Company;
import core.domain.company.CompanyName;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface CompanyRepository extends DomainRepository<Integer, Company> {

    Optional<Company> findByCompanyID(Integer id);

    public Iterable<Company> allCompanies();
}
