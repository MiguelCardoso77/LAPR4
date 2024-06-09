package core.application.controllers;

import core.services.CandidateApplicationsService;

import java.util.List;

/**
 * Controller class for handling candidate application requests.
 *
 * @author Diana Neves
 */
public class ListCandidateApplicationsController {
    CandidateApplicationsService candidateApplicationsService = new CandidateApplicationsService();

    /**
     * Sends a request to retrieve the list of applications for a candidate.
     *
     * @param email The email of the candidate whose applications are to be retrieved
     * @return A list of applications as strings
     */
    public List<String> sendApplicationsRequest(String email) {
        return candidateApplicationsService.requestApplications(email);
    }
}
