package core.domain.process;

public class Process {
    private Process process;

    public Process(Process process) {
        this.process = process;
    }

    protected Process() {
        // for ORM only
    }

    public Process process() {
        return this.process;
    }

    @Override
    public boolean equals(final Object o) {
        return this.process.equals(o);
    }

    @Override
    public int hashCode() {
        return this.process.hashCode();
    }

}
