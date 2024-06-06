package core.services;

import core.domain.interviewModel.InterviewModel;
import core.domain.interviewModel.InterviewModelBuilder;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InterviewModelService {
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();

    public Iterable<InterviewModel> allInterviewModels() {
        return interviewModelRepository.allInterviewModels();
    }

    public InterviewModel findById(int id) {
        return interviewModelRepository.ofIdentity(id).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public InterviewModel registerInterviewModel(String model) {
        InterviewModelBuilder interviewModelBuilder = new InterviewModelBuilder();
        interviewModelBuilder.withoutId(model);
        InterviewModel interviewModel = interviewModelBuilder.build();
        return interviewModelRepository.save(interviewModel);
    }
}
