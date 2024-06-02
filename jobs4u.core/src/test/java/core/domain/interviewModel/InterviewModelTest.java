package core.domain.interviewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterviewModelTest {

    private InterviewModel interviewModel;
    private String model;

    @BeforeEach
    void setUp() {
        model = "Test Model";
        interviewModel = new InterviewModel(model);
    }

    @Test
    void testEquals() {
        InterviewModel sameInterviewModel = new InterviewModel(model);
        assertTrue(interviewModel.equals(sameInterviewModel));
    }

    @Test
    void testHashCode() {
        InterviewModel sameInterviewModel = new InterviewModel(model);
        assertEquals(interviewModel.hashCode(), sameInterviewModel.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "Model : " + model;
        assertEquals(expectedString, interviewModel.toString());
    }

    @Test
    void testModel() {
        assertEquals(model, interviewModel.model());
    }

}