package core.application.controllers;


import core.domain.candidate.Candidate;
import core.services.CandidateService;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.List;
import java.util.Objects;

public class ListCandidateApplicationsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    CandidateService candidateService = new CandidateService();


}
