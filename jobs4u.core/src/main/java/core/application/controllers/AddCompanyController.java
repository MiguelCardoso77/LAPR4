package core.application.controllers;

import core.domain.company.Company;
import core.services.CompanyService;
import eapli.framework.application.UseCaseController;
/**
 * A controller class for adding a new company.
 */
@UseCaseController
public class AddCompanyController {
    private final CompanyService companyService = new CompanyService();
    /**
     * Adds a new company with the specified name.
     *
     * @param companyName   the company name
     * @return the added company
     */
    public Company addCompany(String companyName){
        return companyService.registerCompany(companyName);
    }
}
