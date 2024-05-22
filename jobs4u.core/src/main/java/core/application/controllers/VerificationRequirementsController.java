package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.user.Jobs4URoles;
import core.services.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerificationRequirementsController {

    private final ApplicationService appServ = new ApplicationService();



    public Application findApplicationById(Application applicationID) {
        Iterable<Application> allApplications = appServ.allApplications();
        for (Application application : allApplications) {
            if (application.identity() == applicationID.identity()) {
                return application;
            }
        }
        return null;
    }

    public boolean verifyCandidate(List<String> jobRequirements, List<String> candidateRequirements) {
        Map<String, List<String>> jobRequirementsMap = new HashMap<>();

        for (String requirement : jobRequirements) {
            int startIndex = requirement.indexOf("->");
            int endIndex = requirement.indexOf(":");
            if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                String typeRequirement = requirement.substring(startIndex + 2, endIndex).trim();
                String valueRequirement = requirement.substring(endIndex + 2).trim();
                String[] values = valueRequirement.split(",");
                List<String> valueList = new ArrayList<>();
                for (String value : values) {
                    valueList.add(value.trim());
                }
                jobRequirementsMap.put(typeRequirement, valueList);
            }
        }

        Map<String, List<String>> candidateRequirementsMap = new HashMap<>();
        for (String requirement : candidateRequirements) {
            int startIndex = requirement.indexOf("->");
            int endIndex = requirement.indexOf(":");
            if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                String typeRequirement = requirement.substring(startIndex + 2, endIndex).trim();
                String valueRequirement = requirement.substring(endIndex + 2).trim();
                String[] values = valueRequirement.split(",");
                List<String> valueList = new ArrayList<>();
                for (String value : values) {
                    valueList.add(value.trim());
                }
                candidateRequirementsMap.put(typeRequirement, valueList);
            }
        }

        for (String typeRequirement : jobRequirementsMap.keySet()) {
            List<String> jobValues = jobRequirementsMap.get(typeRequirement);
            List<String> candidateValues = candidateRequirementsMap.get(typeRequirement);

            if (typeRequirement.equals("Years of Experience")) {
                if (candidateValues != null) {
                    try {
                        int jobYears = Integer.parseInt(jobValues.get(0));
                        int candidateYears = Integer.parseInt(candidateValues.get(0));
                        if (candidateYears >= jobYears) {
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
                return false;
            } else {
                if (candidateValues == null) {
                    return false;
                }
                for (String jobValue : jobValues) {
                    if (!candidateValues.contains(jobValue)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public List<String> listJobRequirements(JobRequirementsSpecification jobOpeningRequirement) {

        List<String> jobRequirements = new ArrayList<>();
        try {
            jobRequirements.addAll(Files.readAllLines(Paths.get(jobOpeningRequirement.jobRequirementsPath())));
        } catch (IOException e) {
            System.out.println("Error reading file" + e.getMessage());
        }

        return jobRequirements;
    }


}
