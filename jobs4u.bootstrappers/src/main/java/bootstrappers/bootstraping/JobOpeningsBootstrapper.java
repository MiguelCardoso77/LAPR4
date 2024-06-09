package bootstrappers.bootstraping;

import core.application.controllers.*;
import core.domain.customer.Customer;
import core.domain.interviewModel.InterviewModel;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.JobReference;
import core.domain.jobOpening.Mode;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.persistence.PersistenceContext;
import core.repositories.CustomerRepository;
import core.repositories.InterviewModelRepository;
import core.repositories.JobRequirementsSpecificationRepository;
import core.repositories.ProcessRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Bootstraps job opening data.
 * This bootstrapper registers sample job openings using the AddJobOpeningController.
 * Requires the CustomerRepository, InterviewModelRepository, JobRequirementsSpecificationRepository,
 * ProcessRepository, AddJobOpeningController, ChangeProcessStateController, SelectInterviewModelController,
 * and UpdateJobOpeningRequirementsController to execute.
 * This class is an Action to be used in the bootstrapping process.
 *
 * @author Diogo Ribeiro
 */
public class JobOpeningsBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobOpeningsBootstrapper.class);

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerUsers();
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModelRepository();
    private final JobRequirementsSpecificationRepository requirementsRepository = PersistenceContext.repositories().jobRequirements();
    private final ProcessRepository processRepository = PersistenceContext.repositories().processRepository();

    private final AddJobOpeningController addJobController = new AddJobOpeningController();
    private final ChangeProcessStateController changeProcessStateController = new ChangeProcessStateController();
    private final SelectInterviewModelController selectInterviewModelController = new SelectInterviewModelController();
    private final UpdateJobOpeningRequirementsController updateJobOpeningRequirementsController = new UpdateJobOpeningRequirementsController();

    /**
     * Executes the bootstrapping process for job opening data.
     * Registers sample job openings using the AddJobOpeningController.
     * @return true if bootstrapping is successful, false otherwise
     */
    @Override
    public boolean execute() {
        List<Customer> customers = (List<Customer>) customerRepository.findAllActive();
        List<InterviewModel> interviewModels = (List<InterviewModel>) interviewModelRepository.findAll();
        List<JobRequirementsSpecification> jobRequirementsSpecifications = (List<JobRequirementsSpecification>) requirementsRepository.findAll();

        registerJobOpening( "Jogador da Bola", 3, "Estádio do Dragão", Mode.ON_SITE, ContractType.FULL_TIME, "Ponta de Lança", customers.get(0));
        registerJobOpening( "Jogador de Basquetebol", 2, "Estádio da Luz", Mode.ON_SITE, ContractType.PART_TIME , "Base", customers.get(1));
        registerJobOpening( "Engenheiro Informático", 1, "Rua do Amial", Mode.ON_SITE, ContractType.PART_TIME, "Engenheiro Informático", customers.get(0));
        registerJobOpening("Chefe de Cozinha", 2, "Bar da Ae", Mode.ON_SITE, ContractType.FULL_TIME, "Chef", customers.get(0));
        registerJobOpening( "Software Engineer", 4, "IBM st.", Mode.HYBRID, ContractType.FULL_TIME, "Software Engineer", customers.get(1));

        JobReference jobReference = JobReference.stringToJobReference("Por-000001");
        JobReference jobReference1 = JobReference.stringToJobReference("Por-000002");
        JobReference jobReference2 = JobReference.stringToJobReference("Por-000003");

        updateJobOpeningRequirementsController.updateJobOpening(jobReference, jobRequirementsSpecifications.get(0));
        updateJobOpeningRequirementsController.updateJobOpening(jobReference1, jobRequirementsSpecifications.get(1));
        updateJobOpeningRequirementsController.updateJobOpening(jobReference2, jobRequirementsSpecifications.get(2));

        selectInterviewModelController.updateInterviewModel(jobReference, interviewModels.get(0));
        selectInterviewModelController.updateInterviewModel(jobReference1, interviewModels.get(1));
        selectInterviewModelController.updateInterviewModel(jobReference2, interviewModels.get(2));

        List<Process> processes = (List<Process>) processRepository.findAll();
        changeProcessStateController.changeProcessState(ProcessState.ANALYSIS, processes.get(0));
        changeProcessStateController.changeProcessState(ProcessState.ANALYSIS, processes.get(1));

        return true;
    }

    /**
     * Registers a job opening using the AddJobOpeningController.
     * @param description the job opening description
     * @param vacanciesNumber the number of vacancies
     * @param address the job opening address
     * @param mode the job opening mode
     * @param contractType the job opening contract type
     * @param titleOrFunction the job opening title or function
     * @param customer the customer that owns the job opening
     */
    private void registerJobOpening(String description, int vacanciesNumber, String address, Mode mode, ContractType contractType, String titleOrFunction, Customer customer) {
        JobReference jobReference = new JobReference(customer.company().companyName().toString(), true);
        addJobController.addJobOpening(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, customer);
        LOGGER.debug("»»» {}", jobReference);
    }
}