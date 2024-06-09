package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents a time value, which is a value object.
 *
 * @author Tomás Gonçalves
 */
@Embeddable
public class Time implements ValueObject {
    private Integer time;

    /**
     * Constructs a new Time object with the specified time value.
     *
     * @param time The time value.
     */
    public Time(Integer time){
        this.time = time;
    }

    /**
     * Default constructor required by the ORM framework.
     */
    protected Time(){
        // for ORM
    }

    /**
     * Checks if this Time is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o){
        if (this == o) {
            return true;
        }
        if (!(o instanceof Time)){
            return false;
        }

        final Time that = (Time) o;
        return this.time.equals(that.time);
    }

    /**
     * Returns a hash code value for this Time.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return this.time.hashCode();
    }

    /**
     * Returns a string representation of this Time.
     *
     * @return A string representation of this object.
     */
    @Override
    public String toString() { return "Time = " + time; }

    /**
     * Retrieves the time value.
     *
     * @return The time value.
     */
    public Integer time() {
        return time;
    }
}
