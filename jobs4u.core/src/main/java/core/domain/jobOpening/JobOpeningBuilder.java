package core.domain.jobOpening;

import core.domain.customer.Customer;
import core.domain.process.Process;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private Customer customer;
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
     * @param customer                    The customer associated to the job opening.
     * @param process                    The process for the job opening.
     * @return This builder instance.
     */
    public JobOpeningBuilder withAll(JobReference jobReference, String description, int vacanciesNumber,
                                     String address, Mode mode, ContractType contractType, String titleOrFunction,
                                     Customer customer, Process process) {
        this.jobReference = jobReference;
        this.description = new Description(description);
        this.vacanciesNumber = new VacanciesNumber(vacanciesNumber);
        this.address = new Address(address);
        this.mode = mode;
        this.contractType = contractType;
        this.titleOrFunction = new TitleOrFunction(titleOrFunction);
        this.customer = customer;
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

        if (jobReference == null || description == null || vacanciesNumber == null || address == null || mode == null || contractType == null || titleOrFunction == null || customer == null) {
            LOGGER.error("Missing mandatory information to build a JobOpening");
            return null;
        } else {
            jobOpening = new JobOpening(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, customer, null, process, null);
        }
        return jobOpening;
    }
}