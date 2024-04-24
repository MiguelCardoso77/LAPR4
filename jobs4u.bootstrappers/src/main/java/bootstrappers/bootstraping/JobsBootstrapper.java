package bootstrappers.bootstraping;

import core.application.controllers.AddJobOpeningController;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.Mode;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobsBootstrapper.class);
    final AddJobOpeningController controller = new AddJobOpeningController();

    public boolean execute(){
        registerJobOpening("1-12345", "Jogador da Bola", 3, "Estádio do Dragão", Mode.ON_SITE, ContractType.FULL_TIME, "Ponta de Lança");
        registerJobOpening("2-24567", "Chefe de Cozinha", 2, "Bar da Ae", Mode.ON_SITE, ContractType.FULL_TIME, "Chef");

        return true;
    }

    private void registerJobOpening(String jobReference, String description, int vacanciesNumber, String address, Mode mode, ContractType contractType, String titleOrFunction) {
       controller.addJobOpening(jobReference, description, vacanciesNumber, address, mode , contractType, titleOrFunction);
        LOGGER.debug("»»» %s", jobReference);
    }


}
