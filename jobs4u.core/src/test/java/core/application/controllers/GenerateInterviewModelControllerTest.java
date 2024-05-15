package core.application.controllers;

import static org.junit.jupiter.api.Assertions.*;

import core.domain.interview.QuestionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;
import java.util.List;

public class GenerateInterviewModelControllerTest {
    private GenerateInterviewModelController controller;

    @BeforeEach
    void setUp() {
        controller = new GenerateInterviewModelController();
    }

    @Test
    void testGetQuestionTypes() {
        List<String> questionTypes = controller.getQuestionTypes();
        assertNotNull(questionTypes);
        assertEquals(QuestionType.values().length, questionTypes.size());
    }

}