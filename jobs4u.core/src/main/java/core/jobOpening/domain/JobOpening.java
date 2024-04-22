package core.jobOpening.domain;

import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

@Entity
public class JobOpening implements AggregateRoot<JobReference> {

    @EmbeddedId
    private JobReference jobReference;

    private Description description;
    private VacanciesNumber vacanciesNumber;
    private Adress adress;
    private Mode mode;
    private ContractType contractType;
    private TitleOrFunction titleOrFunction;

    JobOpening(JobReference jobReference, Description description, VacanciesNumber vacanciesNumber, Adress adress, Mode mode, ContractType contractType, TitleOrFunction titleOrFunction) {
        this.jobReference = jobReference;
        this.description = description;
        this.vacanciesNumber = vacanciesNumber;
        this.adress = adress;
        this.mode = mode;
        this.contractType = contractType;
        this.titleOrFunction = titleOrFunction;
    }

    protected JobOpening() {
        // for ORM only
    }

    public JobReference identity() {
        return null;
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
                && vacanciesNumber.equals(that.vacanciesNumber) && adress.equals(that.adress)
                && mode.equals(that.mode) && contractType.equals(that.contractType)
                && titleOrFunction.equals(that.titleOrFunction);
    }
}
