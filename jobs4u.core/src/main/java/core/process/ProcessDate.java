package core.process;

import eapli.framework.domain.model.ValueObject;

import java.util.Calendar;

public class ProcessDate implements ValueObject {
    private Calendar processDate;

    public ProcessDate(Calendar processDate) {
        this.processDate = processDate;
    }

    protected ProcessDate() {
        // for ORM
    }

    public static ProcessDate valueOf(Calendar processDate) {
        return new ProcessDate(processDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcessDate)) {
            return false;
        }

        final ProcessDate that = (ProcessDate) o;
        return this.processDate.equals(that.processDate);
    }

    @Override
    public int hashCode() {
        return processDate.hashCode();
    }

    @Override
    public String toString() {
        return processDate.toString();
    }
}
