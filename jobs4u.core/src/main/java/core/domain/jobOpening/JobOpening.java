package core.domain.jobOpening;

import core.domain.company.Company;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

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

    public JobOpening(JobReference jobReference, Description description, VacanciesNumber vacanciesNumber,
                      Address address, Mode mode, ContractType contractType, TitleOrFunction titleOrFunction, Company company) {
        this.jobReference = jobReference;
        this.description = description;
        this.vacanciesNumber = vacanciesNumber;
        this.address = address;
        this.mode = mode;
        this.contractType = contractType;
        this.titleOrFunction = titleOrFunction;
        this.company = company;
    }

    protected JobOpening() {
        // for ORM only
    }

    public JobReference identity() {
        return jobReference;
    }

    @Override
    public boolean sameAs(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof JobOpening)) {
            return false;
        }

        final JobOpening that = (JobOpening) other;

        return jobReference.equals(that.jobReference) && description.equals(that.description)
                && vacanciesNumber.equals(that.vacanciesNumber) && address.equals(that.address)
                && mode.equals(that.mode) && contractType.equals(that.contractType)
                && titleOrFunction.equals(that.titleOrFunction) && company.equals(company);
    }

    public JobReference jobReference() {
        return jobReference;
    }


    public Description description() {
        return description;
    }

    public VacanciesNumber vacanciesNumber() {
        return vacanciesNumber;
    }

    public Address address() {
        return address;
    }

    public Mode mode() {
        return mode;
    }

    public ContractType contractType() {
        return contractType;
    }

    public TitleOrFunction titleOrFunction() {
        return titleOrFunction;
    }

    public Company company() { return company;}

}