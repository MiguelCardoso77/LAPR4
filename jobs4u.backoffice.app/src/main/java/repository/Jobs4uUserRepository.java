package repository;

import domain.Jobs4uUser;
import domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

public interface Jobs4uUserRepository extends DomainRepository<MecanographicNumber, Jobs4uUser> {

    /**
     * Returns the cafeteria user (utente) whose username is given.
     *
     * @param name
     *            the username to search for
     * @return
     */
    Optional<Jobs4uUser> findByUsername(Username name);

    /**
     * Returns the cafeteria user (utente) with the given mecanographic number.
     *
     * @param number
     * @return
     */
    default Optional<Jobs4uUser> findByMecanographicNumber(final MecanographicNumber number) {
        return ofIdentity(number);
    }

    /**
     *
     * @return
     */
    Iterable<Jobs4uUser> findAllActive();
}
