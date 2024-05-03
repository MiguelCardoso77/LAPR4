package core.pluginManagement.language;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LanguageType implements AggregateRoot<Designation> {

    @Id
    private Designation name;

    protected LanguageType() {
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
