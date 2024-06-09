package core.domain.notification;

import core.domain.application.Application;
import core.domain.candidate.Candidate;
import eapli.framework.domain.model.DomainFactory;

/**
 * A builder class for creating instances of {@link Notification}.
 * This builder provides methods for setting various attributes of a notification
 * and constructs the {@code Notification} object when all mandatory information is provided.
 *
 * @author Diogo Ribeiro
 */
public class NotificationBuilder implements DomainFactory<Notification> {
    private Message message;
    private Application application;
    private Candidate candidate;

    /**
     * Sets all attributes of the notification.
     *
     * @param message     The message of the notification.
     * @param application The application associated with the notification.
     * @param candidate   The candidate associated with the notification.
     * @return This builder instance.
     */
    public NotificationBuilder withAll(Message message, Application application, Candidate candidate) {
        this.message = message;
        this.application = application;
        this.candidate = candidate;
        return this;
    }

    /**
     * Constructs a {@code Notification} object with the provided attributes.
     *
     * @return The constructed {@code Notification} object.
     */
    @Override
    public Notification build() {
        Notification notification;
        notification = new Notification(application, message, candidate);
        return notification;
    }
}
