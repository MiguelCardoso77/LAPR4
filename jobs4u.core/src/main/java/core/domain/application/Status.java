package core.domain.application;

import eapli.framework.domain.model.ValueObject;

/**
 * Represents the status of an application.
 * This enum defines the possible states an application can be in:
 * - SUBMITTED: The application has been submitted.
 * - PENDING: The application is awaiting review.
 * - ACCEPTED: The application has been accepted.
 * - DECLINED: The application has been declined.
 *
 * @author Tomás Gonçalves
 */
public enum Status implements ValueObject {
    SUBMITTED,
    PENDING,
    ACCEPTED,
    DECLINED
}


