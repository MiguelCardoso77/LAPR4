package core.domain.application;

import eapli.framework.domain.model.ValueObject;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class IdApplication implements ValueObject, Comparable<IdApplication> {

    private static final AtomicLong idApplication = new AtomicLong(0);

    private final long value;

    public IdApplication() {
        this.value = idApplication.incrementAndGet();
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdApplication that = (IdApplication) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
