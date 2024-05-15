package core.domain.company;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents a company, including its unique number and name.
 * This class is an aggregate root and is persisted as an entity in the database.
 *
 * @Author 1220812@isep.ipp.pt
 *
 */
@Entity
@Table(name = "COMPANY")
public class Company implements AggregateRoot<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int companyNumber;

    @Column(name = "COMPANY_NAME")
    private CompanyName companyName;

    /**
     * Constructs a Company object with the specified company name and number.
     *
     * @param name          the company name to set
     * @param companyNumber the company number to set
     * @throws NullPointerException if the company name is null
     */
    public Company(final CompanyName name, final int companyNumber) {
        Preconditions.nonNull(name, "Company name cannot be null");

        this.companyName = name;
        this.companyNumber = companyNumber;
    }

    /**
     * Constructs a Company object with the specified company name.
     *
     * @param name the company name to set
     * @throws NullPointerException if the company name is null
     */
    public Company(final CompanyName name){
        Preconditions.nonNull(name, "Company name cannot be null");
        this.companyName= name;
    }

    /**
     * Protected constructor for ORM usage.
     */
    protected Company(){
        // for ORM
    }

    /**
     * Checks if this Company object is equal to another object.
     *
     * @param o the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyNumber, company.companyNumber) && Objects.equals(companyName, company.companyName);
    }

    /**
     * Generates a hash code for this Company object.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(companyNumber, companyName);
    }

    /**
     * Retrieves the company name.
     *
     * @return the company name
     */
    public CompanyName companyName(){
        return this.companyName;
    }

    /**
     * Compares this Company with another Company for equality.
     *
     * @param other the other object to compare to
     * @return true if the Companies are equal, false otherwise
     */
    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * Compares this Company with another object.
     *
     * @param other the other object to compare to
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Integer other) {
        return AggregateRoot.super.compareTo(other);
    }

    /**
     * Retrieves the identity of this Company.
     *
     * @return the company number
     */
    @Override
    public Integer identity() { return companyNumber; }

    /**
     * Returns a string representation of this Company.
     *
     * @return a string representation of this Company
     */
    @Override
    public String toString() {
        return "Company : " +
                "companyNumber = " + companyNumber +
                ", companyName = " + companyName;
    }
}