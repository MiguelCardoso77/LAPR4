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

    private final VerificationRequirementsController verificationRequirementsController = new VerificationRequirementsController();

    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String RESET = "\u001B[0m";

    @Override
    protected boolean doShow() {
        JobOpening jobOpening = verificationRequirementsController.selectJobOpening();
        JobReference jobReference = jobOpening.jobReference();

        Iterable<Application> jobOpeningApplications = verificationRequirementsController.allApplicationsOfJobOpeningReceived(jobReference);

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
                        verificationRequirementsController.changeJobInterviewStatus(statusFinal, applicationToVerify);
                        System.out.println(GREEN + "The candidate is valid for this job opening" + RESET);
                    } else {
                        statusFinal = Status.DECLINED;
                        verificationRequirementsController.changeJobInterviewStatus(statusFinal, applicationToVerify);
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
