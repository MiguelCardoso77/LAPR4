package persistence.jpa;

import core.domain.company.Company;
import core.domain.company.CompanyNumber;
import core.repositories.CompanyRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import infrastructure.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaCompanyRepository extends JpaAutoTxRepository<Company, CompanyNumber, CompanyNumber> implements CompanyRepository{
    public JpaCompanyRepository(TransactionalContext autoTx) {
        super(autoTx, "companyNumber");
    }
    public JpaCompanyRepository(final String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "companyNumber");
    }

    @Override
    public Optional<Company> findByCompanyNumber(final CompanyNumber companyNumber) {
        final Map<String, Object> params = new HashMap<>();
        params.put("companyNumber", companyNumber);
        return matchOne("e.companyNumber=:companyNumber", params);
    }

    @Override
    public Iterable<Company> allCompanies() {
        return null;
    }
}
