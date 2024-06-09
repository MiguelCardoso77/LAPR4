package core.domain.process;

import eapli.framework.domain.model.ValueObject;
/**
 * Represents the state of a process in a job application system.
 * This enum provides different states that a process can be in during the job application process.
 *
 * @author Miguel Cardoso
 */
public enum ProcessState implements ValueObject {
    APPLICATION,
    SCREENING,
    INTERVIEWS,
    ANALYSIS,
    RESULT
}
