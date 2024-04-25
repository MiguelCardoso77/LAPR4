package core.domain.company;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Column;

import java.util.Objects;

/**
 * Represents the company number, a unique identifier for a company.
 * This class is a value object.
 */
public class CompanyNumber implements ValueObject, Comparable<CompanyNumber> {
    @Column(name = "COMPANY_ID")
    private long companyNumber;
    /**
     * Constructs a CompanyNumber object with the specified company number.
     *
     * @param companyNumber the company number to set
     * @throws IllegalArgumentException if the company number is not positive
     */

    public CompanyNumber(final long companyNumber){
        if(companyNumber <= 0){
            throw new IllegalArgumentException("Company number should not be empty");
        }
        this.companyNumber = companyNumber + 1;
    }
    /**
     * Protected constructor for ORM usage.
     */
    protected CompanyNumber(){
        // for ORM
    }
    /**
     * Factory method to create a CompanyNumber instance with the specified company number.
     *
     * @param companyNumber the company number to set
     * @return a CompanyNumber object
     * @throws IllegalArgumentException if the company number is not positive
     */
    public static CompanyNumber valueOf(final long companyNumber){
        return new CompanyNumber(companyNumber);
    }
    /**
     * Checks if this CompanyNumber object is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyNumber that = (CompanyNumber) o;
        return companyNumber == that.companyNumber;
    }
    /**
     * Generates a hash code for this CompanyNumber object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(companyNumber);
    }
    /**
     * Returns the string representation of this CompanyNumber object.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        return String.valueOf(companyNumber);
    }
    /**
     * Compares this CompanyNumber object with another CompanyNumber object.
     *
     * @param o the CompanyNumber object to compare to
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(CompanyNumber o) {
        return Long.compare(companyNumber, o.companyNumber);
    }
}
