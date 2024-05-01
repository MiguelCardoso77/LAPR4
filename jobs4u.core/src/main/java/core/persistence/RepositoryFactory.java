package core.persistence;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.repositories.*;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**

@author
Paulo Gandra
Sousa**/

public interface RepositoryFactory {

    /**

    factory method
    to create
    a transactional
    context to
    use in
    the repositories*
            @return*/

    TransactionalContext newTransactionalContext();

    /**
            *

    @param
    autoTx
    the transactional
    context to
    enrol
    @return
            */

    UserRepository users(TransactionalContext autoTx);

    /**

    repository will
    be created
    in auto
    transaction mode*
            @return*/

    UserRepository users();

    /**
            *

    @param
    autoTx
    the transactional
    context to
    enroll
    @return
            */

    CustomerRepository customerUsers(TransactionalContext autoTx);

    /**

    repository will
    be created
    in auto
    transaction mode*
            @return*/

    CustomerRepository customerUsers();

    /**
            *

    @param
    autoTx
    the transactional
    context to
    enroll
    @return
            */


    /**

    repository will
    be created
    in auto
    transaction mode*
            @return*/


    /**
     * @param autoTx
     * @return
     */

    JobOpeningRepository jobOpenings(TransactionalContext autoTx);

    JobOpeningRepository jobOpenings();

    ApplicationRepository applications(TransactionalContext autoTx);

    ApplicationRepository applications();

    CandidateRepository candidates(TransactionalContext autoTx);
    CandidateRepository candidates();

    CompanyRepository companies(TransactionalContext autoTx);
    CompanyRepository companies();

    JobInterviewRepository jobInterviews(TransactionalContext autoTx);
    JobInterviewRepository jobInterviews();
    JobRequirementsSpecificationRepository jobRequirements(TransactionalContext autoTx);
    JobRequirementsSpecificationRepository jobRequirements();


}