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

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.identities.impl.UUIDGenerator;
import eapli.framework.infrastructure.authz.domain.model.Password;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.GeneralDTO;
import eapli.framework.validations.Invariants;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

/**
 * A Customer.
 * This class represents customer. It follows a DDD approach where User
 * is the root entity of the Base User Aggregate and all of its properties
 * are instances of value objects. A Customer references a System User.
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Entity
public class Customer implements AggregateRoot<TelephoneNumber> {

    @EmbeddedId
    @Column(name = "TELEPHONE_NUMBER")
    private TelephoneNumber telephoneNumber;
    @Column(name = "COMPANY")
    private Company company;


    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne
    @Column(name = "SYSTEM_USER")
    private SystemUser systemUser;

    /**
     * Constructs a new Customer instance with the specified system user, telephone number, and company.
     *
     * @param user            the system user associated with the client user
     * @param telephoneNumber the telephone number of the client user
     * @param company         the company associated with the client user
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public Customer(final SystemUser user, final TelephoneNumber telephoneNumber, final Company company) {
        if (telephoneNumber == null || user == null || company == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.telephoneNumber = telephoneNumber;
        this.company = company;
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
        GeneralDTO ret = new GeneralDTO("user");
        ret.put("telephoneNumber", this.telephoneNumber.toString());
        ret.put("company", this.company.toString());
        ret.put("systemUser", this.systemUser.toString());
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
     * Retrieves the telephone number of this customer.
     *
     * @return the telephone number
     */
    public TelephoneNumber telephoneNumber() {
        return identity();
    }

    /**
     * Retrieves the identity (telephone number) of this customer.
     *
     * @return the identity of this customer
     */
    @Override
    public TelephoneNumber identity() {
        return this.telephoneNumber;
    }
}
