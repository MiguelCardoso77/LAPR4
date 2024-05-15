package persistence.jpa;

import core.pluginManagement.importer.LanguageImporterPluginRepository;
import core.pluginManagement.language.LanguageRepository;
import core.pluginManagement.language.LanguageTypeRepository;
import core.repositories.*;
import infrastructure.Application;
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

    @Override
    public CandidateRepository candidates(final TransactionalContext autoTx) {
        return new JpaCandidateRepository(autoTx);
    }

    @Override
    public CandidateRepository candidates() {
        return new JpaCandidateRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CompanyRepository companies(TransactionalContext autoTx) {
        return new JpaCompanyRepository(autoTx);
    }

    @Override
    public CompanyRepository companies() {
        return new JpaCompanyRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JobInterviewRepository jobInterviews(TransactionalContext autoTx) {
        return new JpaJobInterviewRepository(autoTx);
    }

    @Override
    public JobInterviewRepository jobInterviews() {
        return new JpaJobInterviewRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JobRequirementsSpecificationRepository jobRequirements(TransactionalContext autoTx) {
        return new JpaJobRequirementsSpecificationRepository(autoTx);
    }

    @Override
    public JobRequirementsSpecificationRepository jobRequirements() {
        return new JpaJobRequirementsSpecificationRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public LanguageImporterPluginRepository languageImporterPlugins() {
        return null;
    }

    @Override
    public LanguageRepository languages() {
        return null;
    }

    @Override
    public LanguageTypeRepository languageTypes() {
        return null;
    }

    @Override
    public InterviewModelRepository interviewModelRepository(TransactionalContext autoTx) {
        return new JpaInterviewModelsRepository(autoTx);
    }

    @Override
    public InterviewModelRepository interviewModelRepository() {
        return new JpaInterviewModelsRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public ProcessRepository processRepository(TransactionalContext autoTx) {
            return new JpaProcessRepository(autoTx);
    }

    @Override
    public ProcessRepository processRepository() {
        return new JpaProcessRepository(Application.settings().getPersistenceUnitName());
    }



}
