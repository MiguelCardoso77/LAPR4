package eapli.clientusermanagement.application;

import eapli.clientusermanagement.domain.ClientUser;
import eapli.clientusermanagement.repositories.ClientUserRepository;
import eapli.persistence.PersistenceContext;
import eapli.usermanagement.domain.BaseRoles;
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
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.ADMIN);

        return this.repo.findAllActive();
    }
}
