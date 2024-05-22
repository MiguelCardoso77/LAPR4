package core.application.controllers;

import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.services.ApplicationService;
import core.services.JobOpeningService;

import java.util.ArrayList;
import java.util.List;

public class ListApplicationsController {
    private final ApplicationService appserv = new ApplicationService();



    public Iterable<Application> allApplications() {
        return appserv.allApplications();
    }




}
