package core.pluginManagement.importer;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

@Embeddable
public class FQClassName implements ValueObject, Comparable<FQClassName> {

    private final String fqClassName;

    protected FQClassName(final String name) {
        Preconditions.nonEmpty(name);
        // TODO check if it is a FQName

        this.fqClassName = name;
    }

    protected FQClassName() {
        // for ORM
        fqClassName = null;
    }

    public static FQClassName valueOf(final String fqClassName) {
        return new FQClassName(fqClassName);
    }

    @Override
    public String toString() {
        return fqClassName;
    }

    @Override
    public int compareTo(final FQClassName o) {
        return fqClassName.compareTo(o.fqClassName);
    }
}
