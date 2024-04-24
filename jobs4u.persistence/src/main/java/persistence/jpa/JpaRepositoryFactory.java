package persistence.jpa;

import core.repositories.ApplicationRepository;
import infrastructure.Application;
import core.repositories.SignupRequestRepository;
import core.persistence.RepositoryFactory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public JpaCustomerRepository customerUsers(final TransactionalContext autoTx) {
        return new JpaCustomerRepository(autoTx);
    }

    @Override
    public JpaCustomerRepository customerUsers() {
        return new JpaCustomerRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(final TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JpaJobOpeningRepository jobOpenings(final TransactionalContext autoTx) {
        return new JpaJobOpeningRepository(autoTx);
    }

    @Override
    public JpaJobOpeningRepository jobOpenings() {
        return new JpaJobOpeningRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ApplicationRepository applications(final TransactionalContext autoTx) {
        return  new JpaApplicationRepository(autoTx);
    }

    @Override
    public ApplicationRepository applications() {
        return new JpaApplicationRepository(Application.settings().getPersistenceUnitName());
    }


    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

}
