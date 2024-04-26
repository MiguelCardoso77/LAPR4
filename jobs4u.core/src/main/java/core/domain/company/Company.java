package core.domain.company;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents a company, including its unique number and name.
 * This class is a value object and is embeddable.
 */

@Entity
@Table(name = "COMPANY")
@Embeddable
public class Company implements AggregateRoot<CompanyName> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int companyNumber;

    @Column(name = "COMPANY_NAME")
    private CompanyName companyName;
    /**
     * Constructs a Company object with the specified company number and name.
     *
     * * @param name          the company name to set
     * @throws NullPointerException if the company number or name is null
     */
    public Company(final CompanyName name, final int companyNumber) {
        Preconditions.nonNull(companyNumber, "Company number cannot be null");
        Preconditions.nonNull(name, "Company name cannot be null");

        this.companyName = name;
        this.companyNumber = companyNumber;
    }

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
    }/**
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
    public int companyNumber(){
        return this.companyNumber;
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

    @Override
    public int compareTo(CompanyName o) {
        return companyName.compareTo(o);
    }

    @Override
    public CompanyName identity() { return companyName; }
}
