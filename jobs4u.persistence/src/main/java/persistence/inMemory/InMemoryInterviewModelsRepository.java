package persistence.inMemory;

import core.domain.company.Company;
import core.domain.interview.InterviewModel;
import core.repositories.InterviewModelRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryInterviewModelsRepository extends InMemoryDomainRepository<InterviewModel, Integer>  implements InterviewModelRepository {
    @Override
    public Iterable<InterviewModel> allInterviewModels() {
        return null;
    }

}
