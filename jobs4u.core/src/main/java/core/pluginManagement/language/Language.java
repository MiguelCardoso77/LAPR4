package core.pluginManagement.language;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.representations.RepresentationBuilder;
import eapli.framework.representations.Representationable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Language implements AggregateRoot<Designation>, Representationable {
    @XmlElement
    @JsonProperty
    @EmbeddedId
    private Designation name;

    protected Language() {
        // for ORM
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Designation identity() {
        return this.name;
    }

    @Override
    public <R> R buildRepresentation(RepresentationBuilder<R> builder) {
        return null;
    }
}
