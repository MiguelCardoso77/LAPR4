package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents an interview model, which is a value object.
 */
@Embeddable
public class InterviewModel implements ValueObject {
    private String interviewModel;

    /**
     * Constructs a new InterviewModel object with the specified interview model value.
     *
     * @param interviewModel The interview model value.
     */
    public InterviewModel(String interviewModel) {
        this.interviewModel = interviewModel;
    }

    /**
     * Default constructor required by the ORM framework.
     */
    protected InterviewModel() {
        // for ORM
    }

    /**
     * Checks if this InterviewModel is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterviewModel)) {
            return false;
        }

        final InterviewModel that = (InterviewModel) o;
        return this.interviewModel.equals(that.interviewModel);
    }

    /**
     * Returns a hash code value for this InterviewModel.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.interviewModel.hashCode();
    }

    /**
     * Returns a string representation of this InterviewModel.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return interviewModel;
    }

}
