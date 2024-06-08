package persistence.jpa;

import core.domain.interviewModel.InterviewModel;
import core.repositories.InterviewModelRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import infrastructure.Application;

/**
 * JPA implementation of the InterviewModelRepository interface.
 * This repository provides access to InterviewModel entities using JPA for persistence.
 *
 * @author Miguel Cardoso
 */
public class JpaInterviewModelsRepository extends JpaAutoTxRepository<InterviewModel, Integer, Integer> implements InterviewModelRepository {

    /**
     * Constructs a new JpaInterviewModelsRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     */
    public JpaInterviewModelsRepository(TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * Constructs a new JpaInterviewModelsRepository with the given persistence unit name.
     *
     * @param puname the name of the persistence unit.
     */
    public JpaInterviewModelsRepository(final String puname){
        super(puname, Application.settings().getExtendedPersistenceProperties(), "idInterviewModel");
    }

    /**
     * Retrieves all InterviewModel entities.
     *
     * @return an iterable collection of all InterviewModel entities.
     */
    @Override
    public Iterable<InterviewModel> allInterviewModels() {
        return findAll();
    }
}
