package core.domain.customer;

import core.domain.jobOpening.Address;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

/**
 * Represents a company entity.
 * <p>
 * A company is characterized by its name, address, email, and telephone number.
 * This class is a value object and follows the Value Object pattern.
 * </p>
 * <p>
 * Instances of this class are immutable.
 * </p>
 * <p>
 * The class implements the {@code ValueObject} interface to signify that it is a value object.
 * It also implements the {@code Comparable} interface to allow for comparisons between instances of {@code Company} based on their telephone numbers.
 * </p>
 * <p>
 * Instances of {@code Company} can be created using the {@code valueOf} factory method.
 * </p>
 * <p>
 * This class is embeddable and can be used as part of other entities.
 * </p>
 * <p>
 * Example usage:
 * <pre>{@code
 * Company company = Company.valueOf(companyName, companyAddress, companyEmail, companyTelephoneNumber);
 * }</pre>
 * </p>
 * <p>
 * This class provides implementations for {@code equals}, {@code hashCode}, {@code toString}, and {@code compareTo} methods.
 * </p>
 * <p>
 * This class uses the {@code Preconditions} class for parameter validation in the constructor.
 * </p>
 *
 * @author Diogo Ribeiro 1220812@isep.ipp.pt
 * @see ValueObject
 * @see Comparable
 * @see Preconditions
 */
@Embeddable
public class Company implements ValueObject, Comparable<Company> {

    private static final long serialVersionUID = 1L;
    private CompanyName name;
    private Address address;
    private EmailAddress email;
    private TelephoneNumber telephoneNumber;

    /**
     * Constructs a new Company instance with the specified details.
     *
     * @param name            the name of the company
     * @param address         the address of the company
     * @param emailAddress    the email address of the company
     * @param telephoneNumber the telephone number of the company
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public Company(final CompanyName name, final Address address, final EmailAddress emailAddress, final TelephoneNumber telephoneNumber) {
        Preconditions.nonNull(name, "Company name cannot be null");
        Preconditions.nonNull(address, "Address name cannot be null");
        Preconditions.nonNull(emailAddress, "Email address name cannot be null");
        Preconditions.nonNull(telephoneNumber, "Telephone address cannot be null");

        this.name = name;
        this.address = address;
        this.email = emailAddress;
        this.telephoneNumber = telephoneNumber;
    }
    /**
     * Constructs a new Company instance with the specified details.
     *
     * @param name            the name of the company
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public Company(final String name) {
        Preconditions.nonNull(name, "Company name cannot be null");
        CompanyName companyName = new CompanyName(name);
        this.name = companyName;
    }

    protected Company(){
        // for ORM
    }
    /**
     * Returns a new Company instance with the specified details.
     *
     * @param name            the name of the company
     * @param address         the address of the company
     * @param emailAddress    the email address of the company
     * @param telephoneNumber the telephone number of the company
     * @return a new Company instance
     */
    public static Company valueOf(final CompanyName name, final Address address, final EmailAddress emailAddress, final TelephoneNumber telephoneNumber){
        return new Company(name, address, emailAddress, telephoneNumber);
    }
    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two Company objects are considered equal if they have the same name, address, email, and telephone number.
     * </p>
     *
     * @param o the object to compare with
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }

        final Company that = (Company) o;
        return this.name.equals(that.name) && this.address.equals(that.address) && this.email.equals(that.email) && this.telephoneNumber.equals(that.telephoneNumber);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return this.telephoneNumber.hashCode();
    }
    /**
     * Returns a string representation of the Company.
     *
     * @return a string representation of the Company
     */
    @Override
    public String toString() {
        return "Company: " +
                "name = " + name +
                ", address = " + address +
                ", email = " + email +
                ", telephoneNumber = " + telephoneNumber;
    }
    /**
     * Compares this Company with the specified Company for order based on their telephone numbers.
     *
     * @param arg0 the Company to be compared
     * @return a negative integer, zero, or a positive integer as this Company is less than, equal to, or greater than the specified Company
     */
    @Override
    public int compareTo(final Company arg0) {
        return this.telephoneNumber.compareTo(arg0.telephoneNumber);
    }

}
