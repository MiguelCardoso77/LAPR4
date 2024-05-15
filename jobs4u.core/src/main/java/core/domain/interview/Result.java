package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents the result of an interview, which is a value object.
 *
 * @author tomasgoncalves
 */
@Embeddable
public class Result implements ValueObject {

    private String result;

    /**
     * Constructs a new Result object with the specified result value.
     *
     * @param result The result value.
     */
    public Result(String result) {
        this.result = result;
    }

    /**
     * Default constructor required by the ORM framework.
     */
    protected Result() {
        // for ORM
    }

    /**
     * Checks if this Result is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Result)) {
            return false;
        }

        final Result that = (Result) o;
        return this.result.equals(that.result);
    }

    /**
     * Returns a hash code value for this Result.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.result.hashCode();
    }

    /**
     * Returns a string representation of this Result.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() {
        return result;
    }


}
