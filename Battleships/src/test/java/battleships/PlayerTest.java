package battleships;

import battleships.domain.BotPlayer;
import battleships.domain.HumanPlayer;
import battleships.domain.Player;
import battleships.domain.Sea;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player human;
    Player bot;
    private ByteArrayOutputStream contentOutput = new ByteArrayOutputStream();
    private PrintStream originalOutput = System.out;
    private String input;

    @Before
    public void setUpTests() {
        human = new HumanPlayer("Matt");
        bot = new BotPlayer();
        System.setOut(new PrintStream(contentOutput));
    }

    @After
    public void restoreDefaultStreams() {
        System.out.flush();
        System.setOut(originalOutput);
    }

    @Test
    public void createSeaCreatesArrayCorrectly() {

    }

//    @Test
//    public void getSeaGetsSeaCorrectly() {
//        Sea sea = new Sea();
//
//        assertEquals(sea, human.getSea());
//    }

//    @Test         MOVE THIS TO SEATEST
//    public void getSeaGetsSeaCorrectly() {
//        Sea sea = new Sea();
//
//        int[][] array = new int[5][5];
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[0].length; j++) {
//                array[i][j] = 0;
//            }
//        }
//
//        Assert.assertArrayEquals(array, sea.getSea());
//    }

    @Test
    public void getNameReturnsNameCorrectly() {
        assertEquals("Matt", this.human.getName());
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
