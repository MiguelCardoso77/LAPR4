package core.domain.interview;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {

    @Test
    public void testConstructorAndGetters() {
        Score score = new Score(75);
        assertEquals(75, score.score());
    }

    @Test
    public void testEquals() {
        Score score1 = new Score(80);
        Score score2 = new Score(80);
        Score score3 = new Score(75);

        assertEquals(score1, score2);
        assertNotEquals(score1, score3);
    }

    @Test
    public void testHashCode() {
        Score score1 = new Score(85);
        Score score2 = new Score(85);

        assertEquals(score1.hashCode(), score2.hashCode());
    }

    @Test
    public void testToString() {
        Score score = new Score(90);
        assertEquals("Score = 90", score.toString());
    }
}
