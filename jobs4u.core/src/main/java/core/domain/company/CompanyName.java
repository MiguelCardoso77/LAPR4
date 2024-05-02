package core.domain.company;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the name of a company.
 * This class is a value object and is embeddable.
 */
@Embeddable
public final class CompanyName implements ValueObject, Serializable, Comparable<CompanyName> {

    @Column(name = "COMPANY_NAME")
    private String designation;

    /**
     * Constructs a CompanyName object with the specified designation.
     *
     * @param designation the designation to set
     * @throws IllegalArgumentException if the designation is null or empty
     */
    public CompanyName(final String designation) {
        Preconditions.nonEmpty(designation, "Designation should neither be null nor empty");
        this.designation = designation;
    }

    /**
     * Protected constructor for ORM usage.
     */
    protected CompanyName() {
        // for ORM only
    }

    /**
     * Factory method to create a CompanyName instance with the specified designation.
     *
     * @param designation the designation to set
     * @return a CompanyName object
     * @throws IllegalArgumentException if the designation is null or empty
     */
    public static CompanyName valueOf(final String designation) {
        return new CompanyName(designation);
    }

    /**
     * Returns the string representation of this CompanyName object.
     *
     * @return the string representation
     */
    public String toString() {
        return this.designation;
    }

    /**
     * Retrieves the designation of this CompanyName object.
     *
     * @return the designation
     */
    public String designation() {
        return this.designation;
    }

    /**
     * Checks if this CompanyName object is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
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
     * Generates a hash code for this CompanyName object.
     *
     * @return the hash code
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((designation == null) ? 0 : designation.hashCode());
        return result;
    }

    /**
     * Compares this CompanyName with another CompanyName for order.
     *
     * @param o the CompanyName to be compared
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(CompanyName o) {
        return this.designation.compareTo(o.designation);
    }
}