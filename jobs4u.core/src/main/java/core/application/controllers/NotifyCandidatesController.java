package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.email.Email;
import core.services.ApplicationService;
import core.services.EmailService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class NotifyCandidatesController {
    private final ApplicationService applicationService = new ApplicationService();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final EmailService emailService = new EmailService();

    public List<Application> findApplicationsToNotify() {
        List<Application> appsToNotify = new ArrayList<>();

        for (Application application : applicationService.allApplications()) {
            if (application.status() == Status.ACCEPTED || application.status() == Status.DECLINED) {
                appsToNotify.add(application);
            }
        }

        return appsToNotify;
    }

    public String findCandidateEmail(Application application) {
        return application.candidate().user().email().toString();
    }

    public String checkApplicationStatus(Application application) {
        return application.status().toString();
    }

    public Email createEmail(String candidateEmail, String subject, String body) {
        return emailService.createEmail(candidateEmail, subject, body);
    }

    public void sendEmails(List<Email> emailsToSend) {
        emailService.sendAllEmails(emailsToSend);
    }

    public void sendEmailCostumer(Email emailsToSend) {
        emailService.sendEmailCostumer(emailsToSend);
    }


}
