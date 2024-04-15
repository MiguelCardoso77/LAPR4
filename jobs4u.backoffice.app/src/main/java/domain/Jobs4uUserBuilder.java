package domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class Jobs4uUserBuilder implements DomainFactory<Jobs4uUser> {

    private SystemUser systemUser;
    private MecanographicNumber mecanographicNumber;

    public Jobs4uUserBuilder withSystemUser(final SystemUser systemUser) {
        this.systemUser = systemUser;
        return this;
    }

    public Jobs4uUserBuilder withMecanographicNumber(final MecanographicNumber mecanographicNumber) {
        this.mecanographicNumber = mecanographicNumber;
        return this;
    }

    public Jobs4uUserBuilder withMecanographicNumber(final String mecanographicNumber) {
        this.mecanographicNumber = new MecanographicNumber(mecanographicNumber);
        return this;
    }

    @Override
    public Jobs4uUser build() {
        // since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Jobs4uUser(this.systemUser, this.mecanographicNumber);
    }
}
