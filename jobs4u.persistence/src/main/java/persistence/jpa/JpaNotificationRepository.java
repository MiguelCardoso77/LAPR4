package persistence.jpa;

import core.domain.notification.Notification;
import core.repositories.NotificationRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaNotificationRepository extends JpaAutoTxRepository<Notification, Integer, Integer> implements NotificationRepository{
    public JpaNotificationRepository(TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaNotificationRepository(String puname) {
        super(puname, "idNotification");
    }

    @Override
    public Iterable<Notification> allNotifications() {
        return findAll();
    }
}
