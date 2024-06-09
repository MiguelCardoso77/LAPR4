package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;

/**
 * Represents the mode of a job opening.
 * This enum provides different modes that can be associated with a job opening.
 *
 * @author Miguel Cardoso
 */
public enum Mode implements ValueObject {
    REMOTE,
    HYBRID,
    ON_SITE
}
