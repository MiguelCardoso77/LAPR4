package core.domain.customer;

import core.domain.company.Company;
import core.domain.company.CompanyName;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;
    private SystemUser customerUser;
    private SystemUser customerManager;
    private EmailAddress emailAddress;
    private Company company;

    @BeforeEach
    void setUp() {
        customerUser = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username", "password", "firstName", "lastName", "email@example.com").build();
        customerManager = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username1", "password", "firstName", "lastName", "email1@gmail.com").build();
        company = new Company(new CompanyName("Company1"), 3);
        emailAddress = EmailAddress.valueOf("email@test.com");
        customer = new Customer(customerUser, emailAddress, company, customerManager);


    }

    @Test
    void testUser() {
        assertEquals(customerUser, customer.user());
    }

    @Test
    void testCompany() {
        assertEquals(company, customer.company());
    }

    @Test
    void testCustomerManager() {
        assertEquals(customerManager, customer.customerManager());
    }

    @Test
    void testEquals() {
        Customer sameCustomer = new Customer(customerUser, emailAddress, company, customerManager);
        assertEquals(customer, sameCustomer);
    }

    @Test
    void testSameAs() {
        Customer sameCustomer = new Customer(customerUser, emailAddress, company, customerManager);
        assertTrue(customer.sameAs(sameCustomer));
    }

    @Test
    void testIdentity() {
        assertEquals(emailAddress, customer.identity());
    }
}