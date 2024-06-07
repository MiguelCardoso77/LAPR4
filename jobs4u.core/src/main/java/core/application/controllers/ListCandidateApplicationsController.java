package core.application.controllers;

import core.services.CandidateApplicationsService;

import java.util.List;

public class ListCandidateApplicationsController {
    CandidateApplicationsService candidateApplicationsService = new CandidateApplicationsService();

    public List<String> sendApplicationsRequest(String email) {
        return candidateApplicationsService.requestApplications(email);
    }
}
