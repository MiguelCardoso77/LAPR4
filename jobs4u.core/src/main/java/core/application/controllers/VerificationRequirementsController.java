package core.application.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import plugin.requirements.RequirementsPlugin;

/**
 * The VerificationRequirementsController class provides methods for handling
 * job application verification based on candidate requirements. It integrates
 * with various controllers and plugins to perform operations such as mapping
 * candidate requirements, checking these requirements against a specified path,
 * selecting job openings, retrieving applications for a job opening, and changing
 * the interview status of an application.
 *
 * @author tomasgoncalves
 */
public class VerificationRequirementsController {

    private final RequirementsPlugin requirementsPlugin = new RequirementsPlugin();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    private final ListJobOpeningApplicationsController listJobOpeningApplicationsController = new ListJobOpeningApplicationsController();
    private final ChangeJobInterviewStatusController changeJobInterviewStatusController = new ChangeJobInterviewStatusController();

    /**
     * Maps a list of candidate requirements to a map where the key is the type of
     * requirement and the value is the corresponding requirement value.
     *
     * @param candidateRequirements A list of candidate requirements in the format "type: value".
     * @return A map with the requirement types as keys and the requirement values as values.
     */
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

    /**
     * Checks the candidate requirements against a set of predefined requirements
     * using the RequirementsPlugin.
     *
     * @param path The path to the requirements file or resource.
     * @param clientRequirements A map of client requirements to be checked.
     * @return true if the candidate requirements meet the predefined requirements, false otherwise.
     */
    public boolean pluginRequirements(String path, Map<String, String> clientRequirements) {

        return requirementsPlugin.checkRequirements(path, clientRequirements);
    }

    /**
     * Selects a job opening using the SelectJobOpeningController.
     *
     * @return The selected JobOpening object.
     */
    public JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpening();
    }

    /**
     * Retrieves all applications received for a specific job opening using the
     * ListJobOpeningApplicationsController.
     *
     * @param jobReference A reference to the job opening.
     * @return An iterable collection of Application objects received for the specified job opening.
     */
    public Iterable<Application> allApplicationsOfJobOpeningReceived(JobReference jobReference) {
        return listJobOpeningApplicationsController.allApplicationsOfJobOpeningReceived(jobReference);

    }

    /**
     * Changes the interview status of a specified application using the
     * ChangeJobInterviewStatusController.
     *
     * @param statusFinal The final status to be set for the application.
     * @param applicationToVerify The application whose interview status is to be changed.
     */
    public void changeJobInterviewStatus(Status statusFinal, Application applicationToVerify) {
        changeJobInterviewStatusController.changeJobInterviewStatus(statusFinal, applicationToVerify);
    }

}
