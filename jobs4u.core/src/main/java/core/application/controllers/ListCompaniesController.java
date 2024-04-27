package core.application.controllers;

import core.domain.company.Company;
import core.domain.user.Jobs4URoles;
import core.services.CompanyService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * A controller class for listing companies.
 */
public class ListCompaniesController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CompanyService companyService = new CompanyService();
    /**
     * Finds a company with the given company number.
     *
     * @param companyNumber the company number to search for
     * @return the found company, or null if not found
     */
    public Company findCompany(int companyNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<Company> allCompanies = companyService.allCompanies();
        for (Company c : allCompanies) {
            if (c.identity()==(companyNumber)) {
                return c;
            }
        }
        return null;
    }
    /**
     * Retrieves all companies.
     *
     * @return an iterable of all companies
     */
    public Iterable<Company> allCompanies() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        return companyService.allCompanies();
    }
}
