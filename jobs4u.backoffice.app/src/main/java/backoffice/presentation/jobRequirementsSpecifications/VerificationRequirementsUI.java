package backoffice.presentation.jobRequirementsSpecifications;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.domain.application.Status;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Map;

public class VerificationRequirementsUI extends AbstractUI {
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final VerificationRequirementsController verificationRequirementsController = new VerificationRequirementsController();
    private final ChangeJobInterviewStatusController changeJobInterviewStatusController = new ChangeJobInterviewStatusController();

    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String RESET = "\u001B[0m";

    @Override
    protected boolean doShow() {
        JobOpening jobOpening = selectJobOpeningController.selectJobOpening();
        JobReference jobReference = jobOpening.jobReference();

        Iterable<Application> jobOpeningApplications = listJobOpeningApplicationsController.allApplicationsOfJobOpening(jobReference);

        if (jobOpeningApplications == null) {
            System.out.println(RED + "No applications for this job opening" + RESET);
        } else {
            for (Application applicationToVerify : jobOpeningApplications) {
                if (applicationToVerify != null) {

                    CandidateRequirements candidateRequirements = applicationToVerify.candidateRequirements();
                    String path = jobOpening.jobRequirementsSpecification().jobRequirementsPath();


                    Map<String, String> clientRequirements = verificationRequirementsController.mapCandidate(candidateRequirements.candidateRequirements());

                    boolean result = verificationRequirementsController.pluginRequirements(path, clientRequirements);

                    Status statusFinal;

                    if (result) {
                        statusFinal = Status.ACCEPTED;
                        changeJobInterviewStatusController.changeJobInterviewStatus(statusFinal, applicationToVerify);
                        System.out.println(GREEN + "The candidate is valid for this job opening" + RESET);
                    } else {
                        statusFinal = Status.DECLINED;
                        changeJobInterviewStatusController.changeJobInterviewStatus(statusFinal, applicationToVerify);
                        System.out.println(RED + "This candidate isn't valid for this job opening" + RESET);
                    }
                    System.out.println("=========================================================================================================");
                }
            }
        }
        return true;
    }



    @Override
    public String headline() {
        return "";
    }


}
