package core.domain.candidate;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

public class CandidateBuilder implements DomainFactory<Candidate> {
    private SystemUser systemUser;
    private TelephoneNumber telephoneNumber;
    private Curriculum curriculum;

    public CandidateBuilder withAll(final SystemUser systemUser, final TelephoneNumber telephoneNumber, final Curriculum curriculum) {
        Preconditions.nonNull(systemUser);
        Preconditions.nonNull(telephoneNumber);
        Preconditions.nonNull(curriculum);

        this.systemUser = systemUser;
        this.telephoneNumber = telephoneNumber;
        this.curriculum = curriculum;
        return this;
    }

    @Override
    public Candidate build() {
        return new Candidate(this.systemUser, this.telephoneNumber, this.curriculum);
    }
}
