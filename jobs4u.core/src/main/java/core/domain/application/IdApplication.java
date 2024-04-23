package core.domain.application;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class IdApplication implements ValueObject, Comparable<IdApplication> {
    private long idApplication;

    public IdApplication(final long idApplication) {
        if (idApplication <= 0) {
            throw new IllegalArgumentException("IdApplication should not be empty");
        }

        this.idApplication = idApplication + 1;
    }

    protected IdApplication() {
        // for ORM
    }

    public static IdApplication valueOf(final long idApplication) {
        return new IdApplication(idApplication);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdApplication that = (IdApplication) o;
        return idApplication == that.idApplication;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idApplication);
    }

    @Override
    public String toString() {
        return String.valueOf(idApplication);
    }

    @Override
    public int compareTo(IdApplication o) {
        return Long.compare(idApplication, o.idApplication);
    }
}
