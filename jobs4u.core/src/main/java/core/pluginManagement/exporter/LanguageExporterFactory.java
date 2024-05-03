package core.pluginManagement.exporter;

public final class LanguageExporterFactory {
    public LanguageExporter build() {
        return new TxtLanguageExporter();
    }
}
