package core.application.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import plugin.requirements.RequirementsPlugin;


public class VerificationRequirementsController {

    private final RequirementsPlugin requirementsPlugin = new RequirementsPlugin();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final ChangeJobInterviewStatusController changeJobInterviewStatusController = new ChangeJobInterviewStatusController();

    public Map<String, String> mapCandidate(List<String> candidateRequirements) {

        Map<String, String> candidateRequirementsMap = new HashMap<>();
        for (String requirement : candidateRequirements) {
            int endIndex = requirement.indexOf(":");
            if (endIndex != -1) {
                String typeRequirement = requirement.substring(0, endIndex).trim();
                String valueRequirement = requirement.substring(endIndex + 2).trim();
                candidateRequirementsMap.put(typeRequirement, valueRequirement);
            }
        }
        System.out.println(candidateRequirementsMap);
        return candidateRequirementsMap;

    }


    public boolean pluginRequirements(String path, Map<String, String> clientRequirements) {

        return requirementsPlugin.checkRequirements(path, clientRequirements);
    }

    public JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpening();
    }


    public Iterable<Application> allApplicationsOfJobOpeningReceived(JobReference jobReference) {
        return listJobOpeningApplicationsController.allApplicationsOfJobOpeningReceived(jobReference);

    }

    public void changeJobInterviewStatus(Status statusFinal, Application applicationToVerify) {
        changeJobInterviewStatusController.changeJobInterviewStatus(statusFinal, applicationToVerify);
    }

}
