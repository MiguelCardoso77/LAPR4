package persistence.jpa;

import core.domain.company.Company;
import core.domain.company.CompanyName;
import core.repositories.CompanyRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import infrastructure.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaCompanyRepository extends JpaAutoTxRepository<Company, CompanyName, CompanyName> implements CompanyRepository{
    public JpaCompanyRepository(TransactionalContext autoTx) {
        super(autoTx, "companyNumber");
    }

    public JpaCompanyRepository(final String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "companyNumber");
    }

    @Override
    public Optional<Company> findByCompanyName(final CompanyName companyName) {
        final Map<String, Object> params = new HashMap<>();
        params.put("companyName", companyName);
        return matchOne("e.companyName=:companyName", params);
    }

    @Override
    public Iterable<Company> allCompanies() {
        return findAll();
    }
}
