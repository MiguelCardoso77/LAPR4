package core.domain.application;

import eapli.framework.domain.model.ValueObject;

/**
 * Represents a rank or position.
 * This class is a value object.
 *
 * @author Tomás Gonçalves
 */

public class Rank implements ValueObject, Comparable<Rank> {
    private int rank;

    /**
     * Constructs a `Rank` instance.
     *
     * @param rank The rank or position.
     * @throws IllegalArgumentException if `rank` is less than 0.
     */

    public Rank(final int rank) {
        if (rank < 0) {
            throw new IllegalArgumentException("Rank should be a positive number");
        }

        this.rank = rank;
    }

    /**
     * Default constructor for ORM (Object-Relational Mapping).
     */
    protected Rank() {
        // for ORM
    }

    /**
     * Creates a `Rank` instance from a given integer value.
     *
     * @param rank The rank or position.
     * @return A `Rank` instance.
     */
    public static Rank valueOf(final int rank) {
        return new Rank(rank);
    }

    /**
     * Checks if this `Rank` is equal to another object.
     *
     * @param o The object to compare.
     * @return `true` if equal, otherwise `false`.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rank)) {
            return false;
        }

        final Rank that = (Rank) o;
        return this.rank == that.rank;
    }

    /**
     * Computes the hash code for this `Rank`.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.rank);
    }


    /**
     * Returns the string representation of this `Rank`.
     *
     * @return The rank or position.
     */
    @Override
    public String toString() {
        return Integer.toString(this.rank);
    }

    /**
     * Compares this `Rank` with another `Rank`.
     *
     * @param arg0 The `Rank` to compare.
     * @return A negative integer, zero, or a positive integer as this `Rank` is less than, equal to, or greater than the specified `Rank`.
     */
    @Override
    public int compareTo(final Rank arg0) {
        return Integer.compare(rank, arg0.rank);
    }
}



