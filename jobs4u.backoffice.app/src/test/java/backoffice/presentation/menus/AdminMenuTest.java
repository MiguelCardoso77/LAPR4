package backoffice.presentation.menus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminMenuTest {
    private AdminMenu adminMenu;

    @BeforeEach
    public void setUp() {
        adminMenu = new AdminMenu();
    }

    @Test
    public void testDoShow() {
        assertTrue(adminMenu.doShow(), "doShow should always return true");
    }

    @Test
    public void testHeadline() {
        assertEquals("Admin Menu", adminMenu.headline(), "Headline should be 'Admin Menu'");
    }

}