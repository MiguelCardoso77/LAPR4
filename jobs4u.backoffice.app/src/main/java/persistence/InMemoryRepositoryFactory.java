package persistence;

import app.Jobs4uBootstrapper;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;
import persistence.RepositoryFactory;
import repository.Jobs4uUserRepository;

public class InMemoryRepositoryFactory implements RepositoryFactory {

    /**
     * Initialize a power user so that we can login.
     */
    @Override
    public UserRepository users(final TransactionalContext tx) {
        final InMemoryUserRepository repo = new InMemoryUserRepository();
        Jobs4uBootstrapper.registerPowerUser(repo);
        return repo;
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public Jobs4uUserRepository cafeteriaUsers(final TransactionalContext tx) {

        return new InMemoryCafeteriaUserRepository();
    }

    @Override
    public Jobs4uUserRepository cafeteriaUsers() {
        return cafeteriaUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        return new InMemoryTransactionalContext();
    }

}
