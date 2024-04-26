package core.domain.candidate;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import jakarta.persistence.Embeddable;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Embeddable
public class TelephoneNumber implements ValueObject, Comparable<TelephoneNumber> {

    private static final long serialVersionUID = 1L;

    private String number;

    public TelephoneNumber(final String telephoneNumber) {
        if (StringPredicates.isNullOrEmpty(telephoneNumber)) {
            throw new IllegalArgumentException("Telephone Number should neither be null nor empty");
        }

        this.number = telephoneNumber;
    }

    protected TelephoneNumber() {
        // for ORM
    }

    public static TelephoneNumber valueOf(final String telephoneNumber) {
        return new TelephoneNumber(telephoneNumber);
    }

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

    @Override
    public int hashCode() {
        return this.number.hashCode();
    }

    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int compareTo(final TelephoneNumber arg0) {
        return number.compareTo(arg0.number);
    }
}
