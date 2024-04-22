package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;

public class Company implements ValueObject, Comparable<Company> {
    private int companyNumber;

    public Company(final int companyNumber) {
        if (companyNumber < 0) {
            throw new IllegalArgumentException("Company Number should be a positive number");
        }

        this.companyNumber = companyNumber;
    }

    protected Company() {
        // for ORM
    }

    public static Company valueOf(final int companyNumber) {
        return new Company(companyNumber);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }

        final Company that = (Company) o;
        return this.companyNumber == that.companyNumber;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.companyNumber);
    }

    @Override
    public String toString() {
        return Integer.toString(this.companyNumber);
    }

    @Override
    public int compareTo(final Company arg0) {
        return Integer.compare(companyNumber, arg0.companyNumber);
    }
}
