package battleships.domain;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ScoreTest {

    @Test
    public void constructorSetsNameCorrectly() {
        Score testScore = new Score("Jane", 201);

        assertEquals("Jane", testScore.getName());
    }

    @Test
    public void constructorSetsPointsCorrectly() {
        Score testScore = new Score("Jane", 201);

        assertEquals(201, testScore.getPoints());
    }
}
