package core.domain.candidate;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

/**
 * Builder class for creating instances of {@link Candidate}.
 *
 * @author Miguel Cardoso
 */
public class CandidateBuilder implements DomainFactory<Candidate> {
    private SystemUser systemUser;
    private TelephoneNumber telephoneNumber;
    private Curriculum curriculum;

    /**
     * Sets all attributes of the candidate being built.
     *
     * @param systemUser      the system user associated with the candidate
     * @param telephoneNumber the telephone number of the candidate
     * @param curriculum      the curriculum of the candidate
     * @return this CandidateBuilder instance for method chaining
     * @throws NullPointerException if any of the parameters is null
     */
    public CandidateBuilder withAll(final SystemUser systemUser, final TelephoneNumber telephoneNumber, final Curriculum curriculum) {
        Preconditions.nonNull(systemUser);
        Preconditions.nonNull(telephoneNumber);
        Preconditions.nonNull(curriculum);

        this.systemUser = systemUser;
        this.telephoneNumber = telephoneNumber;
        this.curriculum = curriculum;

        return this;
    }

    /**
     * Builds a new instance of {@link Candidate} based on the attributes set in this builder.
     *
     * @return the newly created candidate
     */
    @Override
    public Candidate build() {
        return new Candidate(this.systemUser, this.telephoneNumber, this.curriculum);
    }
}
