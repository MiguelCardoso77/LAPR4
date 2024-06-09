package backoffice.presentation.menus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatorMenuTest {
    private OperatorMenu operatorMenu;

    @BeforeEach
    public void setUp() {
        operatorMenu = new OperatorMenu();
    }

    @Test
    public void testDoShow() {
        assertTrue(operatorMenu.doShow(), "doShow should always return true");
    }

    @Test
    public void testHeadline() {
        assertEquals("Operator Menu", operatorMenu.headline(), "Headline should be 'Operator Menu'");
    }
}