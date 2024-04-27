package persistence.jpa;

import core.domain.company.Company;
import core.repositories.CompanyRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import infrastructure.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaCompanyRepository extends JpaAutoTxRepository<Company, Integer, Integer> implements CompanyRepository{
    public JpaCompanyRepository(TransactionalContext autoTx) {
        super(autoTx, "companyNumber");
    }

    public JpaCompanyRepository(final String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "companyNumber");
    }

    @Override
    public Optional<Company> findByCompanyID(final Integer id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("company number", id);
        return matchOne("e.companyId=:companyId", params);
    }

    @Override
    public Iterable<Company> allCompanies() {
        return findAll();
    }
}
