package core.services;

import core.domain.application.CandidateRequirements;

import java.util.List;

public class CandidateRequirementsService {
    public CandidateRequirements registerCandidateRequirements(List<String> requirements) {
        return new CandidateRequirements(requirements);
    }
}
