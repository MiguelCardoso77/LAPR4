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
@Table(
        name = "COMPANY"
)
@Embeddable
public class Company implements AggregateRoot<CompanyNumber> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private CompanyNumber companyNumber;

    @Column(name = "COMPANY_NAME")
    private CompanyName companyName;
    /**
     * Constructs a Company object with the specified company number and name.
     *
     * * @param name          the company name to set
     * @throws NullPointerException if the company number or name is null
     */
    public Company(final CompanyName name, final CompanyNumber companyNumber) {

        this.companyName = name;
        this.companyNumber = companyNumber;
    }
    /**
     * Protected constructor for ORM usage.
     */
    protected Company(){
        // for ORM
    }
    public boolean sameAs(final Object o){
        if(this == o){
            return true;
        }

        if(!(o instanceof Company)){
            return false;
        }

        final Company that = (Company) o;

        return companyNumber.equals(that.companyNumber) && companyName.equals(that.companyName);
    }


    /**
     * Retrieves the identity of this Company.
     *
     * @return the company number
     */
    public CompanyNumber identity(){
        return null;
    }
    /**
     * Retrieves the company number.
     *
     * @return the company number
     */
    public CompanyNumber companyNumber(){
        return this.companyNumber;
    }
    /**
     * Retrieves the company name.
     *
     * @return the company name
     */
    public CompanyName companyName(){
        return this.companyName;
    }
    @Override
    public int compareTo(CompanyNumber o) {
        return companyNumber.compareTo(o);
    }

    @Override
    public String toString() {
        return "Company " +
                "companyNumber = " + companyNumber +
                ", companyName = " + companyName;
    }
}
