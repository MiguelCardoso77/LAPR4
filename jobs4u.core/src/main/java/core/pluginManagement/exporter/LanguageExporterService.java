package core.pluginManagement.exporter;

import core.pluginManagement.language.Language;
import eapli.framework.application.ApplicationService;
import eapli.framework.util.TemplateMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@ApplicationService
public class LanguageExporterService {
    private static final Logger logger = LogManager.getLogger(LanguageExporterService.class);

    /**
     * Exports dishes. This a "template method" working in conjunction with a Strategy.
     * If the {@link LanguageExporter} interface had just the export method we would be repeating the
     * logic of traversing the dish list in every implementation!
     *
     * <p>
     * Note that the exporter receives an Iterable and as such you should take attention to the
     * volume of data to export. If you need to export a large volume of data you should provide
     * some kind of cursor-based iterable and not a pure in-memory collection.
     *
     * @param dishes
     * @param filename
     * @param exporter
     * @throws IOException
     */
    @TemplateMethod
    public void export(final Iterable<Language> dishes, final String filename, final LanguageExporter exporter) throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final Language e : dishes) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(e);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting dishes", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
