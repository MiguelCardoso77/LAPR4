package core.domain.interview;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class Result implements ValueObject {

    private String result;

    public Result(String result) {
        this.result = result;
    }

    protected Result() {
        // for ORM
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Result)) {
            return false;
        }

        final Result that = (Result) o;
        return this.result.equals(that.result);
    }
    @Override
    public int hashCode() {
        return this.result.hashCode();
    }

    @Override
    public String toString() {
        return result;
    }


}
