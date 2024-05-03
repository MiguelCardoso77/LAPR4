package core.pluginManagement.exporter;

import core.pluginManagement.language.Language;
import eapli.framework.util.Strategy;

import java.io.IOException;

@Strategy
public interface LanguageExporter {
    /**
     * Initiate the export process. The implementation should open the underlying resource (e.g., file) and create the
     * "document start"/"header" for the respective format.
     *
     * @param filename
     */
    void begin(String filename) throws IOException;

    /**
     * Export one single element.
     *
     * @param e
     */
    void element(Language e);

    /**
     * Indicates that a new element will be created.
     */
    void elementSeparator();

    /**
     * Indicates that there are no more elements to export. The implementation should create any "document closing"
     * element it might need and close the underlying resource.
     */
    void end();

    /**
     * Gives the exporter implementation a change to cleanup in case some exception has occurred.
     */
    void cleanup();
}
