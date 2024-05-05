package core.services;

import core.domain.interview.InterviewModel;
import core.domain.interview.InterviewModelBuilder;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();

    @Transactional
    public Iterable<InterviewModel> allInterviewModels(){
        return interviewModelRepository.allInterviewModels();
    }

    @Transactional
    public InterviewModel registerJobRequirement(String model) {
        InterviewModelBuilder interviewModelBuilder= new InterviewModelBuilder();
        interviewModelBuilder.withoutId(model);
        InterviewModel interviewModel = interviewModelBuilder.build();
        return interviewModelRepository.save(interviewModel);
    }

}
