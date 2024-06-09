package core.domain.process;

import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.util.Calendar;

/**
 * Represents a process associated with a job opening.
 *
 * @author Diana Neves
 */
@Entity
@Table(name = "PROCESS")
public class Process implements AggregateRoot<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProcess;
    @Enumerated(EnumType.STRING)
    @Column(name = "PROCESS_STATE")
    private ProcessState processState;

    @Temporal(TemporalType.DATE)
    private Calendar processDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "PROCESS_STATUS")
    private ProcessStatus processStatus;

    /**
     * Constructs a Process object with the given parameters.
     *
     * @param processState  the state of the process
     * @param processDate   the date of the process
     * @param processStatus the status of the process
     */
    public Process(ProcessState processState, Calendar processDate, ProcessStatus processStatus){
        this.processState = processState;
        this.processDate = processDate;
        this.processStatus = processStatus;
    }

    /**
     * Default constructor required by ORM frameworks.
     */
    protected Process() {
        // for ORM only
    }

    /**
     * Checks if this Process is equal to another object.
     *
     * @param other the object to compare to
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean sameAs(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Process)){
            return false;
        }

        final Process that = (Process) other;

        return idProcess.equals(that.idProcess) && processState.equals(that.processState) && processDate.equals(that.processDate);
    }

    /**
     * Compares this Process to another Integer object.
     *
     * @param other the Integer object to compare to
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    public int compareTo(Integer other) { return AggregateRoot.super.compareTo(other); }

    /**
     * Retrieves the identity of this Process.
     *
     * @return the identity of this Process
     */
    @Override
    public Integer identity() {
        return idProcess;
    }

    /**
     * Retrieves the state of the process.
     *
     * @return the process state
     */
    public ProcessState processState() { return this.processState; }
    /**
     * Retrieves the status of the process.
     *
     * @return the process state
     */

    public ProcessStatus processStatus() { return this.processStatus; }

    /**
     * Retrieves the date of the process.
     *
     * @return the process date
     */
    public Calendar processDate() { return this.processDate; }

    /**
     * Changes the status of the associated process.
     */
    public void changeProcessStatus(ProcessStatus processStatus) { this.processStatus = processStatus; }
    /**
     * Changes the state of the state process.
     */
    public void changeProcessState(ProcessState processState) { this.processState = processState; }
    /**
     * Returns a string representation of this process
     * The string representation includes the process id, state, state date and status
     *
     * @return A string representation of this process.
     */
    @Override
    public String toString() {
        return "Process : " + idProcess +
                ", state = " + processState +
                ", state date = " + processDate +
                ", status = " + processStatus;
    }
}