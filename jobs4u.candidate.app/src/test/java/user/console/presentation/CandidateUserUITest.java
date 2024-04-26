package user.console.presentation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CandidateUserUITest {
    private final CandidateUserUI candidateUserUI = new CandidateUserUI() {
        @Override
        protected boolean doShow() {
            return false;
        }

        @Override
        public boolean show() {
            return false;
        }
    };

    @Test
    public void testHeadlineAnonymousUser() {
        String headline = candidateUserUI.headline();

        assertEquals("Candidate App [ ==Anonymous== ]", headline);
    }

    @Test
    public void testDrawFormTitle() {
        String title = "Test Title";

        candidateUserUI.drawFormTitle(title);
    }

}