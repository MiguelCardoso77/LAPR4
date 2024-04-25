package core.domain.candidate;

import core.domain.customer.TelephoneNumber;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.persistence.*;

@Entity
@Table(name = "CANDIDATE")
public class Candidate implements AggregateRoot<TelephoneNumber> {
    @EmbeddedId
    @Column(name = "TELEPHONE_NUMBER")
    private TelephoneNumber telephoneNumber;

    @Column(name = "CURRICULUM")
    private Curriculum curriculum;

    @OneToOne
    @JoinColumn(name = "EMAIL")
    private SystemUser systemUser;

    public Candidate(final SystemUser user, final TelephoneNumber telephoneNumber, final Curriculum curriculum) {
        if (telephoneNumber == null || user == null || curriculum == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.telephoneNumber = telephoneNumber;
        this.curriculum = curriculum;
    }

    protected Candidate() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    public Curriculum curriculum() {
        return this.curriculum;
    }

    @Override
    public TelephoneNumber identity() {
        return telephoneNumber;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        final Candidate candidate = (Candidate) other;
        return telephoneNumber.equals(candidate.telephoneNumber) && curriculum.equals(candidate.curriculum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Candidate that = (Candidate) o;

        return telephoneNumber.equals(that.telephoneNumber) && curriculum.equals(that.curriculum);
    }
}
