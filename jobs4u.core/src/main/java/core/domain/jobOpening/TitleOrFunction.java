package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;

public class TitleOrFunction implements ValueObject {
    private String titleOrFunction;

    public TitleOrFunction(final String titleOrFunction) {
        if (titleOrFunction == null || titleOrFunction.isEmpty()) {
            throw new IllegalArgumentException("Title or Function should neither be null nor empty");
        }

        this.titleOrFunction = titleOrFunction;
    }

    protected TitleOrFunction() {
        // for ORM
    }

    public static TitleOrFunction valueOf(final String titleOrFunction) {
        return new TitleOrFunction(titleOrFunction);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TitleOrFunction)) {
            return false;
        }

        final TitleOrFunction that = (TitleOrFunction) o;
        return this.titleOrFunction.equals(that.titleOrFunction);
    }

    @Override
    public int hashCode() {
        return this.titleOrFunction.hashCode();
    }

    @Override
    public String toString() {
        return this.titleOrFunction;
    }
}
