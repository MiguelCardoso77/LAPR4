package core.pluginManagement.exporter;

import core.persistence.PersistenceContext;
import core.pluginManagement.language.LanguageRepository;
import eapli.framework.application.UseCaseController;

import java.io.IOException;

@UseCaseController
public class ExportLanguagesController {
    private final LanguageRepository languageRepository = PersistenceContext.repositories().languages();
    private final LanguageExporterFactory factory = new LanguageExporterFactory();
    private final LanguageExporterService exportSvc = new LanguageExporterService();

    public void export(final String filename) throws IOException {
        final LanguageExporter exporter = factory.build();

        exportSvc.export(languageRepository.findAll(), filename, exporter);
    }
}
