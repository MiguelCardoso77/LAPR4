package persistence.inMemory;

import core.domain.notification.Notification;
import core.repositories.NotificationRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryNotificationRepository extends InMemoryDomainRepository<Notification, Integer> implements NotificationRepository{
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Notification> allNotifications() {
        return match(e -> true);
    }
}
