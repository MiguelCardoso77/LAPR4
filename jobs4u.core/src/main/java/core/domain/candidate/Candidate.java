package core.domain.candidate;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

/**
 * Represents a candidate entity in the system.
 *
 * @author Miguel Cardoso
 */
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

    /**
     * Constructs a new Candidate object with the specified user, telephone number, and curriculum.
     *
     * @param user            The system user associated with the candidate.
     * @param telephoneNumber The telephone number of the candidate.
     * @param curriculum      The curriculum of the candidate.
     */
    public Candidate(final SystemUser user, final TelephoneNumber telephoneNumber, final Curriculum curriculum) {
        Preconditions.nonNull(user);
        Preconditions.nonNull(telephoneNumber);
        Preconditions.nonNull(curriculum);

        this.systemUser = user;
        this.telephoneNumber = telephoneNumber;
        this.curriculum = curriculum;
    }

    protected Candidate() {
        // for ORM only
    }

    /**
     * Retrieves the system user associated with this candidate.
     *
     * @return The system user.
     */
    public SystemUser user() {
        return this.systemUser;
    }

    /**
     * Retrieves the curriculum of this candidate.
     *
     * @return The curriculum.
     */
    public Curriculum curriculum() {
        return this.curriculum;
    }

    /**
     * Retrieves the identity of this candidate, which is represented by their telephone number.
     *
     * @return The telephone number representing the identity of this candidate.
     */
    @Override
    public TelephoneNumber identity() {
        return telephoneNumber;
    }

    /**
     * Checks if this candidate is equal to another object.
     *
     * @param other The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
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

    /**
     * Checks if this candidate is equal to another object.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
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

    /**
     * Returns a string representation of this candidate.
     *
     * @return A string representation of this candidate.
     */
    @Override
    public String toString() {
        return "Candidate{" +
                "telephoneNumber=" + telephoneNumber +
                ", curriculum=" + curriculum +
                ", systemUser=" + systemUser.toString() +
                '}';
    }
}
