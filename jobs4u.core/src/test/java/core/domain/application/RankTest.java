package core.domain.application;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RankTest {
    @Test
    public void testConstructor() {
        String rankValue = "Manager";
        Rank rank = new Rank(rankValue);
        assertEquals(rankValue, rank.toString());
    }

    @Test
    public void testEquals() {
        Rank rank1 = new Rank("Manager");
        Rank rank2 = new Rank("Manager");
        Rank rank3 = new Rank("Supervisor");

        assertTrue(rank1.equals(rank2));
        assertTrue(rank2.equals(rank1));
        assertFalse(rank1.equals(rank3));
        assertFalse(rank3.equals(rank1));
        assertFalse(rank2.equals(rank3));
        assertFalse(rank3.equals(rank2));
    }

    @Test
    public void testValueOf() {
        String rankValue = "Developer";
        Rank rank = Rank.valueOf(rankValue);
        assertEquals(rankValue, rank.toString());
    }
}