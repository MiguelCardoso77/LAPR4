package events;

import domain.Jobs4uUser;
import domain.Jobs4uUserBuilder;
import eapli.framework.functional.Functions;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import persistence.PersistenceContext;
import repository.Jobs4uUserRepository;

import java.util.Optional;

public class AddJobs4uUserOnSignupAcceptedController {
    private final UserRepository repo = PersistenceContext.repositories().users();
    private final Jobs4uUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();

    public Jobs4uUser addJobs4uUser(final NewUserRegisteredFromSignupEvent event) {
        final Optional<SystemUser> newUser = findUser(event);
        return newUser.map(u -> createJobs4uUser(event, u)).orElseThrow(IllegalStateException::new);
    }

    private Jobs4uUser createJobs4uUser(final NewUserRegisteredFromSignupEvent event, SystemUser u) {
        final var cafeteriaUser = new Jobs4uUserBuilder().withMecanographicNumber(event.mecanographicNumber()).withSystemUser(u).build();
        return cafeteriaUserRepository.save(cafeteriaUser);
    }

    @SuppressWarnings("squid:S1488")
    private Optional<SystemUser> findUser(final NewUserRegisteredFromSignupEvent event) {
        // since we are using events, the actual user may not yet be
        // created, so lets give it a time and wait
        final Optional<SystemUser> newUser = Functions.retry(() -> repo.ofIdentity(event.username()), 500, 30);
        return newUser;
    }
}
