package core.domain.application;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.util.List;

/**
 * Represents the requirements of a candidate for a job application.
 *
 * @author Diana Neves
 */
@Embeddable
public class CandidateRequirements implements ValueObject {

    private List<String> candidateRequirements;

    /**
     * Constructs a new {@code CandidateRequirements} with the specified list of requirements.
     *
     * @param candidateRequirements the list of requirements for the candidate.
     */
    public CandidateRequirements(List<String> candidateRequirements) {
        this.candidateRequirements = candidateRequirements;
    }

    /**
     * Default constructor for ORM (Object-Relational Mapping) frameworks.
     */
    protected CandidateRequirements() {
        // for ORM
    }

    /**
     * Checks if this {@code CandidateRequirements} is equal to another object.
     *
     * @param o the object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CandidateRequirements)) {
            return false;
        }
        final CandidateRequirements that = (CandidateRequirements) o;
        return this.candidateRequirements.equals(that.candidateRequirements);
    }

    /**
     * Returns the list of candidate requirements.
     *
     * @return the list of requirements.
     */
    public List<String> candidateRequirements() {
        return this.candidateRequirements;
    }

    /**
     * Returns the hash code of this {@code CandidateRequirements}.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return this.candidateRequirements.hashCode();
    }

    /**
     * Returns a string representation of the candidate requirements.
     *
     * @return a string containing the requirements.
     */
    @Override
    public String toString() {
        return candidateRequirements.toString();
    }

}
