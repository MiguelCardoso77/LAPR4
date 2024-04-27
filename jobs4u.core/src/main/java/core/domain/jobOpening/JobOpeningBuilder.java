package core.domain.jobOpening;

import core.domain.company.Company;
import core.domain.company.CompanyName;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ScopeMetadata;

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

    public JobOpeningBuilder withAll(String jobReference, String description, int vacanciesNumber,
                                     String address, Mode mode, ContractType contractType, String titleOrFunction,
                                    Company company) {
        this.jobReference = new JobReference(jobReference);
        this.description = new Description(description);
        this.vacanciesNumber = new VacanciesNumber(vacanciesNumber);
        this.address = new Address(address);
        this.mode = mode;
        this.contractType = contractType;
        this.titleOrFunction = new TitleOrFunction(titleOrFunction);
        this.company = company;
        return this;
    }

    public JobOpening build() {
        JobOpening jobOpening;

        if (jobReference == null || description == null || vacanciesNumber == null || address == null || mode == null || contractType == null || titleOrFunction == null) {
            LOGGER.error("Missing mandatory information to build a JobOpening");
            return null;
        } else {
            LOGGER.debug("Building JobOpening with reference {}, description {}, vacancies number {}, adress {}, mode {}, contract type {}, title or function {}", jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, company);
            jobOpening = new JobOpening(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, company);
        }

        return jobOpening;
    }
}
