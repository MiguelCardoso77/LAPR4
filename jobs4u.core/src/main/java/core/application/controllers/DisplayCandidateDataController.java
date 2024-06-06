package core.application.controllers;

import core.domain.candidate.Candidate;
import core.domain.user.Jobs4URoles;
import core.services.CandidateService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 * Controller responsible for displaying candidate data and managing job openings.
 *
 * @author Tomás Gonçalves
 */
public class DisplayCandidateDataController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CandidateService candServ = new CandidateService();

    /**
     * Retrieves all candidates.
     *
     * @return Iterable of all candidates.
     */
    public Iterable<Candidate> allCandidates() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.ADMIN);
        return candServ.allCandidates();
    }

}
