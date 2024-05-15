package core.pluginManagement.importer;

import core.pluginManagement.language.Language;

import java.io.IOException;
import java.io.InputStream;

public interface LanguageImporter {
    Iterable<Language> importFrom(InputStream filename) throws IOException;
}
