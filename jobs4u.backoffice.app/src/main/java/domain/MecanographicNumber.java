package domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class MecanographicNumber implements ValueObject, Comparable<MecanographicNumber> {

    private static final long serialVersionUID = 1L;

    private String number;

    protected MecanographicNumber(final String mecanographicNumber) {
        if (StringPredicates.isNullOrEmpty(mecanographicNumber)) {
            throw new IllegalArgumentException(
                    "Mecanographic Number should neither be null nor empty");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.number = mecanographicNumber;
    }

    protected MecanographicNumber() {
        // for ORM
    }

    public static MecanographicNumber valueOf(final String mecanographicNumber) {
        return new MecanographicNumber(mecanographicNumber);
    }

    @Override
    public String toString() {
        return this.number;
    }

    @Override
    public int compareTo(final MecanographicNumber arg0) {
        return number.compareTo(arg0.number);
    }
}
