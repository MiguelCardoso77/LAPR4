package core.application.controllers;


import core.services.CandidateService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListCandidateApplicationsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    CandidateService candidateService = new CandidateService();


}
