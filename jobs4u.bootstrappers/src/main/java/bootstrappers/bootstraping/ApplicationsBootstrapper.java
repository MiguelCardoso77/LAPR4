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
        for (SystemUser user : users) {
            if (user.hasAny(Jobs4URoles.OPERATOR)) {
                operators.add(user);
            }
        }
        JobOpening jobOpening = jobOpenings.get(0);
        Candidate candidate = candidates.get(0);
        SystemUser operator = operators.get(0);

        JobOpening jobOpening1 = jobOpenings.get(1);
        Candidate candidate1 = candidates.get(1);

        JobOpening jobOpening2 = jobOpenings.get(2);
        Candidate candidate2 = candidates.get(2);


        registerApplication("9", "/project/FEU1", jobOpening, candidate, operator);
        registerApplication("1", "/project/FMU1", jobOpening1, candidate1, operator);
        registerApplication("3", "/project/FMU2", jobOpening1, candidate2, operator);
        registerApplication("2", "/project/IBM1", jobOpening2, candidate1, operator);


        return true;
    }

    private void registerApplication(String rank, String applicationFiles, JobOpening jobReference, Candidate candidate, SystemUser operator) {
        controller.registerApplication(rank, applicationFiles, jobReference, candidate, operator);
    }
}