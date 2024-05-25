package core.services;

import core.domain.interviewModel.InterviewModel;
import core.domain.interviewModel.InterviewModelBuilder;
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
    public InterviewModel registerInterviewModel(String model) {
        InterviewModelBuilder interviewModelBuilder= new InterviewModelBuilder();
        interviewModelBuilder.withoutId(model);
        InterviewModel interviewModel = interviewModelBuilder.build();
        return interviewModelRepository.save(interviewModel);
    }



}
