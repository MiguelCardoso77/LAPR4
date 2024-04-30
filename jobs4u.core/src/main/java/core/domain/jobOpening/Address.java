package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents the address associated with a job opening.
 *
 * @author Diana Neves
 */
@Embeddable
public class Address implements ValueObject {
    private String address;

    /**
     * Constructs an Address object with the given address.
     *
     * @param address the address to be set
     * @throws IllegalArgumentException if the address is null or empty
     */
    public Address(final String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("address should neither be null nor empty");
        }

        this.address = address;
    }

    /**
     * Default constructor required by ORM frameworks.
     */
    protected Address() {
        // for ORM
    }

    /**
     * Static factory method to create an Address object.
     *
     * @param address the address to be set
     * @return Address object
     */
    public static Address valueOf(final String address) {
        return new Address(address);
    }

    /**
     * Compares this Address instance with another object for equality.
     *
     * @param o the object to be compared with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        final Address that = (Address) o;
        return this.address.equals(that.address);
    }

    /**
     * Generates a hash code value for this Address object.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return this.address.hashCode();
    }

    /**
     * Returns the string representation of this Address object.
     *
     * @return the address string
     */
    @Override
    public String toString() {
        return address;
    }
}
