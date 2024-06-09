package bootstrappers.bootstraping;

import core.application.controllers.AddCompanyController;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstraps company data.
 * This bootstrapper registers sample company names using the AddCompanyController.
 * Requires the AddCompanyController to execute.
 * This class is an Action to be used in the bootstrapping process.
 *
 * @author Diana Neves
 */
public class CompanyBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyBootstrapper.class);
    private final AddCompanyController controller = new AddCompanyController();

    /**
     * Executes the bootstrapping process for company data.
     * Registers sample company names using the AddCompanyController.
     * @return true if bootstrapping is successful, false otherwise
     */
    public boolean execute() {
        registerCompany("Porto");
        registerCompany("ISEP");
        registerCompany("FEUP");
        registerCompany("FMUP");
        registerCompany("IBM");

        return true;
    }

    /**
     * Registers a company name using the AddCompanyController.
     * @param companyName the name of the company to register
     */
    private void registerCompany(String companyName) {
        controller.addCompany(companyName);
        LOGGER.debug("»»» {}", companyName);
    }
}
