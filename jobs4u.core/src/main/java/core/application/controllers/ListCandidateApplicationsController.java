package core.application.controllers;

import core.services.CandidateApplicationsService;

public class ListCandidateApplicationsController {
    CandidateApplicationsService candidateApplicationsService = new CandidateApplicationsService();

    public void sendApplicationsRequest(String email){
        candidateApplicationsService.requestApplications(email);
    }
}
