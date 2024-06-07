package core.application.controllers;

import core.domain.notification.Notification;
import core.services.CandidateNotificationsService;
import core.services.NotificationService;

import java.util.List;

/**
 * Controller to handle the use case of listing all notifications for a specific candidate.
 * This class coordinates the interaction between the UI and the service layer.
 * It uses the CandidateNotificationsService to fetch the notifications for a candidate.
 *
 * @author 1220812@isep.ipp.pt
 */

public class ListCandidateNotificationsController {
    private final CandidateNotificationsService candidateNotificationsService = new CandidateNotificationsService();

    /**
     * Sends a request to the server to retrieve all notifications for a specific candidate.
     *
     * @param email The email of the candidate.
     * @return A list of notifications for the candidate.
     */

    public List<String> sendNotificationsRequest(String email) {
        return candidateNotificationsService.requestNotifications(email);
    }
}
