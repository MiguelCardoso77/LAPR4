package core.domain.interviewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterviewModelBuilderTest {
    private InterviewModelBuilder interviewModelBuilder;
    private String model;

    @BeforeEach
    void setUp() {
        model = "Test Model";
        interviewModelBuilder = new InterviewModelBuilder();
    }

    @Test
    void testWithoutId() {
        interviewModelBuilder.withoutId(model);
        InterviewModel interviewModel = interviewModelBuilder.build();

        assertEquals(model, interviewModel.model());
    }

    @Test
    void testBuild() {
        interviewModelBuilder.withoutId(model);
        InterviewModel interviewModel = interviewModelBuilder.build();

        assertNotNull(interviewModel);
    }
}