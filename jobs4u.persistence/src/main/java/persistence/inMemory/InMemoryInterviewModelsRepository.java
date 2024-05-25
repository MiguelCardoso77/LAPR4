package persistence.inMemory;

import core.domain.interviewModel.InterviewModel;
import core.repositories.InterviewModelRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryInterviewModelsRepository extends InMemoryDomainRepository<InterviewModel, Integer>  implements InterviewModelRepository {
    @Override
    public Iterable<InterviewModel> allInterviewModels() {
        return null;
    }

}
