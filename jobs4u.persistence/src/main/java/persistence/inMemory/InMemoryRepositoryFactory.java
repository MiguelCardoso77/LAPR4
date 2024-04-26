package persistence.inMemory;

import core.repositories.*;
import bootstrappers.Jobs4UBootstrapper;
import core.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;


public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new Jobs4UBootstrapper().execute();
    }

    @Override
    public UserRepository users(final TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public CustomerRepository customerUsers(final TransactionalContext tx) {

        return new InMemoryCustomerRepository();
    }

    @Override
    public CustomerRepository customerUsers() {
        return customerUsers(null);
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
    public JobOpeningRepository jobOpenings(final TransactionalContext tx) {
        return new InMemoryJobOpeningRepository();
    }
    @Override
    public JobOpeningRepository jobOpenings() {
        return jobOpenings(null);
    }

    @Override
    public ApplicationRepository applications(TransactionalContext autoTx) {
        return new InMemoryApplicationRepository();
    }

    @Override
    public ApplicationRepository applications() {
        return applications(null);
    }

    @Override
    public CandidateRepository candidates(TransactionalContext autoTx) {
        return new InMemoryCandidateRepository();
    }

    @Override
    public CandidateRepository candidates() {
        return candidates(null);
    }

    @Override
    public CompanyRepository companies(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public CompanyRepository companies() {
        return null;
    }

    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

}