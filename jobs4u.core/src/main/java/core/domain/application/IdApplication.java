package core.domain.application;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;

/**
 * Represents an application ID.
 * This value object encapsulates the unique identifier for an application.
 *
 * @author Tomás Gonçalves
 */
public class IdApplication implements ValueObject, Comparable<IdApplication> {
    private long idApplication;

    /**
     * Constructs an `IdApplication` with the given ID.
     *
     * @param idApplication the application ID (must be positive)
     * @throws IllegalArgumentException if the ID is non-positive
     */
    public IdApplication(final long idApplication) {
        if (idApplication <= 0) {
            throw new IllegalArgumentException("IdApplication should not be empty");
        }

        this.idApplication = idApplication + 1;
    }

    /**
     * Default constructor for ORM mapping.
     */
    protected IdApplication() {
        // for ORM
    }

    /**
     * Creates an `IdApplication` from the given ID.
     *
     * @param idApplication the application ID
     * @return the corresponding `IdApplication` instance
     */
    public static IdApplication valueOf(final long idApplication) {
        return new IdApplication(idApplication);
    }

    /**
     * Checks if this `IdApplication` is equal to another object.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdApplication that = (IdApplication) o;
        return idApplication == that.idApplication;
    }

    /**
     * Computes the hash code for this `IdApplication`.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(idApplication);
    }

    /**
     * Returns a string representation of this `IdApplication`.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return String.valueOf(idApplication);
    }

    /**
     * Compares this `IdApplication` with another `IdApplication`.
     *
     * @param o the other `IdApplication`
     * @return a negative integer, zero, or a positive integer if this `IdApplication` is less than, equal to, or greater than the other
     */
    @Override
    public int compareTo(IdApplication o) {
        return Long.compare(idApplication, o.idApplication);
    }
}
