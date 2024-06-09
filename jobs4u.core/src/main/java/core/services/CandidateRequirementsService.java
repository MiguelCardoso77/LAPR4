package core.services;

import core.domain.application.CandidateRequirements;

import java.util.List;

/**
 * Service class responsible for managing candidate requirements.
 * This class provides methods to handle operations related to candidate requirements.
 *
 * @author Diana Neves
 */
public class CandidateRequirementsService {

    /**
     * Registers the candidate requirements based on the provided list of strings.
     *
     * @param requirements The list of strings representing candidate requirements
     * @return The CandidateRequirements object representing the registered candidate requirements
     */
    public CandidateRequirements registerCandidateRequirements(List<String> requirements) {
        return new CandidateRequirements(requirements);
    }
}
