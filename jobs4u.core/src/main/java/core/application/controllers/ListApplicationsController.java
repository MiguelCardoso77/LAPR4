package core.application.controllers;

import core.domain.application.Application;
import core.services.ApplicationService;

public class ListApplicationsController {
    private final ApplicationService appserv = new ApplicationService();



    public Iterable<Application> allApplications() {
        return appserv.allApplications();
    }




}
