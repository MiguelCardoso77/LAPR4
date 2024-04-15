package events;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.pubsub.EventHandler;
import eapli.framework.validations.Preconditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SignupAcceptedWatchDog implements EventHandler {
    private static final Logger LOGGER = LogManager.getLogger(SignupAcceptedWatchDog.class);

    @Override
    public void onEvent(final DomainEvent domainevent) {
        Preconditions.ensure(domainevent instanceof SignupAcceptedEvent);

        final SignupAcceptedEvent event = (SignupAcceptedEvent) domainevent;

        final AddUserOnSignupAcceptedController controller = new AddUserOnSignupAcceptedController();
        try {
            controller.addUser(event);
        } catch (final IntegrityViolationException e) {
            // TODO provably should send some warning email...
            LOGGER.error("Unable to register new user on signup event", e);
        }
    }
}

