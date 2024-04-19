package eapli.clientusermanagement.application;

import eapli.clientusermanagement.domain.ClientUser;
import eapli.clientusermanagement.domain.MecanographicNumber;
import eapli.clientusermanagement.repositories.ClientUserRepository;
import eapli.persistence.PersistenceContext;
import eapli.usermanagement.domain.Jobs4URoles;
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
                Jobs4URoles.ADMIN,
                Jobs4URoles.CASHIER);
        return repo.ofIdentity(MecanographicNumber.valueOf(mecNumber));
    }

    public Optional<ClientUser> findClientUserByUsername(
            final Username user) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER,
                Jobs4URoles.ADMIN);
        return repo.findByUsername(user);
    }
}
