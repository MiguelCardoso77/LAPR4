package core.application.controllers;

import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.domain.user.Jobs4UPasswordPolicy;
import core.domain.user.Jobs4URoles;
import core.services.CandidateService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.Calendar;
import java.util.Set;

/**
 * Controller responsible for registering new candidates in the system.
 *
 * @author Miguel Cardoso
 */
@UseCaseController
public class RegisterCandidateController {
    private final UserManagementService userService = AuthzRegistry.userService();
    private final CandidateService candidateService = new CandidateService();
    private final Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();

    /**
     * Registers a new candidate in the system.
     *
     * @param firstName       the first name of the candidate
     * @param lastName        the last name of the candidate
     * @param email           the email of the candidate
     * @param roles           the roles of the candidate
     * @param createdOn       the date when the candidate was created
     * @param telephoneNumber the telephone number of the candidate
     * @param curriculum      the curriculum of the candidate
     * @return the registered candidate
     */
    public Candidate registerCandidate(final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn, final String telephoneNumber, final String curriculum) {
        final SystemUser newUser = createSystemUser(firstName, lastName, email, roles, createdOn);
        return createCandidate(newUser, telephoneNumber, curriculum);
    }

    /**
     * Creates a new system user for the candidate.
     *
     * @param firstName the first name of the candidate
     * @param lastName  the last name of the candidate
     * @param email     the email of the candidate
     * @param roles     the roles of the candidate
     * @param createdOn the date when the candidate was created
     * @return the created system user
     */
    private SystemUser createSystemUser(final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        Preconditions.nonNull(firstName);
        Preconditions.nonNull(lastName);
        Preconditions.nonNull(email);

        roles.add(Jobs4URoles.CANDIDATE);
        String password = passwordPolicy.passwordGenerator(firstName);

        return userService.registerNewUser(email, password, firstName, lastName, email, roles, createdOn);
    }

    /**
     * Creates a new candidate entity.
     *
     * @param newUser         the system user associated with the candidate
     * @param telephoneNumber the telephone number of the candidate
     * @param curriculum      the curriculum of the candidate
     * @return the created candidate
     */
    private Candidate createCandidate(final SystemUser newUser, final String telephoneNumber, final String curriculum) {
        Preconditions.nonNull(newUser);
        Preconditions.nonNull(telephoneNumber);
        Preconditions.nonNull(curriculum);

        return candidateService.registerCandidate(newUser, telephoneNumber, curriculum);
    }
    /**
     * Verifies if a telephone number is already registered.
     *
     * @param telephoneNumber the telephone number to verify
     * @return true if the telephone number is already registered, false otherwise
     */
    public boolean verifyTelephoneNumber(TelephoneNumber telephoneNumber){
        return candidateService.findCandidateByTelephoneNumber(telephoneNumber) != null;
    }

}
