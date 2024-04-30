package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents a job title or function.
 *
 * @author Diana Neves
 */
@Embeddable
public class TitleOrFunction implements ValueObject {
    private String titleOrFunction;

    /**
     * Constructs a {@code TitleOrFunction} with the specified title or function.
     *
     * @param titleOrFunction The title or function string.
     * @throws IllegalArgumentException If the input title or function is null or empty.
     */
    public TitleOrFunction(final String titleOrFunction) {
        if (titleOrFunction == null || titleOrFunction.isEmpty()) {
            throw new IllegalArgumentException("Title or Function should neither be null nor empty");
        }

        this.titleOrFunction = titleOrFunction;
    }

    /**
     * Default constructor for ORM (Object-Relational Mapping) purposes.
     */
    protected TitleOrFunction() {
        // for ORM
    }

    /**
     * Creates a {@code TitleOrFunction} instance from the given title or function string.
     *
     * @param titleOrFunction The title or function string.
     * @return A new {@code TitleOrFunction} instance.
     */
    public static TitleOrFunction valueOf(final String titleOrFunction) {
        return new TitleOrFunction(titleOrFunction);
    }

    /**
     * Checks if this title or function is equal to another object.
     *
     * @param o The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TitleOrFunction)) {
            return false;
        }

        final TitleOrFunction that = (TitleOrFunction) o;
        return this.titleOrFunction.equals(that.titleOrFunction);
    }

    /**
     * Computes the hash code for this title or function.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this.titleOrFunction.hashCode();
    }

    /**
     * Returns the string representation of this title or function.
     *
     * @return The title or function string.
     */
    @Override
    public String toString() {
        return this.titleOrFunction;
    }
}
