package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.Status;

import core.services.ApplicationService;
import core.services.NotificationService;

import java.util.ArrayList;
import java.util.List;
/**
 * Controller class for managing application state changes.
 * This class provides methods for finding applications to notify based on their status (accepted, declined, chosen, received).
 * It also provides methods for notifying applications based on their status and retrieving notifications for a specific candidate.
 *
 * @author 1220812@isep.ipp.pt
 */

public class CandidateApplicationStateController {
    /**
     * The service for accessing applications.
     */
    private final ApplicationService applicationService = new ApplicationService();
    /**
     * The service for managing notifications.
     */
    private final NotificationService notificationService = new NotificationService();
    /**
     * Finds all applications with the status 'ACCEPTED' to notify.
     *
     * @return A list of applications to notify.
     */

    public List<Application> findAcceptedApplicationToNotify(){
        List<Application> appsToNotify = new ArrayList<>();

        for (Application application : applicationService.allApplications()) {
            if(application.status() == Status.ACCEPTED){
                appsToNotify.add(application);
            }
        }
        return appsToNotify;
    }

    /**
     * Finds all applications with the status 'DECLINED' to notify.
     *
     * @return A list of applications to notify.
     */

    public List<Application> findDeclinedApplicationToNotify(){
        List<Application> appsToNotify = new ArrayList<>();

        for (Application application : applicationService.allApplications()) {
            if(application.status() == Status.DECLINED){
                appsToNotify.add(application);
            }
        }
        return appsToNotify;
    }

    /**
     * Finds all applications with the status 'CHOSEN' to notify.
     *
     * @return A list of applications to notify.
     */

    public List<Application> findReceivedApplicationsToNotify(){
        List<Application> appsToNotify = new ArrayList<>();

        for (Application application : applicationService.allApplications()) {
            if(application.status() == Status.RECEIVED){
                appsToNotify.add(application);
            }
        }
        return appsToNotify;
    }

    /**
     * Finds all applications with the status 'RECEIVED' to notify.
     *
     * @return A list of applications to notify.
     */

    public List<Application> findChosenApplications(){
        List<Application> appsToNotify = new ArrayList<>();

        for (Application application : applicationService.allApplications()) {
            if(application.status() == Status.CHOSEN){
                appsToNotify.add(application);
            }
        }
        return appsToNotify;
    }
    /**
     * Notifies all applications with the status 'ACCEPTED'.
     */
    public void notifyAcceptedApplications(){
        List<Application> appsToNotify = findAcceptedApplicationToNotify();
        for (Application application : appsToNotify) {
            notificationService.createNotification(application, "Your application has been accepted!", application.candidate());
        }
    }

    /**
     * Notifies all applications with the status 'DECLINED'.
     */

    public void notifyDeclinedApplications(){
        List<Application> appsToNotify = findDeclinedApplicationToNotify();
        for (Application application : appsToNotify) {
            notificationService.createNotification(application, "Your application has been declined!", application.candidate());
        }
    }

    /**
     * Notifies all applications with the status 'CHOSEN'.
     */

    public void notifyChosenApplications(){
        List<Application> appsToNotify = findChosenApplications();
        for (Application application : appsToNotify) {
            notificationService.createNotification(application, "You have been chosen for the job!", application.candidate());
        }
    }

    /**
     * Notifies all applications with the status 'RECEIVED'.
     */

    public void notifyReceivedApplications(){
        List<Application> appsToNotify = findReceivedApplicationsToNotify();
        for (Application application : appsToNotify) {
            notificationService.createNotification(application, "Your application has been received!", application.candidate());
        }
    }
}