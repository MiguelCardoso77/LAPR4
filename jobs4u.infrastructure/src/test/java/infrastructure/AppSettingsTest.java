package infrastructure;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppSettingsTest {

    @Test
    void testIsMenuLayoutHorizontal() {
        AppSettings appSettings = new AppSettings();
        assertEquals(true, appSettings.isMenuLayoutHorizontal());
    }

    @Test
    void testGetPersistenceUnitName() {
        AppSettings appSettings = new AppSettings();
        assertEquals("jobs4u.database", appSettings.getPersistenceUnitName());
    }

    @Test
    void testGetRepositoryFactory() {
        AppSettings appSettings = new AppSettings();
        assertEquals("persistence.jpa.JpaRepositoryFactory", appSettings.getRepositoryFactory());
    }

    @Test
    void testGetExtendedPersistenceProperties() {
        AppSettings appSettings = new AppSettings();
        assertNotNull(appSettings.getExtendedPersistenceProperties());
    }

    @Test
    void testGetProperty() {
        AppSettings appSettings = new AppSettings();
        assertEquals("horizontal", appSettings.getProperty("ui.menu.layout"));
    }
}