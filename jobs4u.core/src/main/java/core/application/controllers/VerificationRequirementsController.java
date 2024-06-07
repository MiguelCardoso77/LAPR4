package core.application.controllers;

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


}
