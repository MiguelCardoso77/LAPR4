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
        List<JobOpening> jobOpenings = (List<JobOpening>) jobOpeningRepository.allJobOpenings();
        List<Candidate> candidates = (List<Candidate>) candidateRepository.allCandidates();
        List<SystemUser> users = (List<SystemUser>) userRepository.findAll();

        List<SystemUser> operators = new ArrayList<>();
        for (SystemUser user : users) {
            if (user.hasAny(Jobs4URoles.OPERATOR)) {
                operators.add(user);
            }
        }
        SystemUser operator = operators.get(0);


        registerApplication("1", "FEUPApp1", jobOpenings.get(0), candidates.get(0), operator);
        registerApplication("2", "FEUPApp2", jobOpenings.get(0), candidates.get(1), operator);
        registerApplication("3", "FEUPApp3", jobOpenings.get(0), candidates.get(2), operator);
        registerApplication("4", "FEUP2App1", jobOpenings.get(1), candidates.get(0), operator);
        registerApplication("5", "FEUP2App2", jobOpenings.get(1), candidates.get(1), operator);
        registerApplication("6", "FEUP2App2", jobOpenings.get(1), candidates.get(2), operator);
        registerApplication("7", "ISEPApp1", jobOpenings.get(2), candidates.get(0), operator);
        registerApplication("8", "ISEPApp1", jobOpenings.get(2), candidates.get(1), operator);

        return true;
    }

    private void registerApplication(String rank, String applicationFiles, JobOpening jobReference, Candidate candidate, SystemUser operator) {
        controller.registerApplication(rank, applicationFiles, jobReference, candidate, operator);
    }
}