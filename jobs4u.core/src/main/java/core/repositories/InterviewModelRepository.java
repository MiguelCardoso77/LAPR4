package core.repositories;

import core.domain.interview.InterviewModel;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface InterviewModelRepository extends DomainRepository<Integer , InterviewModel> {

    Iterable<InterviewModel> allInterviewModels();

    @Override
    Optional<InterviewModel> ofIdentity(Integer id);
}
