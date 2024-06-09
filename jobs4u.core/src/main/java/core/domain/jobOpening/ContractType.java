package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;

/**
 * Represents the type of contract for a job opening.
 * This enum provides different types of contracts that can be associated with a job opening.
 *
 * @author Miguel Cardoso
 */
public enum ContractType implements ValueObject {
    FULL_TIME,
    PART_TIME
}
