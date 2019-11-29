package battleships.domain;

import battleships.domain.BotPlayer;
import battleships.domain.Game;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BotPlayerTest {

    private BotPlayer bot;
    private ArrayList<String> directions;
    Game game;

    @Before
    public void createBot() {
        this.bot = new BotPlayer();
        this.game = Game.getInstance();
        this.directions = new ArrayList();

        this.directions.add("w");
        this.directions.add("a");
        this.directions.add("s");
        this.directions.add("d");
    }

    @Test
    public void getDirectionGetsDirectionCorrectly() {
        assertTrue(this.directions.contains(bot.getDirection()));
    }

    @Test
    public void getRowOrColumnReturnsIntBetweenZeroAndMax() {
        ArrayList<Integer> list = new ArrayList();
        list.add(0);
        list.add(1);
        list.add(2);

        assertTrue(list.contains(bot.getRowOrColumn(3)));
    }

    @Test
    public void decideCoordinatesGetsDirectionIfNeedForDirectionIsTrue() {
        assertTrue(this.directions.contains(this.bot.decideCoordinates(1, true, 5).getDirection()));
    }

    @Test
    public void decideCoordinatesGetsDirectionIfNeedForDirectionIsFalse() {
        assertEquals(null, this.bot.decideCoordinates(1, false, 5).getDirection());
    }
}
