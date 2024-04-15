package domain;

import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;

public class Jobs4uUser implements AggregateRoot<MecanographicNumber> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private MecanographicNumber mecanographicNumber;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne(optional = false)
    private SystemUser systemUser;

    /**
     * @param user
     * @param mecanographicNumber
     */
    public Jobs4uUser(final SystemUser user, final MecanographicNumber mecanographicNumber) {
        Preconditions.noneNull(mecanographicNumber, user);

        this.systemUser = user;
        this.mecanographicNumber = mecanographicNumber;
    }

    protected Jobs4uUser() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public MecanographicNumber mecanographicNumber() {
        return identity();
    }

    @Override
    public MecanographicNumber identity() {
        return this.mecanographicNumber;
    }
}
