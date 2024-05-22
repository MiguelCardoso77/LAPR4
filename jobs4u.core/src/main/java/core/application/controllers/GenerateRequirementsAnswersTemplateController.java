package core.application.controllers;

import core.domain.jobOpening.JobOpening;

import java.util.ArrayList;
import java.util.List;

public class GenerateRequirementsAnswersTemplateController {

    final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();

    public List<JobOpening> findAllJobOpeningsWithJobRequirements() {
        Iterable<JobOpening> jobOpenings = listJobOpeningController.allJobOpenings();
        List<JobOpening> jobOpeningsWithRequirements = new ArrayList<>();

        for (JobOpening jobOpening : jobOpenings){
            if (jobOpening.jobRequirementsSpecification() != null) {
                jobOpeningsWithRequirements.add(jobOpening);
            }
        }
        return jobOpeningsWithRequirements;
    }
}
