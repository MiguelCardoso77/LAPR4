package core.domain.process;

import eapli.framework.domain.model.ValueObject;

public enum ProcessState implements ValueObject {
    APPLICATION,
    SCREENING,
    INTERVIEWS,
    ANALYSIS,
    RESULT
}
