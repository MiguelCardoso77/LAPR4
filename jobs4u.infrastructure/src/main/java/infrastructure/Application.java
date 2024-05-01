package infrastructure;

/**
 * A "global" static class with the application registry of well-known objects.
 * Provides access to application settings and version information.
 * <p>
 * This class follows the Singleton pattern and provides static access to
 * application settings.
 * </p>
 *
 * @author Miguel Cardoso
 */
public class Application {
    /**
     * The version of the application.
     */
    public static final String VERSION = "2.0.0";

    /**
     * The copyright information for the application.
     */
    public static final String COPYRIGHT = "(C) 2024, Group 2DD5";

    /**
     * The application settings instance.
     */
    private static final AppSettings SETTINGS = new AppSettings();

    /**
     * Get the application settings instance.
     *
     * @return the application settings instance
     */
    public static AppSettings settings() {
        return SETTINGS;
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private Application() {
        // private visibility to ensure singleton & utility
    }
}
