package bootstrappers.bootstraping;

import core.application.controllers.ApplicationRegisterController;
import core.application.controllers.UploadRequirementsAnswersController;
import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.candidate.Candidate;
import core.domain.jobOpening.JobOpening;
import core.domain.user.Jobs4URoles;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import core.repositories.CandidateRepository;
import core.repositories.JobOpeningRepository;
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

        registerApplication("1", "fileBot_OutputDirectory/IBM-000123/1", jobOpenings.get(0), candidates.get(0), operator);
        registerApplication("Not Ranked", "FEUPApp2", jobOpenings.get(0), candidates.get(1), operator);
        registerApplication("Not Ranked", "FEUPApp3", jobOpenings.get(0), candidates.get(2), operator);
        registerApplication("4", "FEUP2App1", jobOpenings.get(1), candidates.get(0), operator);
        registerApplication("Not Ranked", "FEUP2App2", jobOpenings.get(1), candidates.get(1), operator);
        registerApplication("6", "FEUP2App3", jobOpenings.get(1), candidates.get(2), operator);
        registerApplication("Not Ranked", "ISEPApp1", jobOpenings.get(2), candidates.get(0), operator);
        registerApplication("8", "ISEPApp2", jobOpenings.get(2), candidates.get(1), operator);

        List<Application> applications = (List<Application>) applicationRepository.allApplications();

        addRequirements("jobs4u.core/src/main/resources/requirements/requirements4.txt", applications.get(3));
        addRequirements("jobs4u.core/src/main/resources/requirements/requirements3.txt", applications.get(2));
        addRequirements("jobs4u.core/src/main/resources/requirements/requirements2.txt", applications.get(1));
        addRequirements("jobs4u.core/src/main/resources/requirements/requirements1.txt", applications.get(0));
        addRequirements("jobs4u.core/src/main/resources/requirements/requirements3.txt", applications.get(4));
        addRequirements("jobs4u.core/src/main/resources/requirements/requirements5.txt", applications.get(5));
        addRequirements("jobs4u.core/src/main/resources/requirements/requirements4.txt", applications.get(6));
        addRequirements("jobs4u.core/src/main/resources/requirements/requirements4.txt", applications.get(7));

        changeApplicationStatus(applications.get(0), Status.ACCEPTED);
        changeApplicationStatus(applications.get(1), Status.DECLINED);
        changeApplicationStatus(applications.get(2), Status.ACCEPTED);
        changeApplicationStatus(applications.get(3), Status.PENDING);
        changeApplicationStatus(applications.get(4), Status.PENDING);
        changeApplicationStatus(applications.get(5), Status.PENDING);
        changeApplicationStatus(applications.get(6), Status.SUBMITTED);
        changeApplicationStatus(applications.get(7), Status.SUBMITTED);

        return true;
    }

    private void registerApplication(String rank, String applicationFiles, JobOpening jobReference, Candidate candidate, SystemUser operator) {
        controller.registerApplication(rank, applicationFiles, jobReference, candidate, operator);
        LOGGER.debug("»»» Registering application {}", applicationFiles);
    }

    private void addRequirements(String candidateRequirementsPath, Application application) {
        List<String> requirements = uploadRequirementsAnswersController.readFile(candidateRequirementsPath);
        uploadRequirementsAnswersController.uploadRequirements(requirements, application);
        LOGGER.debug("»»» Uploading requirements {}", candidateRequirementsPath);
    }

    private void changeApplicationStatus(Application application, Status status) {
        application.changeStatus(status);
        applicationRepository.save(application);
        LOGGER.debug("»»» Changing application status {}", status);
    }

}