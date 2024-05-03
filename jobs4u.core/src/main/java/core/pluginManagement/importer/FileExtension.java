package core.pluginManagement.importer;

import com.fasterxml.jackson.annotation.JsonProperty;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.StringMixin;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class FileExtension implements ValueObject, Comparable<FileExtension>, StringMixin {

    @XmlAttribute
    @JsonProperty
    private final String fileExtension;

    protected FileExtension(final String name) {
        Preconditions.ensure(StringPredicates.isSingleWord(name) && name.indexOf('.') == -1);

        this.fileExtension = name;
    }

    protected FileExtension() {
        // for ORM
        fileExtension = null;
    }

    public static FileExtension valueOf(final String fileExt) {
        return new FileExtension(fileExt);
    }

    @Override
    public String toString() {
        return fileExtension;
    }

    @Override
    public int compareTo(final FileExtension o) {
        return fileExtension.compareTo(o.fileExtension);
    }
}
