package core.persistence;

import core.repositories.ApplicationRepository;
import core.repositories.CustomerRepository;
import core.repositories.SignupRequestRepository;
import core.repositories.JobOpeningRepository;
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

    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**

    repository will
    be created
    in auto
    transaction mode*
            @return*/

    SignupRequestRepository signupRequests();

    /**
     * @param autoTx
     * @return
     */

    JobOpeningRepository jobOpenings(TransactionalContext autoTx);

    JobOpeningRepository jobOpenings();

    ApplicationRepository applications(TransactionalContext autoTx);

    ApplicationRepository applications();

}