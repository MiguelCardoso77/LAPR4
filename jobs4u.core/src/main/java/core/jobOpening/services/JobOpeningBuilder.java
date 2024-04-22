package core.jobOpening.services;

import core.jobOpening.domain.*;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobOpeningBuilder implements DomainFactory<JobOpening> {
    private static final Logger LOGGER = LogManager.getLogger(JobOpening.class);
    private JobReference jobReference;
    private Description description;
    private VacanciesNumber vacanciesNumber;
    private Adress adress;
    private Mode mode;
    private ContractType contractType;
    private TitleOrFunction titleOrFunction;

    public JobOpeningBuilder withAll(int jobReference, String description, int vacanciesNumber, String adress, Mode mode, ContractType contractType, String titleOrFunction) {
        this.jobReference = new JobReference(jobReference);
        this.description = new Description(description);
        this.vacanciesNumber = new VacanciesNumber(vacanciesNumber);
        this.adress = new Adress(adress);
        this.mode = mode;
        this.contractType = contractType;
        this.titleOrFunction = new TitleOrFunction(titleOrFunction);
        return this;
    }

    public JobOpening build() {
        JobOpening jobOpening;

        if (jobReference == null || description == null || vacanciesNumber == null || adress == null || mode == null || contractType == null || titleOrFunction == null) {
            LOGGER.error("Missing mandatory information to build a JobOpening");
            return null;
        } else {
            LOGGER.debug("Building JobOpening with reference {}, description {}, vacancies number {}, adress {}, mode {}, contract type {}, title or function {}", jobReference, description, vacanciesNumber, adress, mode, contractType, titleOrFunction);
            jobOpening = new JobOpening(jobReference, description, vacanciesNumber, adress, mode, contractType, titleOrFunction);
        }

        return jobOpening;
    }
}
