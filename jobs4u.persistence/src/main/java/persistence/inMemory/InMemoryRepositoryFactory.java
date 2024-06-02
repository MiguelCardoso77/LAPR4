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
    public CustomerRepository customers(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public CustomerRepository customers() {
        return customers(null);
    }

    @Override
    public CompanyRepository companies(TransactionalContext autoTx) {
        return new InMemoryCompanyRepository();
    }

    @Override
    public CompanyRepository companies() {
        return companies(null);
    }

    @Override
    public JobInterviewRepository jobInterviews(TransactionalContext autoTx) {
        return new InMemoryJobInterviewRepository();
    }

    @Override
    public JobInterviewRepository jobInterviews() {
        return null;
    }

    @Override
    public JobRequirementsSpecificationRepository jobRequirements(TransactionalContext autoTx) {
        return new InMemoryJobRequirementsSpecificationRepository();
    }

    @Override
    public JobRequirementsSpecificationRepository jobRequirements() {
        return null;
    }


    @Override
    public TransactionalContext newTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public InterviewModelRepository interviewModelRepository(TransactionalContext autoTx) {
        return new InMemoryInterviewModelsRepository();
    }

    @Override
    public InterviewModelRepository interviewModelRepository() {
        return null;
    }


    @Override
    public ProcessRepository processRepository(TransactionalContext autoTx) {
        return new InMemoryProcessRepository();
    }

    @Override
    public ProcessRepository processRepository() {
        return null;
    }

}