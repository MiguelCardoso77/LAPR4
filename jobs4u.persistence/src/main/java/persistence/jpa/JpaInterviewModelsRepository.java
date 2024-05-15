package persistence.jpa;

import core.domain.interview.InterviewModel;
import core.repositories.InterviewModelRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import infrastructure.Application;

public class JpaInterviewModelsRepository extends JpaAutoTxRepository<InterviewModel, Integer, Integer> implements InterviewModelRepository {
    public JpaInterviewModelsRepository(TransactionalContext autoTx) {
        super(autoTx, "id");
    }
    public JpaInterviewModelsRepository(final String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "idInterviewModel");
    }
    @Override
    public Iterable<InterviewModel> allInterviewModels() {
        return null;
    }
}
