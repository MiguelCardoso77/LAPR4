package backoffice.presentation.jobRequirementsSpecifications;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.domain.application.Status;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import plugin.requirements.RequirementsPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VerificationRequirementsUI extends AbstractUI {


    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final VerificationRequirementsController verificationRequirementsController = new VerificationRequirementsController();
    private final ChangeJobInterviewStatusController changeJobInterviewStatusController = new ChangeJobInterviewStatusController();
    private final RequirementsPlugin requirementsPlugin = new RequirementsPlugin();


    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String RESET = "\u001B[0m";

    @Override
    protected boolean doShow() {
        JobOpening jobOpening = selectJobOpeningController.selectJobOpening();
        JobReference jobReference = jobOpening.identity();

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

    /**
     * Retrieves all job openings.
     *
     * @return Iterable of all job openings.
     */
    protected Iterable<JobOpening> elements() {
        return listJobOpeningController.allJobOpenings();
    }

    @Override
    public String headline() {
        return "";
    }


}
