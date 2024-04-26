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

/* package */ class AddClientUserOnSignupAcceptedController {

    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerUsers();

    public Customer addClientUser(final NewUserRegisteredFromSignupEvent event) {
        final Optional<SystemUser> newUser = findUser(event);

        return newUser.map(u -> {
            final CustomerBuilder customerBuilder = new CustomerBuilder();
            customerBuilder.withEmailAddress(event.emailAddress()).withUser(u);
            return customerRepository.save(customerBuilder.build());
        }).orElseThrow(IllegalStateException::new);
    }

    private Optional<SystemUser> findUser(final NewUserRegisteredFromSignupEvent event) {
        final Optional<SystemUser> newUser = Functions.retry(() -> userRepository.ofIdentity(event.username()), 1000, 3);
        return newUser;
    }
}