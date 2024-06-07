package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.Status;
import core.services.ApplicationService;
import core.services.NotificationService;

/**
 * A controller class for managing the status of job interviews.
 * This class provides a method for changing the status of a job interview.
 * It uses the ApplicationService and NotificationService to update the status of an application
 * and create a notification for the candidate respectively.
 *
 * @author 1220812@isep.ipp.pt
 */

public class ChangeJobInterviewStatusController {
    private final ApplicationService applicationService = new ApplicationService();
    private final NotificationService notificationService = new NotificationService();

    /**
     * Changes the status of a job interview for a given application.
     * If the status is ACCEPTED, a notification is created for the candidate indicating they have been accepted for the job opening.
     * If the status is not ACCEPTED, a notification is created for the candidate indicating they have been declined for the job opening.
     *
     * @param applicationStatus The new status of the application.
     * @param application The application for which the status is to be changed.
     */
    public void changeJobInterviewStatus(Status applicationStatus, Application application) {
        applicationService.updateStatus(applicationStatus, application);
        if(applicationStatus == Status.ACCEPTED){
            notificationService.createNotification(application, "You have been accepted for the job opening", application.candidate());
        } else{
            notificationService.createNotification(application, "You have been declined for the job opening", application.candidate());
        }
    }
}
