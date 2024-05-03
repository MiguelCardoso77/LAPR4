package core.pluginManagement.language;

import core.pluginManagement.importer.LanguageImporterPlugin;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;

public interface LanguageTypeRepository extends DomainRepository<Designation, LanguageType> {
}
