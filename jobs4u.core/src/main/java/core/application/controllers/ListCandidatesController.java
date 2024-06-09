package core.application.controllers;

import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.domain.user.Jobs4URoles;
import core.services.CandidateService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

@UseCaseController
public class ListCandidatesController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CandidateService candidateSvc = new CandidateService();

    public List<Candidate> allCandidates() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.OPERATOR);

        return (List<Candidate>) candidateSvc.allCandidates();
    }
    public Candidate findCandidateByTelephoneNumber(TelephoneNumber telephoneNumber){
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN, Jobs4URoles.OPERATOR);

        return candidateSvc.findCandidateByTelephoneNumber(telephoneNumber);
    }
}
