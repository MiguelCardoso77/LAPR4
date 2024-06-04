package core.application.controllers;

import core.domain.application.Application;
import core.services.CandidateApplicationsService;

import java.util.List;

public class ListCandidateApplicationsController {
    CandidateApplicationsService candidateApplicationsService = new CandidateApplicationsService();

    public List<Application> sendApplicationsRequest(String email){
        return candidateApplicationsService.requestApplications(email);
    }
}
