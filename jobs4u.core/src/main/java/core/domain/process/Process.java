package core.domain.process;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

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

    @Column(name = "PROCESS_DATE")
    private ProcessDate processDate;

    @Column(name = "JOB_REFERENCE")
    private JobReference jobReference;

    /**
     * Constructs a Process object with the given parameters.
     *
     * @param idProcess     the unique identifier of the process
     * @param processState  the state of the process
     * @param processDate   the date of the process
     * @param jobReference  the reference to the associated job opening
     */
    public Process(Integer idProcess,ProcessState processState, ProcessDate processDate, JobReference jobReference){
        this.idProcess = idProcess;
        this.processState = processState;
        this.processDate = processDate;
        this.jobReference = jobReference;
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

        return idProcess.equals(that.idProcess) && processState.equals(that.processState) && processDate.equals(that.processDate) && jobReference.equals(that.jobReference);
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
     * Retrieves the date of the process.
     *
     * @return the process date
     */
    public ProcessDate processDate() { return  this.processDate; }

    /**
     * Retrieves the reference to the associated job opening.
     *
     * @return the job reference
     */
    public JobReference jobReference() { return this.jobReference; }
}
