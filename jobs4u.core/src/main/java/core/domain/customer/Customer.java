package core.domain.customer;

import core.domain.company.Company;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.persistence.*;

/**
 * Represents a customer entity.
 *
 * @Author 1220812@isep.ipp.pt
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer implements AggregateRoot<EmailAddress> {

    @EmbeddedId
    @Column(name = "EMAIL_ADDRESS")
    private EmailAddress emailAddress;

    @ManyToOne
    @JoinColumn(name = "COMPANY_NUMBER")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_MANAGER_EMAIL")
    private SystemUser customerManager;

    @OneToOne
    @JoinColumn(name = "CUSTOMER_EMAIL")
    private SystemUser systemUser;

    /**
     * Constructs a new Customer instance.
     *
     * @param user            The system user associated with the customer.
     * @param emailAddress    The email address of the customer.
     * @param company         The company associated with the customer.
     * @param customerManager The customer manager associated with the customer.
     * @throws IllegalArgumentException if any of the parameters are null.
     */
    public Customer(final SystemUser user, final EmailAddress emailAddress, final Company company, final SystemUser customerManager) {
        this.systemUser = user;
        this.company = company;
        this.customerManager = customerManager;
        this.emailAddress = emailAddress;
    }

    /**
     * Protected constructor for ORM usage only.
     */
    protected Customer() {
        // for ORM only
    }

    /**
     * Retrieves the system user associated with this customer.
     *
     * @return The system user associated with this customer.
     */
    public SystemUser user() {
        return this.systemUser;
    }

    /**
     * Retrieves the company associated with this customer.
     *
     * @return The company associated with this customer.
     */
    public Company company() {
        return this.company;
    }

    /**
     * Retrieves the customer manager associated with this customer.
     *
     * @return The customer manager associated with this customer.
     */
    public SystemUser customerManager() {
        return this.customerManager;
    }

    /**
     * Checks if this customer is equal to another object.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    /**
     * Computes the hash code of this customer.
     *
     * @return The hash code of this customer.
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * Compares this customer with another object for equality.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * Retrieves the identity (email address) of this customer.
     *
     * @return The email address of this customer.
     */
    @Override
    public EmailAddress identity() {
        return emailAddress;
    }

    /**
     * Returns a string representation of this customer.
     *
     * @return A string representation of this customer.
     */
    @Override
    public String toString() {
        return systemUser.name().toString();
    }
}