package core.application.controllers;

import core.domain.client.ClientUser;
import core.repositories.ClientUserRepository;
import core.persistence.PersistenceContext;
import core.domain.user.Jobs4URoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author losa
 */
public class ListClientUsersController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final ClientUserRepository repo = PersistenceContext.repositories().clientUsers();

    public Iterable<ClientUser> activeClientUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.ADMIN);

        return this.repo.findAllActive();
    }
}
