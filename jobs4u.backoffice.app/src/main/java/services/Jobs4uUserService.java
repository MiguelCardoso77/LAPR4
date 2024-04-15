package services;

import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.money.domain.model.Money;
import persistence.PersistenceContext;

@ApplicationService
public class Jobs4uUserService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CafeteriaUserRepository cafeteriaUsersRepo = PersistenceContext.repositories().cafeteriaUsers();

    public CafeteriaUser me() {
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.CAFETERIA_USER);

        // TODO cache the cafeteria user object
        final Optional<CafeteriaUser> me = cafeteriaUsersRepo.findByUsername(myUser().identity());

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
        authz.ensureAuthenticatedUserHasAnyOf(CafeteriaRoles.CAFETERIA_USER);

        return movementsRepo.balanceOf(myUser().identity());
    }
}
