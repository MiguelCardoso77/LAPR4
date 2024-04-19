package eapli.myclientuser.application;

import eapli.clientusermanagement.domain.ClientUser;
import eapli.clientusermanagement.repositories.ClientUserRepository;
import eapli.persistence.PersistenceContext;
import eapli.usermanagement.domain.Jobs4URoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.Optional;

/**
 *
 * @author Paulo Gandra de Sousa
 */
public class MyClientUserService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ClientUserRepository repo = PersistenceContext.repositories().clientUsers();

    public ClientUser me() {
        final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
        final SystemUser myUser = s.authenticatedUser();
        // TODO cache the client user object
        final Optional<ClientUser> me = repo.findByUsername(myUser.identity());
        return me.orElseThrow(IllegalStateException::new);
    }

    public ClientUser myUser() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.CLIENT_USER);
        final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
        final SystemUser me = s.authenticatedUser();
        return repo.findByUsername(me.identity()).orElseThrow(IllegalStateException::new);
    }

}
