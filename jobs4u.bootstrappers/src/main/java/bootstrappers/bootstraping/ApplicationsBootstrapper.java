package bootstrappers.bootstraping;

import core.application.controllers.ApplicationRegisterController;
import core.application.controllers.UploadRequirementsAnswersController;
import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.candidate.Candidate;
import core.domain.jobOpening.JobOpening;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.user.Jobs4URoles;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import core.repositories.CandidateRepository;
import core.repositories.JobOpeningRepository;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationsBootstrapper.class);

    final ApplicationRegisterController controller = new ApplicationRegisterController();
    final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    final CandidateRepository candidateRepository = PersistenceContext.repositories().candidates();
    final UserRepository userRepository = PersistenceContext.repositories().users();
    final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    final UploadRequirementsAnswersController uploadRequirementsAnswersController = new UploadRequirementsAnswersController();

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

        registerApplication("Not Ranked", "App1", jobOpenings.get(0), candidates.get(0), operator);
        registerApplication("1", "App2", jobOpenings.get(0), candidates.get(1), operator);
        registerApplication("4", "App3", jobOpenings.get(0), candidates.get(3), operator);
        registerApplication("Not Ranked", "App4", jobOpenings.get(0), candidates.get(4), operator);
        registerApplication("4", "App5", jobOpenings.get(1), candidates.get(0), operator);
        registerApplication("Not Ranked", "App6", jobOpenings.get(1), candidates.get(1), operator);
        registerApplication("6", "App7", jobOpenings.get(1), candidates.get(2), operator);
        registerApplication("3", "App8", jobOpenings.get(1), candidates.get(3), operator);
        registerApplication("Not Ranked", "App9", jobOpenings.get(1), candidates.get(4), operator);
        registerApplication("Not Ranked", "App10", jobOpenings.get(2), candidates.get(0), operator);
        registerApplication("2", "App11", jobOpenings.get(2), candidates.get(1), operator);

        List<Application> applications = (List<Application>) applicationRepository.allApplications();

        insertRequirementsResponses("jobs4u.core/src/main/resources/answered/requirements/requirements1.txt", applications.get(0));
        insertRequirementsResponses(("jobs4u.core/src/main/resources/answered/requirements/requirements2.txt"), applications.get(1));
        insertRequirementsResponses(("jobs4u.core/src/main/resources/answered/requirements/requirements1.txt"), applications.get(2));
        insertRequirementsResponses(("jobs4u.core/src/main/resources/answered/requirements/requirements2.txt"), applications.get(3));

        changeApplicationStatus(applications.get(0), Status.RECEIVED);
        changeApplicationStatus(applications.get(1), Status.ACCEPTED);
        changeApplicationStatus(applications.get(2), Status.DECLINED);
        changeApplicationStatus(applications.get(3), Status.RECEIVED);
        changeApplicationStatus(applications.get(4), Status.ACCEPTED);
        changeApplicationStatus(applications.get(5), Status.RECEIVED);
        changeApplicationStatus(applications.get(6), Status.DECLINED);
        changeApplicationStatus(applications.get(7), Status.ACCEPTED);
        changeApplicationStatus(applications.get(8), Status.RECEIVED);
        changeApplicationStatus(applications.get(9), Status.RECEIVED);
        changeApplicationStatus(applications.get(10), Status.ACCEPTED);

        return true;
    }

    private void registerApplication(String rank, String applicationFiles, JobOpening jobReference, Candidate candidate, SystemUser operator) {
        controller.registerApplication(rank, applicationFiles, jobReference, candidate, operator);
        LOGGER.debug("»»» Registering application {}", applicationFiles);
    }

    private void insertRequirementsResponses(String candidateRequirementsPath, Application application) {
        List<String> requirements = uploadRequirementsAnswersController.retrieveResponseRequirements(candidateRequirementsPath);
        uploadRequirementsAnswersController.uploadRequirements(requirements, application);
        LOGGER.debug("»»» Changing requirements responses {}", candidateRequirementsPath);
    }

    private void changeApplicationStatus(Application application, Status status) {
        application.changeStatus(status);
        applicationRepository.save(application);
        LOGGER.debug("»»» Changing application status to {}", status);
    }

}