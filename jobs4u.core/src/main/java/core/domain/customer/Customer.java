/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package core.domain.customer;

import core.domain.company.Company;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.GeneralDTO;
import jakarta.persistence.*;

/**
 * Represents a customer entity.
 * This class serves as an aggregate root.
 */
@Entity
@Table(
        name = "CUSTOMER"
)
public class Customer implements AggregateRoot<EmailAddress> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private EmailAddress emailAddress;
    @OneToOne
    @JoinColumn(name = "COMPANY_NUMBER")
    private Company company;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_MANAGER_EMAIL")
    private SystemUser customerManager;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_EMAIL")
    private SystemUser systemUser;
    /**
     * Constructs a new Customer instance with the specified system user, company, and customer manager.
     *
     * @param user            the system user associated with the customer
     * @param company         the company associated with the customer
     * @param customerManager the customer manager associated with the customer
     */

    public Customer(final SystemUser user, final EmailAddress emailAddress, final Company company, final SystemUser customerManager) {
        if (user == null || company == null || customerManager == null || emailAddress == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.company = company;
        this.customerManager = customerManager;
        this.emailAddress = emailAddress;
    }

    /**
     * Default constructor for JPA persistence. Intended for internal use only.
     */
    protected Customer() {
        // for ORM only
    }
    /**
     * Retrieves the system user associated with this customer.
     *
     * @return the system user
     */
    public SystemUser user() {
        return this.systemUser;
    }
    /**
     * Retrieves the company associated with this customer.
     *
     * @return the company
     */
    public Company company(){
        return this.company;
    }
    /**
     * Retrieves the customer manager associated with this customer.
     *
     * @return the customer manager
     */
    public SystemUser customerManager(){
        return this.customerManager;
    }

    /**
     * Checks if this customer is equal to another object.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }
    /**
     * Computes the hash code of this customer.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }
    /**
     * Generates a DTO (Data Transfer Object) representing this customer.
     *
     * @return a DTO representing this customer
     */
    public GeneralDTO toDTO() {
        GeneralDTO ret = new GeneralDTO("customer");
        ret.put("emailAddress", this.emailAddress.toString());
        ret.put("company", this.company.toString());
        ret.put("systemUser", this.systemUser.toString());
        ret.put("customerManager", this.customerManager.toString());
        return ret;
    }
    /**
     * Compares this customer with another object for equality.
     *
     * @param other the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }
    /**
     * Retrieves the customer ID of this customer.
     *
     * @return the customer ID
     */
    public EmailAddress emailAddress() {
        return identity();
    }


    /**
     * Retrieves the identity (customer ID) of this customer.
     *
     * @return the identity of this customer
     */
    @Override
    public EmailAddress identity() {
        return this.emailAddress();
    }
}
