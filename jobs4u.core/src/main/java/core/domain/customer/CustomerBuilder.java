/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package core.domain.customer;

import core.domain.company.Company;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
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
    public CustomerBuilder withUser(final SystemUser systemUser){
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
    public CustomerBuilder withCompany(final Company company){
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
    public CustomerBuilder withCustomerManager(final SystemUser customerManager){
        this.customerManager = customerManager;
        return this;
    }
    /**
     * Sets the created user.
     *
     * @param emailAddress the associated emailAddress
     * @return this CustomerBuilder instance for method chaining
     * @throws IllegalArgumentException if the emailAddress is null
     */
    public CustomerBuilder withEmailAddress(final EmailAddress emailAddress){
        this.emailAddress = emailAddress;
        return this;
    }
    /**
     * Sets the created user.
     *
     * @param emailAddress the associated emailAddress
     * @return this CustomerBuilder instance for method chaining
     * @throws IllegalArgumentException if the emailAddress is null
     */
    public CustomerBuilder withEmailAddress(final String emailAddress){
        this.emailAddress = EmailAddress.valueOf(emailAddress);
        return this;
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
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Customer(this.systemUser, this.emailAddress, this.company, this.customerManager);
    }

}
