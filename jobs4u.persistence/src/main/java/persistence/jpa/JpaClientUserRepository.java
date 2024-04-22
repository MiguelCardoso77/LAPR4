package persistence.jpa;

import core.clientusermanagement.domain.TelephoneNumber;
import infrastructure.Application;
import core.clientusermanagement.domain.ClientUser;
import core.clientusermanagement.repositories.ClientUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaClientUserRepository
        extends JpaAutoTxRepository<ClientUser, TelephoneNumber, TelephoneNumber>
        implements ClientUserRepository {

    public JpaClientUserRepository(final TransactionalContext autoTx) {
        super(autoTx, "mecanographicNumber");
    }

    public JpaClientUserRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "mecanographicNumber");
    }

    @Override
    public Optional<ClientUser> findByUsername(final Username name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.systemUser.username=:name", params);
    }

    @Override
    public Optional<ClientUser> findByMecanographicNumber(final TelephoneNumber number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.mecanographicNumber=:number", params);
    }

    @Override
    public Iterable<ClientUser> findAllActive() {
        return match("e.systemUser.active = true");
    }
}
