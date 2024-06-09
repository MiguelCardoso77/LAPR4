package core.domain.notification;

import core.domain.application.Application;
import core.domain.candidate.Candidate;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Represents a notification in the system.
 * A notification is associated with an application, a message, and a candidate.
 * This class is an aggregate root in the domain-driven design context.
 *
 * @author Diogo Ribeiro
 */
@Entity
@Table(name = "NOTIFICATION")
public class Notification implements AggregateRoot<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JoinColumn(name = "APPLICATION_ID")
    @ManyToOne
    private Application application;

    @Column(name = "MESSAGE")
    private Message message;

    @JoinColumn(name = "CANDIDATE_ID")
    @ManyToOne
    private Candidate candidate;
    private boolean read;

    /**
     * Protected constructor used by ORM.
     */
    protected Notification() {
        // for ORM
    }

    /**
     * Creates a new notification.
     *
     * @param application The application associated with the notification.
     * @param message The message of the notification.
     * @param candidate The candidate associated with the notification.
     */
    public Notification(Application application, Message message, Candidate candidate) {
        this.application = application;
        this.message = message;
        this.candidate = candidate;
    }

    /**
     * Checks if this notification is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id) && Objects.equals(application, that.application) && Objects.equals(message, that.message) && Objects.equals(candidate, that.candidate);
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, application, message, candidate);
    }

    /**
     * Checks if this notification is the same as another object.
     *
     * @param other The object to compare with.
     * @return true if the objects are the same, false otherwise.
     */
    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * Returns the identity of this notification.
     *
     * @return the identity of this notification.
     */
    @Override
    public Integer identity() {
        return id;
    }

    /**
     * Returns the application associated with this notification.
     *
     * @return the application associated with this notification.
     */

    public Application application() {
        return application;
    }

    /**
     * Returns the message of this notification.
     *
     * @return the message of this notification.
     */
    public Message message() {
        return message;
    }

    /**
     * Returns the candidate associated with this notification.
     *
     * @return the candidate associated with this notification.
     */
    public Candidate candidate() {
        return candidate;
    }

    /**
     * Returns the read status of this notification.
     *
     * @return the read status of this notification.
     */
    public boolean read() {
        return read;
    }

    /**
     * Marks this notification as read.
     */
    public void readMarker(){
        this.read = true;
    }

    /**
     * Returns a string representation of this notification.
     * The string representation includes the id, application, message, and candidate.
     *
     * @return a string representation of this notification.
     */
    @Override
    public String toString() {
        return application().notificationServer();
    }
}