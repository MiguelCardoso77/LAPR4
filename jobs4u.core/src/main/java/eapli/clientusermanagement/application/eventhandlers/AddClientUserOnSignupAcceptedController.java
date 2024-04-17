package eapli.clientusermanagement.application.eventhandlers;

import eapli.clientusermanagement.domain.ClientUser;
import eapli.clientusermanagement.domain.ClientUserBuilder;
import eapli.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.clientusermanagement.repositories.ClientUserRepository;
import eapli.persistence.PersistenceContext;
import eapli.framework.functional.Functions;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

import java.util.Optional;

/**
 *
 * @author Paulo Gandra de Sousa
 *
 */
/* package */ class AddClientUserOnSignupAcceptedController {

    private final UserRepository repo = PersistenceContext.repositories().users();
    private final ClientUserRepository clientUserRepository = PersistenceContext
            .repositories().clientUsers();

    public ClientUser addClientUser(final NewUserRegisteredFromSignupEvent event) {
        final Optional<SystemUser> newUser = findUser(event);

        return newUser.map(u -> {
            final ClientUserBuilder clientUserBuilder = new ClientUserBuilder();
            clientUserBuilder.withMecanographicNumber(event.mecanographicNumber())
                    .withSystemUser(u);
            return clientUserRepository.save(clientUserBuilder.build());
        }).orElseThrow(IllegalStateException::new);
    }

    @SuppressWarnings("squid:S1488")
    private Optional<SystemUser> findUser(final NewUserRegisteredFromSignupEvent event) {
        // since we are using events, the actual user may not yet be
        // created, so lets give it a time and wait
        final Optional<SystemUser> newUser = Functions
                .retry(() -> repo.ofIdentity(event.username()), 1000, 3);
        return newUser;
    }
}
