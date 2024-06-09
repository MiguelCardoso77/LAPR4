package core.domain.candidate;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

/**
 * The telephone number of a candidate.
 *
 * @author Miguel Cardoso
 */
@Embeddable
public class TelephoneNumber implements ValueObject, Comparable<TelephoneNumber> {
    private String number;

    /**
     * Constructs a telephone number.
     * @param telephoneNumber the telephone number
     */
    public TelephoneNumber(final String telephoneNumber) {
        Preconditions.nonNull(telephoneNumber);
        this.number = telephoneNumber;
    }

    protected TelephoneNumber() {
        // for ORM
    }

    /**
     * ValueOf method for TelephoneNumber
     *
     * @param telephoneNumber the telephone number to create an instance of TelephoneNumber
     * @return TelephoneNumber instance
     */
    public static TelephoneNumber valueOf(final String telephoneNumber) {
        return new TelephoneNumber(telephoneNumber);
    }

    /**
     * Checks if this TelephoneNumber is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TelephoneNumber)) {
            return false;
        }

        final TelephoneNumber that = (TelephoneNumber) o;
        return this.number.equals(that.number);
    }

    /**
     * Computes the hash code of this TelephoneNumber.
     *
     * @return the hash code value for this TelephoneNumber
     */
    @Override
    public int hashCode() {
        return this.number.hashCode();
    }

    /**
     * Returns the string representation of this TelephoneNumber.
     *
     * @return the string representation of this TelephoneNumber
     */
    @Override
    public String toString() {
        return this.number;
    }

    /**
     * Compares this TelephoneNumber with another TelephoneNumber.
     *
     * @param other the TelephoneNumber to compare to
     * @return a negative integer, zero, or a positive integer as this TelephoneNumber is less than, equal to, or greater than the specified TelephoneNumber
     */
    @Override
    public int compareTo(final TelephoneNumber arg0) {
        return number.compareTo(arg0.number);
    }
}
