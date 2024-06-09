package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.util.List;

/**
 * Represents the answers given in an interview.
 * This class provides methods for getting the list of answers, checking equality, generating hashcode, and getting a string representation of the answers.
 *
 * @author Miguel Cardoso
 */

@Embeddable
public class InterviewAnswers implements ValueObject {
    private List<String> answers;
    /**
     * Constructs an InterviewAnswers object with the specified list of answers.
     *
     * @param answers The list of answers.
     */
    public InterviewAnswers(List<String> answers) {
        this.answers = answers;
    }
    /**
     * Protected constructor for ORM usage.
     */
    protected InterviewAnswers() {
        // for ORM
    }
    /**
     * Checks if this InterviewAnswers object is equal to the specified object.
     *
     * @param o The object to compare this InterviewAnswers object to.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterviewAnswers)) {
            return false;
        }

        final InterviewAnswers that = (InterviewAnswers) o;
        return this.answers.equals(that.answers);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.answers.hashCode();
    }
    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return answers.toString();
    }
    /**
     * Retrieves the list of answers.
     *
     * @return The list of answers.
     */
    public List<String> answersList() {
        return answers;
    }
}
