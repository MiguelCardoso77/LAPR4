package core.jobOpening.domain;

import eapli.framework.domain.model.ValueObject;

public class Adress implements ValueObject {
    private String adress;

    public Adress(final String adress) {
        if (adress == null || adress.isEmpty()) {
            throw new IllegalArgumentException("Adress should neither be null nor empty");
        }

        this.adress = adress;
    }

    protected Adress() {
        // for ORM
    }

    public static Adress valueOf(final String adress) {
        return new Adress(adress);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Adress)) {
            return false;
        }

        final Adress that = (Adress) o;
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
