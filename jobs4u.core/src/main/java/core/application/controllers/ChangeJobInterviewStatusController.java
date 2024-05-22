package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.Status;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;

public class ChangeJobInterviewStatusController {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();

    public Application changeJobInterviewStatus(Status applicationStatus, Application application) {
        application.changeStatus(applicationStatus);
        return applicationRepository.save(application);
    }
}
