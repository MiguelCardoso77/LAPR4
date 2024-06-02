package core.domain.candidate;

import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidateTest {
    private Candidate candidate;
    private SystemUser candidateUser;
    private TelephoneNumber telephoneNumber;
    private Curriculum curriculum;

    @BeforeEach
    void setUp() {
        candidateUser = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username2", "password", "firstName", "lastName", "email2@gmail.com").build();
        telephoneNumber = new TelephoneNumber("123456789");
        curriculum = new Curriculum("test_curriculum.txt");

        candidate = new Candidate(candidateUser, telephoneNumber, curriculum);
    }

    @Test
    void testUser() {
        assertEquals(candidateUser, candidate.user());
    }

    @Test
    void testCurriculum() {
        assertEquals(curriculum, candidate.curriculum());
    }

    @Test
    void testIdentity() {
        assertEquals(telephoneNumber, candidate.identity());
    }

    @Test
    void testSameAs() {
        Candidate sameCandidate = new Candidate(candidateUser, telephoneNumber, curriculum);
        assertTrue(candidate.sameAs(sameCandidate));
    }

    @Test
    void testEquals() {
        Candidate sameCandidate = new Candidate(candidateUser, telephoneNumber, curriculum);
        assertEquals(candidate, sameCandidate);
    }

}