package persistence.inMemory;

import core.domain.company.Company;
import core.repositories.CompanyRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryCompanyRepository extends InMemoryDomainRepository<Company, Integer> implements CompanyRepository {

    @Override
    public Optional<Company> findByCompanyID(Integer id) {
        return Optional.empty();
    }

    @Override
    public Iterable<Company> allCompanies() {
        return match(e -> true);
    }
}
