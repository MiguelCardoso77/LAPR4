package core.pluginManagement.language;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Language implements AggregateRoot<Designation> {

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
}
