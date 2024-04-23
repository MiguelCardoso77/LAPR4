package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address implements ValueObject {
    private String adress;

    public Address(final String adress) {
        if (adress == null || adress.isEmpty()) {
            throw new IllegalArgumentException("Adress should neither be null nor empty");
        }

        this.adress = adress;
    }

    protected Address() {
        // for ORM
    }

    public static Address valueOf(final String adress) {
        return new Address(adress);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }

        final Address that = (Address) o;
        return this.adress.equals(that.adress);
    }

    @Override
    public int hashCode() {
        return this.adress.hashCode();
    }

    @Override
    public String toString() {
        return adress;
    }
}
