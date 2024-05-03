package core.pluginManagement.importer;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

@Entity
public class LanguageImporterPlugin implements AggregateRoot<Designation> {
    private static final Logger LOGGER = LogManager.getLogger(LanguageImporterPlugin.class);

    @Id
    @GeneratedValue
    private Long pk;

    @Column(name = "DESIGNATION")
    private final Designation name;

    private final FileExtension fileExtension;

    private final FQClassName className;

    protected LanguageImporterPlugin() {
        // for ORM
        name = null;
        fileExtension = null;
        className = null;
    }

    public LanguageImporterPlugin(final String name2, final String fileExtension2, final String fqClassName) {
        Preconditions.noneNull(name2, fileExtension2, fqClassName);

        name = Designation.valueOf(name2);
        fileExtension = FileExtension.valueOf(fileExtension2);
        className = FQClassName.valueOf(fqClassName);
    }

    @Override
    public boolean sameAs(final Object other) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Designation identity() {
        return name;
    }

    /**
     * Dynamically loads and builds the plugin importer.
     *
     * @return
     */
    public LanguageImporter buildImporter() {
        try {
            return (LanguageImporter) Class.forName(className.toString()).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | IllegalArgumentException
                 | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            LOGGER.error("Unable to dynamically load the Plugin", ex);
            throw new IllegalStateException("Unable to dynamically load the Plugin: " + className.toString(), ex);
        }
    }
}
