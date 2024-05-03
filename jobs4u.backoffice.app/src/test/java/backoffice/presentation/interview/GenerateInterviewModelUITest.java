package backoffice.presentation.interview;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateInterviewModelUITest {
    @Test
    void headline() {
        GenerateInterviewModelUI ui = new GenerateInterviewModelUI();
        assertEquals("Generate Template File", ui.headline());
    }
}