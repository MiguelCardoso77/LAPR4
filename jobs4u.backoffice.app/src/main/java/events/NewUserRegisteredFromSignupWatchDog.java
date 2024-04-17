package events;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.pubsub.EventHandler;
import eapli.framework.validations.Preconditions;

public class NewUserRegisteredFromSignupWatchDog implements EventHandler {

    /*
     * (non-Javadoc)
     *
     * @see eapli.framework.domain.events.EventHandler#onEvent(eapli.framework.
     * domain. events.DomainEvent)
     */
    @Override
    public void onEvent(final DomainEvent domainevent) {
        Preconditions.ensure(domainevent instanceof NewUserRegisteredFromSignupEvent);

        final NewUserRegisteredFromSignupEvent event = (NewUserRegisteredFromSignupEvent) domainevent;

        final var controller = new AddJobs4uUserOnSignupAcceptedController();
        controller.addJobs4uUser(event);
    }
}
