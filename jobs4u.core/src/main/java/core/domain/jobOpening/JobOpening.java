package core.domain.jobOpening;

import core.domain.company.Company;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents a job opening in the system.
 *
 * @author 1220812@isep.ipp.pt
 */
@Entity
@Table(name = "JOB_OPENING")
public class JobOpening implements AggregateRoot<JobReference> {

    @EmbeddedId
    @Column(name = "JOB_REFERENCE")
    private JobReference jobReference;

    @Column(name = "DESCRIPTION")
    private Description description;

    @Column(name = "VACANCIES_NUMBER")
    private VacanciesNumber vacanciesNumber;

    @Column(name = "ADDRESS")
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "MODE")
    private Mode mode;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONTRACT_TYPE")
    private ContractType contractType;

    @Column(name = "TITLE_OR_FUNCTION")
    private TitleOrFunction titleOrFunction;

    @ManyToOne
    @JoinColumn(name = "COMPANY_NUMBER")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "JOB_REQUIREMENTS")
    private JobRequirementsSpecification jobRequirementsSpecification;
    /**
     * Constructs a new job opening with the provided attributes.
     *
     * @param jobReference               The reference of the job opening.
     * @param description                The description of the job opening.
     * @param vacanciesNumber            The number of vacancies for the job opening.
     * @param address                    The address of the job opening.
     * @param mode                       The mode of the job opening (e.g., full-time, part-time).
     * @param contractType               The contract type of the job opening.
     * @param titleOrFunction            The title or function of the job opening.
     * @param company                    The company offering the job opening.
     * @param jobRequirementsSpecification The job requirements specification for the job opening.
     */
    public JobOpening(JobReference jobReference, Description description, VacanciesNumber vacanciesNumber,
                      Address address, Mode mode, ContractType contractType, TitleOrFunction titleOrFunction, Company company, JobRequirementsSpecification jobRequirementsSpecification) {
        this.jobReference = jobReference;
        this.description = description;
        this.vacanciesNumber = vacanciesNumber;
        this.address = address;
        this.mode = mode;
        this.contractType = contractType;
        this.titleOrFunction = titleOrFunction;
        this.company = company;
        this.jobRequirementsSpecification = jobRequirementsSpecification;
    }

    protected JobOpening() {
        // for ORM only
    }
    /**
     * Gets the job reference.
     *
     * @return The job reference.
     */
    public JobReference identity() {
        return jobReference;
    }
    /**
     * Gets the description of the job opening.
     *
     * @return The description of the job opening.
     */



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JobReference otherJobReference = (JobReference) obj;
        return jobReference.equals(otherJobReference);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }
    /**
     * Gets the job reference.
     *
     * @return The job reference.
     */
    public JobReference jobReference() {
        return jobReference;
    }
    /**
     * Gets the description of the job opening.
     *
     * @return The description of the job opening.
     */
    public Description description() {
        return description;
    }
    /**
     * Gets the number of vacancies for the job opening.
     *
     * @return The number of vacancies.
     */
    public VacanciesNumber vacanciesNumber() {
        return vacanciesNumber;
    }
    /**
     * Gets the address of the job opening.
     *
     * @return The address of the job opening.
     */
    public Address address() {
        return address;
    }
    /**
     * Gets the mode of the job opening.
     *
     * @return The mode of the job opening.
     */
    public Mode mode() {
        return mode;
    }
    /**
     * Gets the contract type of the job opening.
     *
     * @return The contract type of the job opening.
     */
    public ContractType contractType() {
        return contractType;
    }
    /**
     * Gets the title or function of the job opening.
     *
     * @return The title or function of the job opening.
     */
    public TitleOrFunction titleOrFunction() {
        return titleOrFunction;
    }
    /**
     * Gets the job requirements specification for the job opening.
     *
     * @return The job requirements specification for the job opening.
     */
    public Company company() {
        return company;
    }
    /**
     * Gets the job requirements specification for the job opening.
     *
     * @return The job requirements specification for the job opening.
     */
    public JobRequirementsSpecification jobRequirementsSpecification(){
        return jobRequirementsSpecification;
    }
    /**
     * Updates the job requirements specification for the job opening.
     *
     * @param jobRequirementsSpecification The new job requirements specification.
     */
    public void updateJobRequirements(JobRequirementsSpecification jobRequirementsSpecification){
        this.jobRequirementsSpecification = jobRequirementsSpecification;
    }

    public boolean sameReference(JobReference jobReference){
        String jobReference1 = String.valueOf(jobReference);
        String jobOpeningReference = String.valueOf(this.jobReference);
        if(jobOpeningReference.equals(jobReference1)){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "jobReference=" + jobReference +
                ", " + description +
                ", vacancies number = " + vacanciesNumber +
                ", address = " + address +
                ", mode = " + mode +
                ", contract type = " + contractType +
                ", title = " + titleOrFunction +
                ", company = " + company +
                ", job requirements specification = " + jobRequirementsSpecification;
    }

}