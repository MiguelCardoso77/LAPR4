package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import plugin.requirements.RequirementsPlugin;

import java.util.List;

public class UploadRequirementsAnswersController {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();

    public void uploadRequirements(List<String> requirements, Application application) {
        CandidateRequirements candidateRequirements = new CandidateRequirements(requirements);

        application.uploadCandidateRequirements(candidateRequirements);
        applicationRepository.save(application);
    }


    public List<String> retrieveResponseRequirements(String path){
        RequirementsPlugin plugin = new RequirementsPlugin();
        return plugin.retrieveAnswersRequirements(path);
    }

}
