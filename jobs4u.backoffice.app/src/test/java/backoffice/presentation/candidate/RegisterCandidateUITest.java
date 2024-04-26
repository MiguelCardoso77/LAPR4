package backoffice.presentation.candidate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterCandidateUITest {
    private final RegisterCandidateUI registerCandidateUI = new RegisterCandidateUI();

    @Test
    public void testHeadline() {
        String headline = registerCandidateUI.headline();
        Assertions.assertEquals("Register Candidate", headline);
    }

}
