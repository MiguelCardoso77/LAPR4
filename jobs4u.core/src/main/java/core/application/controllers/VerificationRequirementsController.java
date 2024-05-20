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
import java.util.List;

public class VerificationRequirementsController {

    private final ApplicationService appServ = new ApplicationService();


    public List<String> typeRequirements(List<String> candidateRequirements) {
        List<String> requirements = new ArrayList<>();


        for (String line : candidateRequirements) {
            int startIndex = line.indexOf("->");
            int endIndex = line.indexOf(":");
            if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                String extractedText = line.substring(startIndex + 2, endIndex).trim();
                requirements.add(extractedText);
            }
        }
        return requirements;
    }

    public List<String> typeRequirements (String jobRequirementsPath) {


        return null;

    }

    public Application findApplicationById(Application applicationID) {
        Iterable<Application> allApplications = appServ.allApplications();
        for (Application application : allApplications) {
            if (application.identity() == applicationID.identity() ) {
                return application;
            }
        }
        return null;
    }

    public boolean verifyCandidate( List<String> typeRequirements , JobRequirementsSpecification jobOpeningRequirement ){
        for (String requirement : typeRequirements) {








        }






        return false;
    }



}
