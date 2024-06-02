package core.domain.candidate;

import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidateBuilderTest {
    private CandidateBuilder candidateBuilder;
    private SystemUser candidateUser;
    private TelephoneNumber telephoneNumber;
    private Curriculum curriculum;

    @BeforeEach
    void setUp() {
        candidateUser = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username2", "password", "firstName", "lastName", "email2@gmail.com").build();
        telephoneNumber = new TelephoneNumber("123456789");
        curriculum = new Curriculum("test_curriculum.txt");

        candidateBuilder = new CandidateBuilder();
    }

    @Test
    void testWithAll() {
        candidateBuilder.withAll(candidateUser, telephoneNumber, curriculum);
        Candidate candidate = candidateBuilder.build();

        assertEquals(candidateUser, candidate.user());
        assertEquals(telephoneNumber, candidate.identity());
        assertEquals(curriculum, candidate.curriculum());
    }

    @Test
    void testBuild() {
        candidateBuilder.withAll(candidateUser, telephoneNumber, curriculum);
        Candidate candidate = candidateBuilder.build();

        assertNotNull(candidate);
    }
}