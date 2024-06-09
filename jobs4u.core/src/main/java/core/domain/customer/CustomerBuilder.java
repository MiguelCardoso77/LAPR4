package core.domain.customer;

import core.domain.company.Company;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * A factory for Customer entities.
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. It acts as a Builder (GoF).
 *
 * @author Diogo Ribeiro
 */
public class CustomerBuilder implements DomainFactory<Customer> {
    private EmailAddress emailAddress;
    private SystemUser systemUser;
    private SystemUser customerManager;
    private Company company;

    /**
     * Sets the created user.
     *
     * @param systemUser the created system user
     * @return this CustomerBuilder instance for method chaining
     * @throws IllegalArgumentException if the systemUser is null
     */
    public CustomerBuilder withUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    /**
     * Sets the created user.
     *
     * @param company the associated company
     * @return this CustomerBuilder instance for method chaining
     * @throws IllegalArgumentException if the company is null
     */
    public CustomerBuilder withCompany(final Company company) {
        this.company = company;
        return this;
    }

    /**
     * Sets the created user.
     *
     * @param customerManager the associated Customer Manager
     * @return this CustomerBuilder instance for method chaining
     * @throws IllegalArgumentException if the customerManager is null
     */
    public CustomerBuilder withCustomerManager(final SystemUser customerManager) {
        this.customerManager = customerManager;
        return this;
    }

    /**
     * Sets the created user.
     *
     * @param emailAddress the associated emailAddress
     * @throws IllegalArgumentException if the emailAddress is null
     */
    public void withEmailAddress(final EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Builds and returns a new instance of Customer with the configured properties.
     * <p>
     * This method constructs a Customer instance using the configured system user,
     * customer manager, company, and customer ID.
     * </p>
     * <p>
     * If any of the required fields are missing, it will throw an IllegalArgumentException.
     * </p>
     *
     * @return a new instance of Customer
     * @throws IllegalArgumentException if any of the required fields are missing
     */
    @Override
    public Customer build() {
        return new Customer(this.systemUser, this.emailAddress, this.company, this.customerManager);
    }

}