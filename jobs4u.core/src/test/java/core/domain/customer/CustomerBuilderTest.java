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

class CustomerBuilderTest {

    private CustomerBuilder customerBuilder;
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

        customerBuilder = new CustomerBuilder();
    }

    @Test
    void testWithUser() {
        customerBuilder.withUser(customerUser);
        Customer customer = customerBuilder.build();

        assertEquals(customerUser, customer.user());
    }

    @Test
    void testWithCompany() {
        customerBuilder.withCompany(company);
        Customer customer = customerBuilder.build();

        assertEquals(company, customer.company());
    }

    @Test
    void testWithCustomerManager() {
        customerBuilder.withCustomerManager(customerManager);
        Customer customer = customerBuilder.build();

        assertEquals(customerManager, customer.customerManager());
    }

    @Test
    void testWithEmailAddress() {
        customerBuilder.withEmailAddress(emailAddress);
        Customer customer = customerBuilder.build();

        assertEquals(emailAddress, customer.identity());
    }

    @Test
    void testBuild() {
        customerBuilder.withUser(customerUser).withCompany(company).withCustomerManager(customerManager).withEmailAddress(emailAddress);
        Customer customer = customerBuilder.build();

        assertNotNull(customer);
    }

}