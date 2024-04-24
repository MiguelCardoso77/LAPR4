package bootstrappers.bootstraping;

import core.application.controllers.AddJobOpeningController;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobOpeningBuilder;
import core.domain.jobOpening.Mode;

public class JobsBootstrapper {
    final AddJobOpeningController controller = new AddJobOpeningController();


    public boolean execute(){

        return true;
    }

    private void registerJobOpening(String jobReference, String description, int vacanciesNumber, String address, Mode mode, ContractType contractType, String titleOrFunction) {
       controller.addJobOpening(jobReference, description, vacanciesNumber, address, mode , contractType, titleOrFunction);
    }


}
