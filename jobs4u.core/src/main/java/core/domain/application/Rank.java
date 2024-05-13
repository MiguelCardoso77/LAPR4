package core.domain.application;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

/**
 * Represents a rank or position.
 * This class is a value object.
 *
 * @author Tomás Gonçalves
 */

@Embeddable
public class Rank implements ValueObject {
    private String rank;

    /**
     * Constructs a `Rank` instance.
     *
     * @param rank The rank or position.
     * @throws IllegalArgumentException if `rank` is less than 0.
     */

    public Rank(final String rank) {
        Preconditions.nonNull(rank);

        this.rank = rank;
    }

    public Rank(final int rank) {
        Preconditions.nonNull(rank);
        Preconditions.nonNegative(rank);

        this.rank = String.valueOf(rank);
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
    public static Rank valueOf(final String rank) {
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
        return this.rank.equals(that.rank);
    }


    /**
     * Returns the string representation of this `Rank`.
     *
     * @return The rank or position.
     */
    @Override
    public String toString() {
        return this.rank;
    }
}