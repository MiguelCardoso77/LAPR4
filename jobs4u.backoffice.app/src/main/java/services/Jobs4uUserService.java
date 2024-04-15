package services;

import domain.Jobs4uRoles;
import domain.Jobs4uUser;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.money.domain.model.Money;
import persistence.PersistenceContext;
import repository.Jobs4uUserRepository;

import java.util.Optional;

@ApplicationService
public class Jobs4uUserService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final Jobs4uUserRepository cafeteriaUsersRepo = PersistenceContext.repositories().cafeteriaUsers();

    public Jobs4uUser me() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4uRoles.JOBS4U_USER);

        // TODO cache the cafeteria user object
        final Optional<Jobs4uUser> me = cafeteriaUsersRepo.findByUsername(myUser().identity());

        return me.orElseThrow(IllegalStateException::new);
    }

    private SystemUser myUser() {
        return authz.session().map(UserSession::authenticatedUser).orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns the card balance of the authenticated cafeteria user.
     *
     * @return
     */
    public Money myBalance() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4uRoles.JOBS4U_USER);

        return movementsRepo.balanceOf(myUser().identity());
    }
}
