package core.pluginManagement.importer;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;

import java.util.Optional;

public interface LanguageImporterPluginRepository extends DomainRepository<Designation, LanguageImporterPlugin> {
    Optional<LanguageImporterPlugin> findByFileExtension(FileExtension fileExt);
}
