package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;

public class VacanciesNumber implements ValueObject, Comparable<VacanciesNumber> {
    private int number;

    public VacanciesNumber(final int vacanciesNumber) {
        if (vacanciesNumber < 0) {
            throw new IllegalArgumentException("Vacancies Number should be a positive number");
        }

        this.number = vacanciesNumber;
    }

    protected VacanciesNumber() {
        // for ORM
    }

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
}
