package core.myclientuser.application;

import core.clientusermanagement.domain.ClientUser;
import core.persistence.PersistenceContext;
import core.usermanagement.domain.Jobs4URoles;
import core.clientusermanagement.repositories.ClientUserRepository;
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
