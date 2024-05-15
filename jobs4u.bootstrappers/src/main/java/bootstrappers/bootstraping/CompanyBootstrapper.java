package bootstrappers.bootstraping;

import core.application.controllers.AddCompanyController;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyBootstrapper.class);

    final AddCompanyController controller = new AddCompanyController();

    public boolean execute(){
        registerCompany("Porto");
        registerCompany("ISEP");
        return true;
    }

    private void registerCompany(String companyName){
        controller.addCompany(companyName);
        LOGGER.debug("»»» %s");
    }
}
