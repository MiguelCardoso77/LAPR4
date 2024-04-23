package core.domain.jobOpening;

import core.domain.application.Application;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "JOBOPENING")
public class JobOpening implements AggregateRoot<JobReference> {

    @EmbeddedId
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

    public JobOpening(JobReference jobReference, Description description, VacanciesNumber vacanciesNumber, Address address, Mode mode, ContractType contractType, TitleOrFunction titleOrFunction) {
        this.jobReference = jobReference;
        this.description = description;
        this.vacanciesNumber = vacanciesNumber;
        this.address = address;
        this.mode = mode;
        this.contractType = contractType;
        this.titleOrFunction = titleOrFunction;
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
                && titleOrFunction.equals(that.titleOrFunction);
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

}