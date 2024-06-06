package core.application.controllers;

import core.domain.application.Application;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.ApplicationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import plugin.requirements.RequirementsPlugin;


public class VerificationRequirementsController {

    private final RequirementsPlugin requirementsPlugin = new RequirementsPlugin();


    public Map<String, String> mapCandidate(List<String> candidateRequirements) {

        Map<String, String> candidateRequirementsMap = new HashMap<>();
        for (String requirement : candidateRequirements) {
            int endIndex = requirement.indexOf(":");
            if (endIndex != -1 ) {
                String typeRequirement = requirement.substring(0, endIndex).trim();
                String valueRequirement = requirement.substring(endIndex + 2).trim();
                candidateRequirementsMap.put(typeRequirement, valueRequirement);
            }
        }
        System.out.println(candidateRequirementsMap);
        return candidateRequirementsMap;

}


        public boolean pluginRequirements ( String path , Map<String, String> clientRequirements) {

         boolean status = requirementsPlugin.checkRequirements(path, clientRequirements);

        return status;
        }



}
