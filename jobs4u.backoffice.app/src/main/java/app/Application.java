package app;

import eapli.framework.util.Utility;

@Utility
public class Application {
    public static final String VERSION = "v4 (Not using a IoC container)";
    public static final String COPYRIGHT = "(C) 2013-2024, ISEP's Professors of EAPLI";

    private static final AppSettings SETTINGS = new AppSettings();

    public static AppSettings settings() {
        return SETTINGS;
    }

    private Application() {
        // private visibility to ensure singleton & utility
    }
}
