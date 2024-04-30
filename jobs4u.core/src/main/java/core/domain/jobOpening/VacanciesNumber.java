package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents the number of vacancies in a job opening.
 *
 * @author Diana Neves
 */
@Embeddable
public class VacanciesNumber implements ValueObject, Comparable<VacanciesNumber> {
    private int number;

    /**
     * Constructs a `VacanciesNumber` instance with the specified number of vacancies.
     *
     * @param vacanciesNumber The number of vacancies (should be a positive number).
     * @throws IllegalArgumentException If the provided number is negative.
     */
    public VacanciesNumber(final int vacanciesNumber) {
        if (vacanciesNumber < 0) {
            throw new IllegalArgumentException("Vacancies Number should be a positive number");
        }

        this.number = vacanciesNumber;
    }

    /**
     * Default constructor for ORM purposes.
     */
    protected VacanciesNumber() {
        // for ORM
    }

    /**
     * Creates a `VacanciesNumber` instance from the given integer value.
     *
     * @param vacanciesNumber The number of vacancies.
     * @return A `VacanciesNumber` instance.
     */
    public static VacanciesNumber valueOf(final int vacanciesNumber) {
        return new VacanciesNumber(vacanciesNumber);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VacanciesNumber)) {
            return false;
        }

        final VacanciesNumber that = (VacanciesNumber) o;
        return this.number == that.number;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.number);
    }

    @Override
    public String toString() {
        return Integer.toString(this.number);
    }

    @Override
    public int compareTo(final VacanciesNumber arg0) {
        return Integer.compare(number, arg0.number);
    }

    public int number() {
        return number;
    }
}
