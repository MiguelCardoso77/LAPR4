package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents the description of a job opening.
 *
 * @author Diana Neves
 */
@Embeddable
public class Description implements ValueObject {
    private String description;

    /**
     * Constructs a Description object with the given description.
     *
     * @param description the description to be set
     * @throws IllegalArgumentException if the description is null or empty
     */
    public Description(final String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description should neither be null nor empty");
        }

        this.description = description;
    }

    /**
     * Default constructor required by ORM frameworks.
     */
    protected Description() {
        // for ORM
    }

    /**
     * Static factory method to create a Description object.
     *
     * @param description the description to be set
     * @return Description object
     */
    public static Description valueOf(final String description) {
        return new Description(description);
    }

    /**
     * Checks if this Description is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Description)) {
            return false;
        }

        final Description that = (Description) o;
        return this.description.equals(that.description);
    }

    /**
     * Generates a hash code value for this Description object.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    /**
     * Returns the string representation of this Description object.
     *
     * @return the description string
     */
    @Override
    public String toString() {
        return description;
    }
}
