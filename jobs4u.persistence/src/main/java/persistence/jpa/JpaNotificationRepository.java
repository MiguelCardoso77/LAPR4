package persistence.jpa;

import core.domain.notification.Notification;
import core.repositories.NotificationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * JPA implementation of the NotificationRepository interface.
 * This repository provides access to Notification entities using JPA for persistence.
 *
 * @author Miguel Cardoso
 */
public class JpaNotificationRepository extends JpaAutoTxRepository<Notification, Integer, Integer> implements NotificationRepository {

    /**
     * Constructs a new JpaNotificationRepository with the given transactional context.
     *
     * @param autoTx the transactional context.
     */
    public JpaNotificationRepository(TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    /**
     * Constructs a new JpaNotificationRepository with the given persistence unit name.
     *
     * @param puname the name of the persistence unit.
     */
    public JpaNotificationRepository(String puname) {
        super(puname, "idNotification");
    }

    /**
     * Retrieves all Notification entities.
     *
     * @return an iterable collection of all Notification entities.
     */
    @Override
    public Iterable<Notification> allNotifications() {
        return findAll();
    }
}
