package core.domain.notification;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.util.Objects;

/**
 * Represents a message.
 *
 * @author Diogo Ribeiro
 */
@Embeddable
public class Message implements ValueObject {
    private String message;

    /**
     * Default constructor for ORM (Object-Relational Mapping) purposes.
     */
    protected Message() {
        // for ORM
    }

    /**
     * Constructs a {@code Message} with the specified message.
     *
     * @param message The message string.
     */
    public Message(String message) {
        this.message = message;
    }

    /**
     * Retrieves the message instance from the given message string.
     *
     * @param message the message string.
     * @return a new {@code Message} instance.
     */
    public static Message valueOf(String message) {
        return new Message(message);
    }

    /**
     * Checks if this message is equal to another object.
     *
     * @param o The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message, message1.message);
    }

    /**
     * Computes the hash code of this message.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    /**
     * Returns the string representation of this message.
     *
     * @return the message string.
     */
    @Override
    public String toString() {
        return message;
    }
}
