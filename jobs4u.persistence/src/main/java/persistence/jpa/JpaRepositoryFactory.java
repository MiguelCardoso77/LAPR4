package persistence.jpa;

import core.repositories.*;
import infrastructure.Application;
import core.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * Factory for creating JPA repositories.
 * This factory creates instances of repositories using JPA for persistence.
 * It supports transactional contexts for operations that require transactions.
 *
 * @author Miguel Cardoso
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    /**
     * Creates a new transactional context for JPA operations.
     *
     * @return a new transactional context.
     */
    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(), Application.settings().getExtendedPersistenceProperties());
    }

    /**
     * Creates a new UserRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new UserRepository.
     */
    @Override
    public UserRepository users(TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    /**
     * Creates a new UserRepository without a transactional context.
     *
     * @return a new UserRepository.
     */
    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    /**
     * Creates a new CustomerRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new CustomerRepository.
     */
    @Override
    public CustomerRepository customerUsers(TransactionalContext autoTx) {
        return new JpaCustomerRepository(autoTx);
    }

    /**
     * Creates a new CustomerRepository without a transactional context.
     *
     * @return a new CustomerRepository.
     */
    @Override
    public CustomerRepository customerUsers() {
        return new JpaCustomerRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new JobOpeningRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new JobOpeningRepository.
     */
    @Override
    public JobOpeningRepository jobOpenings(TransactionalContext autoTx) {
        return new JpaJobOpeningRepository(autoTx);
    }

    /**
     * Creates a new JobOpeningRepository without a transactional context.
     *
     * @return a new JobOpeningRepository.
     */
    @Override
    public JobOpeningRepository jobOpenings() {
        return new JpaJobOpeningRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new ApplicationRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new ApplicationRepository.
     */
    @Override
    public ApplicationRepository applications(TransactionalContext autoTx) {
        return new JpaApplicationRepository(autoTx);
    }

    /**
     * Creates a new ApplicationRepository without a transactional context.
     *
     * @return a new ApplicationRepository.
     */
    @Override
    public ApplicationRepository applications() {
        return new JpaApplicationRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new CandidateRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new CandidateRepository.
     */
    @Override
    public CandidateRepository candidates(TransactionalContext autoTx) {
        return new JpaCandidateRepository(autoTx);
    }

    /**
     * Creates a new CandidateRepository without a transactional context.
     *
     * @return a new CandidateRepository.
     */
    @Override
    public CandidateRepository candidates() {
        return new JpaCandidateRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new CompanyRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new CompanyRepository.
     */
    @Override
    public CompanyRepository companies(TransactionalContext autoTx) {
        return new JpaCompanyRepository(autoTx);
    }

    /**
     * Creates a new CompanyRepository without a transactional context.
     *
     * @return a new CompanyRepository.
     */
    @Override
    public CompanyRepository companies() {
        return new JpaCompanyRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new JobInterviewRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new JobInterviewRepository.
     */
    @Override
    public JobInterviewRepository jobInterviews(TransactionalContext autoTx) {
        return new JpaJobInterviewRepository(autoTx);
    }

    /**
     * Creates a new JobInterviewRepository without a transactional context.
     *
     * @return a new JobInterviewRepository.
     */
    @Override
    public JobInterviewRepository jobInterviews() {
        return new JpaJobInterviewRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new JobRequirementsSpecificationRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new JobRequirementsSpecificationRepository.
     */
    @Override
    public JobRequirementsSpecificationRepository jobRequirements(TransactionalContext autoTx) {
        return new JpaJobRequirementsSpecificationRepository(autoTx);
    }

    /**
     * Creates a new JobRequirementsSpecificationRepository without a transactional context.
     *
     * @return a new JobRequirementsSpecificationRepository.
     */
    @Override
    public JobRequirementsSpecificationRepository jobRequirements() {
        return new JpaJobRequirementsSpecificationRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new InterviewModelRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new InterviewModelRepository.
     */
    @Override
    public InterviewModelRepository interviewModelRepository(TransactionalContext autoTx) {
        return new JpaInterviewModelsRepository(autoTx);
    }

    /**
     * Creates a new InterviewModelRepository without a transactional context.
     *
     * @return a new InterviewModelRepository.
     */
    @Override
    public InterviewModelRepository interviewModelRepository() {
        return new JpaInterviewModelsRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new ProcessRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new ProcessRepository.
     */
    @Override
    public ProcessRepository processRepository(TransactionalContext autoTx) {
        return new JpaProcessRepository(autoTx);
    }

    /**
     * Creates a new ProcessRepository without a transactional context.
     *
     * @return a new ProcessRepository.
     */
    @Override
    public ProcessRepository processRepository() {
        return new JpaProcessRepository(Application.settings().getPersistenceUnitName());
    }

    /**
     * Creates a new NotificationRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     * @return a new NotificationRepository.
     */
    @Override
    public NotificationRepository notifications(TransactionalContext autoTx) {
        return new JpaNotificationRepository(autoTx);
    }

    /**
     * Creates a new NotificationRepository without a transactional context.
     *
     * @return a new NotificationRepository.
     */
    @Override
    public NotificationRepository notifications() {
        return new JpaNotificationRepository(Application.settings().getPersistenceUnitName());
    }

}
