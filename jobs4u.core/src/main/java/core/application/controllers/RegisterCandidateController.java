package core.application.controllers;

import core.domain.candidate.Candidate;
import core.domain.user.Jobs4UPasswordPolicy;
import core.domain.user.Jobs4URoles;
import core.services.CandidateService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.Calendar;
import java.util.Set;

@UseCaseController
public class RegisterCandidateController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userService = AuthzRegistry.userService();
    private final Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();

    public Candidate registerCandidate(final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn, final String telephoneNumber, final String curriculum) {
        final SystemUser newUser = createSystemUser(firstName, lastName, email, roles, createdOn);
        return createCandidate(newUser, telephoneNumber, curriculum);
    }

    private SystemUser createSystemUser(final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        Preconditions.nonNull(firstName);
        Preconditions.nonNull(lastName);
        Preconditions.nonNull(email);

        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.ADMIN, Jobs4URoles.BOOTSTRAP, Jobs4URoles.CANDIDATE, Jobs4URoles.OPERATOR);

        roles.add(Jobs4URoles.CANDIDATE);
        String password = passwordPolicy.passwordGenerator(firstName);

        return userService.registerNewUser(email, password, firstName, lastName, email, roles, createdOn);
    }

    private Candidate createCandidate(final SystemUser newUser, final String telephoneNumber, final String curriculum) {
        Preconditions.nonNull(newUser);
        Preconditions.nonNull(telephoneNumber);
        Preconditions.nonNull(curriculum);

        CandidateService candidateService = new CandidateService();

        return candidateService.registerCandidate(newUser, telephoneNumber, curriculum);
    }
}
