package bootstrappers.bootstraping;

import core.application.controllers.AddJobOpeningController;
import core.domain.company.Company;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.JobReference;
import core.domain.jobOpening.Mode;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobsBootstrapper.class);
    final AddJobOpeningController controller = new AddJobOpeningController();

    public boolean execute() {
        CompanyBootstrapper companyBootstrapper = new CompanyBootstrapper();

        registerJobOpening("FEUP", "Jogador da Bola", 3, "Estádio do Dragão", Mode.ON_SITE, ContractType.FULL_TIME, "Ponta de Lança", companyBootstrapper.controller.addCompany("FEUP"));
        registerJobOpening("FMUP", "Chefe de Cozinha", 2, "Bar da Ae", Mode.ON_SITE, ContractType.FULL_TIME, "Chef", companyBootstrapper.controller.addCompany("FMUP"));
        registerJobOpening("IBM-000123", "Software Engineer", 4, "IBM st.", Mode.HYBRID, ContractType.FULL_TIME, "Software Engineer", companyBootstrapper.controller.addCompany("IBM"));

        return true;
    }

    private void registerJobOpening(String companyName, String description, int vacanciesNumber, String address, Mode mode, ContractType contractType, String titleOrFunction, Company company) {
        JobReference jobReference = new JobReference(companyName, true);
        controller.addJobOpening(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, company);
        LOGGER.debug("»»» %s", jobReference);
    }

}