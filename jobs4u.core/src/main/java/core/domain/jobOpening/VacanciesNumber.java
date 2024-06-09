package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

/**
 * Represents the number of vacancies in a job opening.
 * This class provides methods for getting the number of vacancies, checking equality, generating hashcode, and getting a string representation of the number of vacancies.
 *
 * @author Diana Neves
 */
@Embeddable
@Table(name = "VACANCIES_NUMBER")
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

    /**
     * Checks if this number of vacancies is equal to another object.
     *
     * @param o The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
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

    /**
     * Computes the hash code for this number of vacancies.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.number);
    }

    /**
     * Returns the string representation of this number of vacancies.
     *
     * @return The number of vacancies as a string.
     */
    @Override
    public String toString() {
        return Integer.toString(this.number);
    }

    /**
     * Compares this number of vacancies to another number of vacancies.
     *
     * @param arg0 The other number of vacancies to compare to.
     * @return A negative integer, zero, or a positive integer if this number of vacancies is less than,
     * equal to, or greater than the specified number of vacancies, respectively.
     */
    @Override
    public int compareTo(final VacanciesNumber arg0) {
        return Integer.compare(number, arg0.number);
    }

    /**
     * Retrieves the number of vacancies.
     *
     * @return The number of vacancies.
     */
    public int number() {
        return number;
    }
}