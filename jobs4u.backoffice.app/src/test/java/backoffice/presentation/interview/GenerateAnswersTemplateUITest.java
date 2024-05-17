package backoffice.presentation.interview;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenerateAnswersTemplateUITest {
    @Test
    void headline() {
        GenerateAnswersTemplateUI ui = new GenerateAnswersTemplateUI();
        assertEquals("Generate Answers Template", ui.headline());
    }
}