package user.console.presentation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrontMenuTest {

    @Test
    void testHeadline() {
        FrontMenu frontMenu = new FrontMenu();
        assertEquals("Jobs4U Customer Menu", frontMenu.headline());
    }
}