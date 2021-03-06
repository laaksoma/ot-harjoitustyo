package battleships.domain;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player human;
    Player bot;

    @Before
    public void setUpTests() {
        human = new HumanPlayer("TestName");
        bot = new BotPlayer();
    }

    @Test
    public void addShipsForPlayerAddsCorrectShipToCorrectArea() {
        human.addShipForPlayer(1, 1, 3);
        
        assertEquals(3, human.getSea().getSea()[1][1]);
    }
    
    @Test
    public void updatePointsUpdatesCorrectly() {
        human.updatePoints(2);
        assertEquals(2, human.getPointsAsInt());
    }
    
    @Test
    public void setFinalPointsSetsCorrectly() {
        human.getSea().modifyMaskedSea(1, 1, 1);
        human.setFinalPoints(3); 
        
        assertEquals(196, human.getPointsAsInt());
    }

    @Test
    public void getSeaGetsSeaCorrectly() {
        Sea sea = human.getSea();

        assertTrue(sea.equals(human.getSea()));
    }

    @Test
    public void getNameReturnsNameCorrectly() {
        assertEquals("TestName", this.human.getName());
    }

    @Test
    public void getShipsReturnsShipsCorrectly() {
        this.human.setShips(2);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(1);

        assertTrue(list.equals(this.human.getShips()));
    }

    @Test
    public void setShipsSetsCorrectShips() {
        this.human.setShips(3);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(2);
        list.add(1);

        assertTrue(list.equals(this.human.getShips()));
    }
}
