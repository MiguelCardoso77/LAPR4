package core.domain.interview;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterviewModelTest {

    @Test
    public void testConstructorAndGetters() {
        InterviewModel model = new InterviewModel("Structured");
        assertEquals("Structured", model.toString());
    }

    @Test
    public void testEquals() {
        InterviewModel model1 = new InterviewModel("Structured");
        InterviewModel model2 = new InterviewModel("Structured");
        InterviewModel model3 = new InterviewModel("Unstructured");

        assertEquals(model1, model2);
        assertNotEquals(model1, model3);
    }

    @Test
    public void testHashCode() {
        InterviewModel model1 = new InterviewModel("Structured");
        InterviewModel model2 = new InterviewModel("Structured");

        assertEquals(model1.hashCode(), model2.hashCode());
    }

    @Test
    public void testToString() {
        InterviewModel model = new InterviewModel("Unstructured");
        assertEquals("Unstructured", model.toString());
    }
}
