package persistence;

import domain.Jobs4uUser;
import domain.MecanographicNumber;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import repository.Jobs4uUserRepository;

import java.util.Optional;

public class InMemoryJobs4uUserRepository extends InMemoryDomainRepository<Jobs4uUser, MecanographicNumber> implements Jobs4uUserRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<Jobs4uUser> findByUsername(final Username name) {
        return matchOne(e -> e.user().username().equals(name));
    }

    @Override
    public Optional<Jobs4uUser> findByMecanographicNumber(final MecanographicNumber number) {
        return Optional.of(data().get(number));
    }

    @Override
    public Iterable<Jobs4uUser> findAllActive() {
        return match(e -> e.user().isActive());
    }
}
