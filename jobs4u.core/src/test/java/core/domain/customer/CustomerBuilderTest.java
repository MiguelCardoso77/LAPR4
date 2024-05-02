package core.domain.customer;

import core.domain.company.Company;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.general.domain.model.EmailAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
/*
class CustomerBuilderTest {
    CustomerBuilder customerBuilder;
    EmailAddress emailAddress;
    SystemUser systemUser;
    Company company;
    static SystemUser customerManager;

    @BeforeEach
    void setUp() {
        customerBuilder = new CustomerBuilder();
        emailAddress = EmailAddress.valueOf("customer@example.com");
        systemUser = mock(SystemUser.class);
        company = mock(Company.class);
        customerManager = mock(SystemUser.class);
    }

    @Test
    public void ensureCustomerIsBuiltWithValidSystemUser() {
        Customer customer = customerBuilder
                .withUser(systemUser)
                .withEmailAddress(emailAddress)
                .withCompany(company)
                .withCustomerManager(customerManager)
                .build();

        assertNotNull(customer);
    }

    @Test
    public void ensureCustomerBuilderThrowsExceptionWhenCompanyIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customerBuilder
                    .withUser(systemUser)
                    .withEmailAddress(emailAddress)
                    .withCustomerManager(customerManager)
                    .build();
        });
    }

    @Test
    public void ensureCustomerBuilderThrowsExceptionWhenSystemUserIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customerBuilder
                    .withEmailAddress(emailAddress)
                    .withCompany(company)
                    .withCustomerManager(customerManager)
                    .build();
        });
    }

    @Test
    public void ensureCustomerBuilderThrowsExceptionWhenEmailAddressIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            customerBuilder
                    .withUser(systemUser)
                    .withCompany(company)
                    .withCustomerManager(customerManager)
                    .build();
        });
    }
}

 */
