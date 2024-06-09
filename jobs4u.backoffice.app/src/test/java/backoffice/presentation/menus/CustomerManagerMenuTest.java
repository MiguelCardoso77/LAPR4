package backoffice.presentation.menus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerManagerMenuTest {

    private CustomerManagerMenu customerManagerMenu;

    @BeforeEach
    public void setUp() {
        customerManagerMenu = new CustomerManagerMenu();
    }

    @Test
    public void testDoShow() {
        assertTrue(customerManagerMenu.doShow(), "doShow should always return true");
    }

    @Test
    public void testHeadline() {
        assertEquals("Customer Manager Menu", customerManagerMenu.headline(), "Headline should be 'Customer Manager Menu'");
    }

}