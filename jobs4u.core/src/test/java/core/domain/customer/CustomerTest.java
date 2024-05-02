package core.domain.customer;

import core.domain.company.Company;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
/*
class CustomerTest {
    private Customer customer;
    private final SystemUser systemUser = mock(SystemUser.class);
    private final EmailAddress emailAddress = EmailAddress.valueOf("test@example.com");
    private final Company company = mock(Company.class);
    private final SystemUser customerManager = mock(SystemUser.class);

    @BeforeEach
    void setUp() {
        customer = new Customer(systemUser, emailAddress, company, customerManager);
    }

    // Testing the raise of exceptions when a Null parameter is passed to the constructor
    @Test
    void constructor_NullUser_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(null, emailAddress, company, customerManager));
    }

    @Test
    void constructor_NullEmailAddress_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(systemUser, null, company, customerManager));
    }

    @Test
    void constructor_NullCompany_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(systemUser, emailAddress, null, customerManager));
    }

    @Test
    void constructor_NullCustomerManager_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Customer(systemUser, emailAddress, company, null));
    }

    // Testing the returns of the get() methods
    @Test
    void user_ReturnsCorrectSystemUser() {
        assertEquals(systemUser, customer.user());
    }

    @Test
    void company_ReturnsCorrectCompany() {
        assertEquals(company, customer.company());
    }

    @Test
    void customerManager_ReturnsCorrectCustomerManager() {
        assertEquals(customerManager, customer.customerManager());
    }

    @Test
    void emailAddress_ReturnsCorrectEmailAddress() {
        assertEquals(emailAddress, customer.emailAddress());
    }

    // Testing the equals

    @Test
    void equals_IdenticalCustomers_ReturnsTrue() {
        Customer sameCustomer = new Customer(systemUser, emailAddress, company, customerManager);
        assertEquals(customer, sameCustomer);
    }

    @Test
    void equals_DifferentCustomers_ReturnsFalse() {
        Customer differentCustomer = new Customer(systemUser, EmailAddress.valueOf("diferentexample@example.com"), company, customerManager);
        assertNotEquals(customer, differentCustomer);
    }

    // Test for hashCode method
    @Test
    void hashCode_IdenticalCustomers_HashCodesAreEqual() {
        Customer sameCustomer = new Customer(systemUser, emailAddress, company, customerManager);
        assertEquals(customer.hashCode(), sameCustomer.hashCode());
    }

    // Test for sameAs method
    @Test
    void sameAs_IdenticalCustomers_ReturnsTrue() {
        Customer sameCustomer = new Customer(systemUser, emailAddress, company, customerManager);
        assertEquals(customer.sameAs(sameCustomer), customer.equals(sameCustomer));
    }
}

 */
