package persistence.jpa;



import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

class JpaJobRequirementsSpecificationRepository extends JpaAutoTxRepository<JobRequirementsSpecification, Integer ,Integer> implements JobRequirementsSpecificationRepository {

    public JpaJobRequirementsSpecificationRepository(final TransactionalContext autoTx) {
        super(autoTx, "jobRequirementsSpecification");
    }

    public JpaJobRequirementsSpecificationRepository(final String puname) {
        super(puname, "jobRequirementsSpecification");
    }


    @Override
    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification() {
        return findAll();
    }
}
