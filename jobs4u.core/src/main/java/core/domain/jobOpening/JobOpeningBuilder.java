package core.domain.jobOpening;

import core.domain.company.Company;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.process.Process;
import core.domain.process.ProcessBuilder;
import core.domain.process.ProcessState;
import core.domain.process.ProcessStatus;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

/**
 * A builder class for creating instances of {@link JobOpening}.
 * This builder provides methods for setting various attributes of a job opening
 * and constructs the {@code JobOpening} object when all mandatory information is provided.
 *
 * @author 1220812@isep.ipp.pt
 */
public class JobOpeningBuilder implements DomainFactory<JobOpening> {
    private static final Logger LOGGER = LogManager.getLogger(JobOpening.class);
    private JobReference jobReference;
    private Description description;
    private VacanciesNumber vacanciesNumber;
    private Address address;
    private Mode mode;
    private ContractType contractType;
    private TitleOrFunction titleOrFunction;
    private Company company;
    private JobRequirementsSpecification jobRequirementsSpecification;
    private Process process;

    /**
     * Sets all attributes of the job opening.
     *
     * @param jobReference               The reference of the job opening.
     * @param description                The description of the job opening.
     * @param vacanciesNumber            The number of vacancies for the job opening.
     * @param address                    The address of the job opening.
     * @param mode                       The mode of the job opening (e.g., full-time, part-time).
     * @param contractType               The contract type of the job opening.
     * @param titleOrFunction            The title or function of the job opening.
     * @param company                    The company offering the job opening.
     * @return This builder instance.
     */
    public JobOpeningBuilder withAll(JobReference jobReference, String description, int vacanciesNumber,
                                     String address, Mode mode, ContractType contractType, String titleOrFunction,
                                    Company company, JobRequirementsSpecification jobRequirementsSpecification, Process process) {
        this.jobReference = jobReference;
        this.description = new Description(description);
        this.vacanciesNumber = new VacanciesNumber(vacanciesNumber);
        this.address = new Address(address);
        this.mode = mode;
        this.contractType = contractType;
        this.titleOrFunction = new TitleOrFunction(titleOrFunction);
        this.company = company;
        this.jobRequirementsSpecification = null;
        this.process = process;
        return this;
    }
    /**
     * Constructs a {@code JobOpening} object with the provided attributes.
     *
     * @return The constructed {@code JobOpening} object, or {@code null} if any mandatory information is missing.
     */
    public JobOpening build() {
        JobOpening jobOpening;

        if (jobReference == null || description == null || vacanciesNumber == null || address == null || mode == null || contractType == null || titleOrFunction == null || company == null) {
            LOGGER.error("Missing mandatory information to build a JobOpening");
            return null;
        } else {
            LOGGER.debug("Building JobOpening with reference {}, description {}, vacancies number {}, adress {}, mode {}, contract type {}, title or function {}, company {}", jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, company);
            jobOpening = new JobOpening(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, company, null, process);
        }
        return jobOpening;
    }
}