package bootstrappers.bootstraping;

import core.application.controllers.AddJobOpeningController;
import core.application.controllers.AddJobRequirementSpecificationController;
import core.application.controllers.ListJobRequirementsSpecificationController;
import core.application.controllers.UpdateJobOpeningRequirementsController;
import core.domain.company.Company;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.JobReference;
import core.domain.jobOpening.Mode;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.persistence.PersistenceContext;
import core.repositories.CompanyRepository;
import core.repositories.JobRequirementsSpecificationRepository;
import eapli.framework.actions.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JobsBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobsBootstrapper.class);
    final AddJobOpeningController controller = new AddJobOpeningController();
    private final CompanyRepository companyRepository = PersistenceContext.repositories().companies();

    final ListJobRequirementsSpecificationController theController = new ListJobRequirementsSpecificationController();
    private final JobRequirementsSpecificationRepository repo = PersistenceContext.repositories().jobRequirements();

    final UpdateJobOpeningRequirementsController updateJobOpeningRequirementsController = new UpdateJobOpeningRequirementsController();

    @Override
    public boolean execute() {
        List<Company> companies = (List<Company>) companyRepository.allCompanies();
        List<JobRequirementsSpecification> jobRequirementsSpecifications = (List<JobRequirementsSpecification>) repo.findAll();

        registerJobOpening("FEU-000123", "Jogador da Bola", 3, "Estádio do Dragão", Mode.ON_SITE, ContractType.FULL_TIME, "Ponta de Lança", companies.get(0));
        registerJobOpening("FEU-000124", "Jogador de Basquetebol", 2, "Estádio da Luz", Mode.ON_SITE, ContractType.PART_TIME , "Base", companies.get(1));
        registerJobOpening("ISE-000123", "Engenheiro Informático", 1, "Rua do Amial", Mode.ON_SITE, ContractType.PART_TIME, "Engenheiro Informático", companies.get(2));
        registerJobOpening("FMU-000123", "Chefe de Cozinha", 2, "Bar da Ae", Mode.ON_SITE, ContractType.FULL_TIME, "Chef", companies.get(3));
        registerJobOpening("IBM-000123", "Software Engineer", 4, "IBM st.", Mode.HYBRID, ContractType.FULL_TIME, "Software Engineer", companies.get(0));

        JobReference jobReference = JobReference.stringToJobReference("FEU-000001");
        JobReference jobReference1 = JobReference.stringToJobReference("FEU-000002");
        JobReference jobReference2 = JobReference.stringToJobReference("ISE-000003");

        updateJobOpeningRequirementsController.updateJobOpening(jobReference, jobRequirementsSpecifications.get(0));
        updateJobOpeningRequirementsController.updateJobOpening(jobReference1, jobRequirementsSpecifications.get(1));
        updateJobOpeningRequirementsController.updateJobOpening(jobReference2, jobRequirementsSpecifications.get(2));

        return true;
    }

    private void registerJobOpening(String companyName, String description, int vacanciesNumber, String address, Mode mode, ContractType contractType, String titleOrFunction, Company company) {
        JobReference jobReference = new JobReference(companyName, true);
        controller.addJobOpening(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, company);
        LOGGER.debug("»»» {}", jobReference);
    }
}