package core.application.controllers;

import core.domain.company.Company;
import core.domain.user.Jobs4URoles;
import core.services.CompanyService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * A controller class for adding a new company.
 *
 * @author 1220812@isep.ipp.pt
 */
@UseCaseController
public class AddCompanyController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CompanyService companyService = new CompanyService();
    /**
     * Adds a new company with the specified name.
     *
     * @param companyName   the company name
     * @return the added company
     */
    public Company addCompany(String companyName){
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.BOOTSTRAP);

        return companyService.registerCompany(companyName);
    }
}