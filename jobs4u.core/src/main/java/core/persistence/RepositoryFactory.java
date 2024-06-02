package core.persistence;

import core.repositories.*;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 * Factory interface for creating repositories.
 *
 * @author Miguel Cardoso
 */
public interface RepositoryFactory {

    /**
     * Factory method to create a transactional context to be used by the repositories.
     */
    TransactionalContext newTransactionalContext();

    /**
     * SystemUser repository.
     */
    UserRepository users(TransactionalContext autoTx);
    UserRepository users();

    /**
     * Customer repository.
     */
    CustomerRepository customerUsers(TransactionalContext autoTx);
    CustomerRepository customerUsers();

    /**
     * JobOpening repository.
     */
    JobOpeningRepository jobOpenings(TransactionalContext autoTx);
    JobOpeningRepository jobOpenings();

    /**
     * Application repository.
     */
    ApplicationRepository applications(TransactionalContext autoTx);
    ApplicationRepository applications();

    /**
     * Candidate repository.
     */
    CandidateRepository candidates(TransactionalContext autoTx);
    CandidateRepository candidates();


    CustomerRepository customers(TransactionalContext autoTx);
    CustomerRepository customers();

    /**
     * Company repository.
     */
    CompanyRepository companies(TransactionalContext autoTx);
    CompanyRepository companies();

    /**
     * JobInterview repository.
     */
    JobInterviewRepository jobInterviews(TransactionalContext autoTx);
    JobInterviewRepository jobInterviews();

    /**
     * RequirementsSpecification repository.
     */
    JobRequirementsSpecificationRepository jobRequirements(TransactionalContext autoTx);
    JobRequirementsSpecificationRepository jobRequirements();

    /**
     * InterviewModel repository.
     */
    InterviewModelRepository interviewModelRepository(TransactionalContext autoTx);
    InterviewModelRepository interviewModelRepository();

    /**
     * Process repository.
     */
    ProcessRepository processRepository(TransactionalContext autoTx);
    ProcessRepository processRepository();
}