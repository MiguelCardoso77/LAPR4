package core.repositories;

import core.domain.notification.Notification;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;
/**
 * Repository interface for managing {@link Notification} persistence.
 * This interface provides methods for retrieving a notification by its ID and retrieving all notifications.
 *
 * @author 1220812@isep.ipp.pt
 */
public interface NotificationRepository extends DomainRepository<Integer, Notification> {
    /**
     * Retrieves a notification by its ID.
     *
     * @param id The ID of the notification to retrieve.
     * @return An Optional containing the found notification, or an empty Optional if no notification was found.
     */
    @Override
    Optional<Notification> ofIdentity(Integer id);
    /**
     * Retrieves all notifications.
     *
     * @return An Iterable containing all notifications.
     */

    Iterable<Notification> allNotifications();
}
