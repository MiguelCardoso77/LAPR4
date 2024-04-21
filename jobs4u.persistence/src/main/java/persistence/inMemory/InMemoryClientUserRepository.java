package persistence.inMemory;

import core.clientusermanagement.domain.ClientUser;
import core.clientusermanagement.domain.MecanographicNumber;
import core.clientusermanagement.repositories.ClientUserRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class InMemoryClientUserRepository extends InMemoryDomainRepository<ClientUser, MecanographicNumber> implements ClientUserRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Optional<ClientUser> findByUsername(final Username name) {
        return matchOne(e -> e.user().username().equals(name));
    }

    @Override
    public Optional<ClientUser> findByMecanographicNumber(final MecanographicNumber number) {
        return Optional.of(data().get(number));
    }

    @Override
    public Iterable<ClientUser> findAllActive() {
        return match(e -> e.user().isActive());
    }
}
