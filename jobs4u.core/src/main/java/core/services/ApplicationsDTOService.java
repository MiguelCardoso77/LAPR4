package core.services;

import core.domain.application.Application;
import core.domain.application.ApplicationsDTO;

public class ApplicationsDTOService {
    private final ApplicationService applicationService = new ApplicationService();

    public ApplicationsDTO toDTO(Application application) {
        int numberOfApplicants = applicationService.numberOfApplicationsForJobOpening(application.jobReference());

        return new ApplicationsDTO(application.toStringServer(), numberOfApplicants);
    }
}
