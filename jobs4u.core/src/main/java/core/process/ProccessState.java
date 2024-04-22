package core.process;

import eapli.framework.domain.model.ValueObject;

public enum ProccessState implements ValueObject {
    APPLICATION,
    SCREENING,
    INTERVIEWS,
    ANALYSIS,
    RESULT
}
