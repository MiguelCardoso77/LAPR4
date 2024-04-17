package events;

import domain.Jobs4uRoles;
import domain.UserBuilderHelper;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.infrastructure.pubsub.PubSubRegistry;
import persistence.PersistenceContext;

@UseCaseController
class AddUserOnSignupAcceptedController {

    private final UserRepository userRepository = PersistenceContext.repositories().users();

    private final EventPublisher dispatcher = PubSubRegistry.publisher();

    /**
     * @param theSignupRequest
     *
     * @return
     */
    public SystemUser addUser(final SignupAcceptedEvent theSignupRequest) {

        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(theSignupRequest.username()).withPassword(theSignupRequest.password())
                .withName(theSignupRequest.name()).withEmail(theSignupRequest.email())
                .withRoles(Jobs4uRoles.JOBS4U_USER);
        final SystemUser newUser = userRepository.save(userBuilder.build());

        // notify interested parties
        final DomainEvent event = new NewUserRegisteredFromSignupEvent(theSignupRequest.mecanographicNumber(),
                newUser.username());
        dispatcher.publish(event);

        return newUser;
    }
}
