package core.application.eventhandlers;

import core.domain.customer.Customer;
import core.domain.events.NewUserRegisteredFromSignupEvent;
import core.persistence.PersistenceContext;
import core.domain.customer.CustomerBuilder;
import core.repositories.CustomerRepository;
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
    private final CustomerRepository customerRepository = PersistenceContext
            .repositories().customerUsers();
    public Customer addClientUser(final NewUserRegisteredFromSignupEvent event) {
        final Optional<SystemUser> newUser = findUser(event);

        return newUser.map(u -> {
            final CustomerBuilder customerBuilder = new CustomerBuilder();
            customerBuilder.withAll(u, event.telephoneNumber(), event.company());
            return customerRepository.save(customerBuilder.build());
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
