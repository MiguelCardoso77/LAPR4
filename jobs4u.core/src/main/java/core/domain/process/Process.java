package core.domain.process;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

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

    public Process(Integer idProcess,ProcessState processState, ProcessDate processDate, JobReference jobReference){
        this.idProcess = idProcess;
        this.processState = processState;
        this.processDate = processDate;
        this.jobReference = jobReference;
    }

    protected Process() {
        // for ORM only
    }

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

    public int compareTo(Integer other) { return AggregateRoot.super.compareTo(other); }

    @Override
    public Integer identity() {
        return idProcess;
    }

    public ProcessState processState() { return this.processState; }

    public ProcessDate processDate() { return  this.processDate; }

    public JobReference jobReference() { return this.jobReference; }
}
