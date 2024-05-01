package infrastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationTest {

    @Test
    void testVersion() {
        assertEquals("2.0.0", Application.VERSION);
    }

    @Test
    void testCopyright() {
        assertEquals("(C) 2024, Group 2DD5", Application.COPYRIGHT);
    }

    @Test
    void testSettingsNotNull() {
        assertNotNull(Application.settings());
    }
}