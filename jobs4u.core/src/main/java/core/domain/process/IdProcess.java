package core.domain.process;

import core.domain.application.IdApplication;
import eapli.framework.domain.model.ValueObject;

import java.util.Objects;

public class IdProcess implements ValueObject, Comparable<IdProcess> {

    private long idProcess;

    public IdProcess(final long idProcess){
        if (idProcess <= 0 ) {
            throw  new IllegalArgumentException("IdProcess should not be empty");
        }

        this.idProcess = idProcess + 1;
    }

    protected IdProcess(){
        // for ORM
    }

    public static IdProcess valueOf(final long idProcess) { return new IdProcess(idProcess); }

    @Override
    public  boolean equals(Object o) {
        if (this == o ) return  true;
        if (o == null || getClass() != o.getClass()) return  false;
        IdProcess that = (IdProcess) o;
        return  idProcess == that.idProcess;
    }

    @Override
    public int hashCode() { return Objects.hash(idProcess); }

    @Override
    public String toString() { return String.valueOf(idProcess); }

    @Override
    public int compareTo(IdProcess o) { return Long.compare(idProcess, o.idProcess); }

}
