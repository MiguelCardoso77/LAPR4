package bootstrappers.bootstraping;

import core.application.controllers.ApplicationRegisterController;
import core.domain.candidate.Candidate;
import core.domain.jobOpening.JobOpening;
import core.domain.user.Jobs4URoles;
import core.persistence.PersistenceContext;
import core.repositories.CandidateRepository;
import core.repositories.JobOpeningRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.actions.Action;
import java.util.ArrayList;
import java.util.List;

public class ApplicationsBootstrapper implements Action {

    final ApplicationRegisterController controller = new ApplicationRegisterController();
    final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();
    final UserRepository userRepository = PersistenceContext.repositories().users();

    @Override
    public boolean execute() {
        String path = "/project/IBM";
        List<JobOpening> jobOpenings = (List<JobOpening>) jobOpeningRepository.allJobOpenings();
        List<Candidate> candidates = (List<Candidate>) candidateRepository.allCandidates();
        List<SystemUser> users = (List<SystemUser>) userRepository.findAll();
        List<SystemUser> operators = new ArrayList<>();
        for (SystemUser user : users){
            if (user.hasAny(Jobs4URoles.OPERATOR)){
                operators.add(user);
            }
        }
        JobOpening jobOpening = jobOpenings.get(0);
        Candidate candidate = candidates.get(0);
        SystemUser operator = operators.get(0);
        registerApplication("9", path,jobOpening,candidate,operator);

        return true;
    }

    private void registerApplication(String rank, String applicationFiles, JobOpening jobReference,
                                     Candidate candidate, SystemUser operator){
        controller.registerApplication(rank,applicationFiles, jobReference,candidate,operator);
    }
}