package core.application.controllers;

import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.domain.user.Jobs4URoles;
import core.services.CandidateService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.List;

/**
 * Controller for managing the listing of candidates in the Jobs4U system.
 * This class provides methods to retrieve all candidates and to find a candidate by telephone number.
 * It uses the AuthorizationService and CandidateService from the eapli framework and core services respectively.
 *
 * @author Miguel Cardoso
 */
@UseCaseController
public class ListCandidatesController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CandidateService candidateSvc = new CandidateService();

    /**
     * Retrieves all candidates.
     *
     * @return a list of all candidates
     */
    public List<Candidate> allCandidates() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.OPERATOR);

        return (List<Candidate>) candidateSvc.allCandidates();
    }

    /**
     * Finds a candidate by their telephone number.
     *
     * @param telephoneNumber the telephone number of the candidate to find
     * @return the candidate with the specified telephone number
     */
    public Candidate findCandidateByTelephoneNumber(TelephoneNumber telephoneNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN, Jobs4URoles.OPERATOR);

        return candidateSvc.findCandidateByTelephoneNumber(telephoneNumber);
    }
}
