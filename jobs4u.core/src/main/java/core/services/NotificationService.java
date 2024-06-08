package core.services;

import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.domain.notification.Message;
import core.domain.notification.Notification;
import core.domain.notification.NotificationBuilder;
import core.persistence.PersistenceContext;
import core.repositories.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Service class for managing notifications.
 * This class provides methods for creating notifications, finding notifications by candidate or application, and finding a notification by its ID.
 * It also provides a method to retrieve all notifications.
 *
 * @author 1220812@isep.ipp.pt
 */
@Service
public class NotificationService {
    /**
     * The repository for accessing notifications.
     */
    private final NotificationRepository notificationRepository = PersistenceContext.repositories().notifications();

    /**
     * The service for accessing applications.
     */
    private final ApplicationService applicationService = new ApplicationService();

    /**
     * Creates a new notification and saves it in the repository.
     *
     * @param application The application associated with the notification.
     * @param message     The message of the notification.
     * @param candidate   The candidate associated with the notification.
     * @return The created notification.
     */
    @Transactional
    public Notification createNotification(Application application, String message, Candidate candidate) {
        NotificationBuilder notificationBuilder = new NotificationBuilder();
        Message message1 = new Message(message);
        notificationBuilder.withAll(message1, application, candidate);
        Notification notification = notificationBuilder.build();
        return notificationRepository.save(notification);
    }

    /**
     * Finds all notifications associated with a given candidate.
     *
     * @param candidate The candidate to find notifications for.
     * @return A list of notifications associated with the candidate.
     */
    public List<Notification> findNotificationsByCandidate(Candidate candidate) {
        Iterable<Application> applications = applicationService.allApplications();

        List<Notification> candidateNotifications = new ArrayList<>();
        for (Application application : applications) {
            if (application.candidate().equals(candidate)) {
                candidateNotifications.addAll(findNotificationsByApplication(application));
            }
        }
        return candidateNotifications;
    }

    /**
     * Finds all notifications associated with a given application.
     *
     * @param application The application to find notifications for.
     * @return A list of notifications associated with the application.
     */
    public List<Notification> findNotificationsByApplication(Application application) {
        Iterable<Notification> notifications = allNotifications();

        List<Notification> applicationNotifications = new ArrayList<>();

        for (Notification notification : notifications) {
            if (notification.application().equals(application)) {
                applicationNotifications.add(notification);
            }
        }
        return applicationNotifications;
    }

    /**
     * Finds a notification by its ID.
     *
     * @param id The ID of the notification to find.
     * @return The found notification, or null if not found.
     */
    public Notification findNotificationById(int id) {
        Iterable<Notification> notifications = allNotifications();
        for (Notification notification : notifications) {
            if (notification.identity().equals(id)) {
                return notification;
            }
        }
        return null;
    }

    /**
     * Retrieves all notifications.
     *
     * @return An Iterable containing all notifications.
     */
    public Iterable<Notification> allNotifications() {
        return notificationRepository.allNotifications();
    }
}
