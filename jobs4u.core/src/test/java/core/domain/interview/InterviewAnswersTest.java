package core.domain.interview;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterviewAnswersTest {
    private InterviewAnswers interviewAnswers;
    private List<String> answers;

    @BeforeEach
    void setUp() {
        answers = Arrays.asList("Answer 1", "Answer 2", "Answer 3");
        interviewAnswers = new InterviewAnswers(answers);
    }

    @Test
    void testEquals() {
        InterviewAnswers sameInterviewAnswers = new InterviewAnswers(answers);
        assertTrue(interviewAnswers.equals(sameInterviewAnswers));
    }

    @Test
    void testHashCode() {
        InterviewAnswers sameInterviewAnswers = new InterviewAnswers(answers);
        assertEquals(interviewAnswers.hashCode(), sameInterviewAnswers.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = answers.toString();
        assertEquals(expectedString, interviewAnswers.toString());
    }

    @Test
    void testAnswersList() {
        assertEquals(answers, interviewAnswers.answersList());
    }

}