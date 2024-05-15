package core.pluginManagement.exporter;

import core.pluginManagement.language.Language;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TxtLanguageExporter implements LanguageExporter {
    private PrintWriter stream;

    @Override
    public void begin(final String filename) throws IOException {
        stream = new PrintWriter(new FileWriter(filename));
        stream.println("\"name\",\"type\",\"active\",\"salt\",\"calories\"");
    }

    @Override
    public void element(final Language e) {
        stream.printf("%s", e.identity());
    }

    @Override
    public void elementSeparator() {
        stream.println();
    }

    @Override
    public void end() {
        stream.close();
    }

    @Override
    public void cleanup() {
        if (stream != null) {
            stream.close();
        }
    }
}
