package backoffice.presentation.menus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageEngineerMenuTest {
    private LanguageEngineerMenu languageEngineerMenu;

    @BeforeEach
    public void setUp() {
        languageEngineerMenu = new LanguageEngineerMenu();
    }

    @Test
    public void testDoShow() {
        assertTrue(languageEngineerMenu.doShow(), "doShow should always return true");
    }

    @Test
    public void testHeadline() {
        assertEquals("Language Engineer Menu", languageEngineerMenu.headline(), "Headline should be 'Language Enginee'");
    }

}