package infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Application settings provider.
 * <p>
 * Loads settings from the "application.properties" file on the classpath.
 * If the file is not found, default settings are used.
 * </p>
 *
 * @author Miguel Cardoso
 */
public class AppSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettings.class);
    private static final String PROPERTIES_RESOURCE = "application.properties";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String SCHEMA_GENERATION_KEY = "jakarta.persistence.schema-generation.database.action";
    private final Properties applicationProperties = new Properties();

    /**
     * Constructs an instance of {@code AppSettings} and loads properties.
     */
    public AppSettings() {
        loadProperties();
    }

    /**
     * Loads properties from the "application.properties" file.
     * If the file is not found, default properties are set.
     */
    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException("Property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();
            LOGGER.warn("Loading default properties", exio);
        }
    }

    /**
     * Sets default properties.
     */
    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY, "persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "jobs4u" + ".database");
    }

    /**
     * Checks if the menu layout is horizontal.
     *
     * @return true if the menu layout is horizontal, false otherwise
     */
    public Boolean isMenuLayoutHorizontal() {
        return "horizontal".equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    /**
     * Retrieves the persistence unit name.
     *
     * @return the persistence unit name
     */
    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    /**
     * Retrieves the repository factory.
     *
     * @return the repository factory
     */
    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    /**
     * Retrieves the high calories dish limit.
     *
     * @return the high calories dish limit
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Map getExtendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY, this.applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }

    /**
     * Retrieves a specific property.
     *
     * @param prop the property key
     * @return the value of the property
     */
    public String getProperty(final String prop) {
        return this.applicationProperties.getProperty(prop);
    }
}
