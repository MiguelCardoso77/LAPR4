package bootstrappers.bootstraping;

import core.application.controllers.AddCompanyController;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyBootstrapper.class);
    private final AddCompanyController controller = new AddCompanyController();

    public boolean execute() {
        registerCompany("Porto");
        registerCompany("ISEP");
        registerCompany("FEUP");
        registerCompany("FMUP");
        registerCompany("IBM");

        return true;
    }

    private void registerCompany(String companyName) {
        controller.addCompany(companyName);
        LOGGER.debug("»»» {}", companyName);
    }
}
