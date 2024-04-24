package core.domain.customer;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Represents the designation of a company.
 * <p>
 * This class encapsulates the designation of a company. It ensures that the company designation follows a valid format
 * and provides methods for creating, accessing, comparing, and representing company names.
 * </p>
 * <p>
 * Instances of this class are immutable.
 * </p>
 *
 * @author Diogo Ribeiro 1220812@isep.ipp.pt
 */
@Embeddable
public final class CompanyName implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    private static final Pattern VALID_NAME_REGEX = Pattern.compile("^[\\pL\\pM\\p{Nl}][\\pL\\pM\\p{Nl} ',.\\-]*$", 2);
    private final String designation;
    /**
     * Creates a new {@code CompanyName} instance with the given designation.
     *
     * @param designation the designation of the company
     * @return a new {@code CompanyName} instance
     * @throws IllegalArgumentException if the designation is null or empty, or if it does not match the valid name regex
     */
    protected CompanyName(final String designation) {
        Preconditions.nonEmpty(designation, "Designation should neither be null nor empty");
        Preconditions.matches(VALID_NAME_REGEX, designation, "Invalid Last Name: " + designation);
        this.designation = designation;
    }
    /**
     * Constructs a new CompanyName instance with a null designation.
     * <p>
     * This constructor is marked as protected to prevent external classes from directly instantiating a CompanyName
     * instance with a null designation. It is primarily used for ORM frameworks like JPA, which may require a no-argument
     * constructor for entity mapping purposes. For creating CompanyName instances with a non-null designation, use the
     * {@code valueOf} static factory method.
     * </p>
     */
    protected CompanyName(){
        this.designation = null;
    }

    /**
     * Creates a new CompanyName instance with the specified designation.
     * <p>
     * This static factory method creates and returns a new CompanyName instance with the provided designation. It ensures
     * that the designation is not null or empty, and it adheres to the valid name regex pattern. If the provided
     * designation is invalid, an IllegalArgumentException is thrown.
     * </p>
     *
     * @param designation the designation of the company
     * @return a new CompanyName instance
     * @throws IllegalArgumentException if the designation is null or empty, or if it does not match the valid name regex
     */
    public static CompanyName valueOf(final String designation) {
        return new CompanyName(designation);
    }
    /**
     * Returns the string representation of the company name.
     *
     * @return the designation of the company
     */
    public String toString() {
        return this.designation;
    }
    /**
     * Returns the designation of the company.
     *
     * @return the designation of the company
     */
    public String designation() {
        return this.designation;
    }
    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two {@code CompanyName} objects are considered equal if they have the same designation.
     * </p>
     *
     * @param o the object to compare with
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyName that = (CompanyName) o;

        return Objects.equals(designation, that.designation);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((designation == null) ? 0 : designation.hashCode());
        return result;
    }
}
