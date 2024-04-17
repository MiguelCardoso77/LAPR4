package eapli.clientusermanagement.application.eventhandlers;

import eapli.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.infrastructure.pubsub.EventHandler;

/**
 * @author Paulo Gandra de Sousa
 *
 */
public class NewUserRegisteredFromSignupWatchDog implements EventHandler {

    /*
     * (non-Javadoc)
     *
     * @see eapli.framework.domain.events.EventHandler#onEvent(eapli.framework.
     * domain. events.DomainEvent)
     */
    @Override
    public void onEvent(final DomainEvent domainevent) {
        assert domainevent instanceof NewUserRegisteredFromSignupEvent;

        final NewUserRegisteredFromSignupEvent event = (NewUserRegisteredFromSignupEvent) domainevent;

        final AddClientUserOnSignupAcceptedController
                controller = new AddClientUserOnSignupAcceptedController();
        controller.addClientUser(event);
    }
}
