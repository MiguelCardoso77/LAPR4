package core.domain.company;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

/**
 * A factory class for creating instances of {@link Company}.
 */
public class CompanyBuilder implements DomainFactory<Company> {

    private CompanyNumber companyNumber;
    private CompanyName companyName;
    /**
     * Sets the company number for the company being built.
     *
     * @param companyNumber the company number to set
     * @return this CompanyBuilder instance
     */
    public CompanyBuilder withCompanyNumber(final CompanyNumber companyNumber){
        Preconditions.nonNull(companyNumber);
        this.companyNumber = companyNumber;
        return this;
    }
    /**
     * Sets the company name for the company being built.
     *
     * @param companyName the company name to set
     * @return this CompanyBuilder instance
     */
    public CompanyBuilder withCompanyName(final CompanyName companyName){
        Preconditions.nonNull(companyName);
        this.companyName = companyName;
        return this;
    }
    /**
     * Sets the company name for the company being built from a string.
     *
     * @param companyName the company name as a string
     * @return this CompanyBuilder instance
     */
    public CompanyBuilder withCompanyName(final String companyName){
        Preconditions.nonNull(companyName);
        this.companyName = new CompanyName(companyName);
        return this;
    }
    /**
     * Sets the company number for the company being built from a long value.
     *
     * @param companyNumber the company number as a long value
     * @return this CompanyBuilder instance
     */
    public CompanyBuilder withCompanyNumber(final long companyNumber){
        Preconditions.nonNull(companyNumber);
        this.companyNumber = new CompanyNumber(companyNumber);
        return this;
    }
    /**
     * Builds a new instance of {@link Company} using the provided company number and name.
     *
     * @return a new Company instance
     */
    @Override
    public Company build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Company(this.companyNumber, this.companyName);
    }


}
