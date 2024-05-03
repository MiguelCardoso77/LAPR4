package core.pluginManagement.importer;

import core.persistence.PersistenceContext;
import core.pluginManagement.language.Language;
import core.pluginManagement.language.LanguageRepository;
import core.pluginManagement.language.LanguageTypeRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class ImportLanguageController {
    private static final Logger LOGGER = LogManager.getLogger(ImportLanguageController.class);
    private final LanguageImporterPluginRepository pluginRepo = PersistenceContext.repositories().languageImporterPlugins();
    private final LanguageRepository langRepository = PersistenceContext.repositories().languages();
    private final LanguageTypeRepository langTypeRepository = PersistenceContext.repositories().languageTypes();

    /**
     * Import dishes from a file. It uses the file extension to determine which
     * import plugin to activate.
     * <p>
     * If there is an error parsing the file no dish will be imported.
     *
     * @param filename
     * @return the list of imported dishes
     * @throws IOException
     */
    public List<Language> importDishes(final String filename) throws IOException {

        // TODO refactor this method to move logic from the controller into a service
        // class

        // prepare the result variable
        List<Language> dishes = new ArrayList<>();

        // get the content of the file to import
        InputStream content = null;
        try {
            // get the content of the file to import
            content = inputStreamFromResourceOrFile(filename);

            // get the right plugin for the file
            final var fileExt = FilenameUtils.getExtension(filename);
            final var plugin = pluginRepo.findByFileExtension(FileExtension.valueOf(fileExt)).orElseThrow(
                    () -> new IllegalStateException("There is no plugin associated with that file extension"));

            // load the plugin
            final var importer = plugin.buildImporter();

            // parse the content
            final var dishesToRegister = importer.importFrom(content);

            // do the import
            dishes = doTheImport(dishesToRegister);
        } finally {
            if (content != null) {
                try {
                    content.close();
                } catch (final IOException e) {
                    LOGGER.error("Error closing the file {}", filename);
                }
            }
        }

        return dishes;
    }

    private List<Language> doTheImport(final Iterable<Language> languagesToRegister) {
        final List<Language> dishes = new ArrayList<>();

        for (final var lang : languagesToRegister) {
            final var savedDish = langRepository.save(lang);
            dishes.add(savedDish);
        }

        return dishes;
    }

    private InputStream inputStreamFromResourceOrFile(final String filename) throws FileNotFoundException {
        InputStream content;
        final var classLoader = this.getClass().getClassLoader();
        final var resource = classLoader.getResource(filename);
        if (resource != null) {
            final var file = new File(resource.getFile());
            content = new FileInputStream(file);
        } else {
            content = new FileInputStream(filename);
        }
        return content;
    }
}
