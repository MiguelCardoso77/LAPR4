package core.services;

import core.domain.client.ClientUser;
import core.domain.client.TelephoneNumber;
import core.persistence.PersistenceContext;
import core.domain.user.Jobs4URoles;
import core.repositories.ClientUserRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

/**
 * @author mcn
 */
public class ClientUserService {

    private final AuthorizationService authz =
            AuthzRegistry.authorizationService();
    private final ClientUserRepository repo =
            PersistenceContext.repositories().clientUsers();

    //private final JobOpeningService jobserv=

    public Optional<ClientUser> findClientUserByMecNumber(
            final String mecNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER,
                Jobs4URoles.ADMIN);
        return repo.ofIdentity(TelephoneNumber.valueOf(mecNumber));
    }

    public Optional<ClientUser> findClientUserByUsername(
            final Username user) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER,
                Jobs4URoles.ADMIN);
        return repo.findByUsername(user);
    }
}
