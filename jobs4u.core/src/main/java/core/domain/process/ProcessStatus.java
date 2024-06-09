package core.domain.process;

import eapli.framework.domain.model.ValueObject;

/**
 * Represents the status of a process in a job application system.
 * This enum provides different statuses that a process can be in during the job application process.
 *
 * @author Diana Neves
 */
public enum ProcessStatus implements ValueObject {
    OPEN,
    CLOSE
}
