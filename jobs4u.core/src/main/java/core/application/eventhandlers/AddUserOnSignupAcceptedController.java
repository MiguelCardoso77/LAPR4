package core.application.eventhandlers;

import core.domain.events.NewUserRegisteredFromSignupEvent;
import core.domain.events.SignupAcceptedEvent;
import core.domain.user.Jobs4URoles;
import core.domain.user.UserBuilderHelper;
import core.persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.pubsub.EventPublisher;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;

@UseCaseController
class AddUserOnSignupAcceptedController {
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final EventPublisher dispatcher = InProcessPubSub.publisher();

    public SystemUser addUser(final SignupAcceptedEvent theSignupRequest) {

        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(theSignupRequest.username())
                .withPassword(theSignupRequest.password()).withName(theSignupRequest.name())
                .withEmail(theSignupRequest.email()).withRoles(Jobs4URoles.CLIENT_USER);
        final SystemUser newUser = userRepository.save(userBuilder.build());

        // notify interested parties
        final DomainEvent event = new NewUserRegisteredFromSignupEvent(
                theSignupRequest.email(), theSignupRequest.username());
        dispatcher.publish(event);

        return newUser;
    }
}
