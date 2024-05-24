package core.domain.application;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import java.util.List;

@Embeddable
public class CandidateRequirements implements ValueObject {

    private List<String> candidateRequirements;

    public CandidateRequirements(List<String> candidateRequirements){
        this.candidateRequirements = candidateRequirements;
    }

    protected CandidateRequirements(){
        // for ORM
    }

    @Override
    public boolean equals(final Object o){
        if (this == o){
            return true;
        }
        if (!(o instanceof CandidateRequirements)){
            return false;
        }
        final CandidateRequirements that = (CandidateRequirements) o;
        return this.candidateRequirements.equals(that.candidateRequirements);
    }

    public List<String> candidateRequirements(){
        return this.candidateRequirements;
    }

    @Override
    public int hashCode(){ return this.candidateRequirements.hashCode(); }

    @Override
    public String toString() { return candidateRequirements.toString(); }

}
