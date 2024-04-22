package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;

public class Description implements ValueObject {
    private String description;

    public Description(final String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description should neither be null nor empty");
        }

        this.description = description;
    }

    protected Description() {
        // for ORM
    }

    public static Description valueOf(final String description) {
        return new Description(description);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Description)) {
            return false;
        }

        final Description that = (Description) o;
        return this.description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return this.description.hashCode();
    }

    @Override
    public String toString() {
        return description;
    }
}
