package eapli.clientusermanagement.application;

import eapli.clientusermanagement.domain.SignupRequest;
import eapli.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.clientusermanagement.repositories.SignupRequestRepository;
import eapli.persistence.PersistenceContext;
import eapli.usermanagement.domain.Jobs4URoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;
import eapli.framework.validations.Preconditions;
import org.springframework.transaction.annotation.Transactional;

/**
 * the controller for the use case "Accept or refuse signup request"
 *
 * this implementation makes use of domain events to (1) follow the rule that
 * one controller should only modify one aggregate, and (2) notify other parts
 * of the system to react accordingly. For an alternative transactional approach
 * see {@link AcceptRefuseSignupRequestControllerTxImpl}
 *
 * @author Paulo Gandra de Sousa
 */
@UseCaseController
public class AcceptRefuseSignupRequestControllerEventfullImpl
        implements AcceptRefuseSignupRequestController{

    private final SignupRequestRepository signupRequestsRepository = PersistenceContext
            .repositories().signupRequests();
    private final AuthorizationService authorizationService = AuthzRegistry.authorizationService();
    private final EventPublisher dispatcher = InProcessPubSub.publisher();

    @Override
    @SuppressWarnings("squid:S1226")
    public SignupRequest acceptSignupRequest(SignupRequest theSignupRequest) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER,
                Jobs4URoles.ADMIN);

        Preconditions.nonNull(theSignupRequest);

        theSignupRequest = markSignupRequestAsAccepted(theSignupRequest);
        return theSignupRequest;
    }

    /**
     * modify Signup Request to accepted
     *
     * @param theSignupRequest
     * @return
     * @throws ConcurrencyException
     * @throws IntegrityViolationException
     */
    @SuppressWarnings("squid:S1226")
    private SignupRequest markSignupRequestAsAccepted(SignupRequest theSignupRequest) {
        // do just what is needed in the scope of this use case
        theSignupRequest.accept();
        theSignupRequest = signupRequestsRepository.save(theSignupRequest);

        // notify interested parties (if any)
        final DomainEvent event = new SignupAcceptedEvent(theSignupRequest);
        dispatcher.publish(event);

        return theSignupRequest;
    }

    @Override
    @Transactional
    public SignupRequest refuseSignupRequest(final SignupRequest theSignupRequest) {
        authorizationService.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER,
                Jobs4URoles.ADMIN);

        Preconditions.nonNull(theSignupRequest);

        theSignupRequest.refuse();
        return signupRequestsRepository.save(theSignupRequest);
    }

    /**
     *
     * @return
     */
    @Override
    public Iterable<SignupRequest> listPendingSignupRequests() {
        return signupRequestsRepository.pendingSignupRequests();
    }
}
