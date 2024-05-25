package core.services;

import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.domain.interviewModel.InterviewModel;
import core.domain.interviewModel.InterviewModelBuilder;
import core.persistence.PersistenceContext;
import core.repositories.InterviewModelRepository;
import core.repositories.JobInterviewRepository;
import eapli.framework.validations.Preconditions;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();
    private final JobInterviewRepository repository = PersistenceContext.repositories().jobInterviews();
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
    /**
     * Updates the job interview score.
     *
     * @param jobInterviewID The id of the interview to be updated.
     * @param newScore The new score to be set.
     */
    @Transactional
    public void interviewScoreUpdate(Integer jobInterviewID, Score newScore){
        Preconditions.nonNull(newScore, "Score cannot be null");
        JobInterview jobInterview = repository.ofIdentity(jobInterviewID).orElseThrow(() -> new IllegalArgumentException("Invalid interview id"));
        jobInterview.updateScore(newScore);
        repository.save(jobInterview);
    }
}
