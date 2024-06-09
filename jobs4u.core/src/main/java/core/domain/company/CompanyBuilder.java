package core.domain.company;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

/**
 * A factory class for creating instances of {@link Company}.
 *
 * @author Diogo Ribeiro
 */
public class CompanyBuilder implements DomainFactory<Company> {
    private int companyNumber;
    private CompanyName companyName;

    /**
     * Sets the company number for the company being built.
     *
     * @param companyNumber the company number to set
     * @return this CompanyBuilder instance
     * @throws NullPointerException if the company number is null
     */
    public CompanyBuilder withCompanyNumber(final int companyNumber){
        Preconditions.nonNull(companyNumber, "Company number cannot be null");
        this.companyNumber = companyNumber;
        return this;
    }

    /**
     * Sets the company name for the company being built.
     *
     * @param companyName the company name to set
     * @return this CompanyBuilder instance
     * @throws NullPointerException if the company name is null
     */
    public CompanyBuilder withCompanyName(final CompanyName companyName){
        Preconditions.nonNull(companyName, "Company name cannot be null");
        this.companyName = companyName;
        return this;
    }

    /**
     * Sets the company name for the company being built from a string.
     *
     * @param companyName the company name as a string
     * @return this CompanyBuilder instance
     * @throws NullPointerException if the company name is null
     */
    public CompanyBuilder withCompanyName(final String companyName){
        Preconditions.nonNull(companyName, "Company name cannot be null");
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Builds a new instance of {@link Company} using the provided company number and name.
     *
     * @return a new Company instance
     * @throws NullPointerException if the company number or name is null
     */
    @Override
    public Company build() {
        Preconditions.nonNull(companyName, "Company name cannot be null");
        Preconditions.nonNull(companyNumber, "Company number cannot be null");

        return new Company(companyName, companyNumber);
    }
}