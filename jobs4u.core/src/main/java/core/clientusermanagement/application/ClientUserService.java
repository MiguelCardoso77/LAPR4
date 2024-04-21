package core.clientusermanagement.application;

import core.clientusermanagement.domain.ClientUser;
import core.clientusermanagement.domain.MecanographicNumber;
import core.persistence.PersistenceContext;
import core.usermanagement.domain.Jobs4URoles;
import core.clientusermanagement.repositories.ClientUserRepository;
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

    public Optional<ClientUser> findClientUserByMecNumber(
            final String mecNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER,
                Jobs4URoles.ADMIN);
        return repo.ofIdentity(MecanographicNumber.valueOf(mecNumber));
    }

    public Optional<ClientUser> findClientUserByUsername(
            final Username user) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER,
                Jobs4URoles.ADMIN);
        return repo.findByUsername(user);
    }
}
