package battleships;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player player;
    private ByteArrayOutputStream contentOutput = new ByteArrayOutputStream();
    private PrintStream originalOutput = System.out;
    private String input;

    @Before
    public void setUpTests() {
        player = new Player("Matt");
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

    @Test
    public void printSeaPrintsCorrectly() {

    }

//    @Test
//    public void clearTheSeaEmptiesTheArray() {
//        player.printSea();
//        
//        assertTrue(contentOutput.toString().contains("00000"));
//        assertTrue(contentOutput.toString().contains("00000"));
//        assertTrue(contentOutput.toString().contains("00000"));
//        assertTrue(contentOutput.toString().contains("00000"));
//        assertTrue(contentOutput.toString().contains("00000"));
//    }

    @Test
    public void getSeaGetsSeaCorrectly() {
        int[][] testSea = new int[5][5];

        for (int[] testSea1 : testSea) {
            for (int j = 0; j < testSea[0].length; j++) {
                testSea1[j] = 0;
            }
        }

        Assert.assertArrayEquals(testSea, player.getSea());
    }    //*

    @Test
    public void getSeaSizeReturnCorrectly() {
        assertEquals(5, player.getSeaSize());
    }

    @Test
    public void getNameReturnsNameCorrectly() {
        assertEquals("Matt", player.getName());
    }

    @Test
    public void getShipsReturnsShipsCorrectly() {
        this.player.setShips(2);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(1);

        assertTrue(list.equals(this.player.getShips()));
    }

    @Test
    public void setShipsSetsCorrectShips() {
        this.player.setShips(3);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3);
        list.add(2);
        list.add(1);

        assertTrue(list.equals(this.player.getShips()));
    }

    @Test
    public void addShipAddsCorrectly() {
        player.addShipToTheSea(0, 1, 1);

        assertEquals(1, player.sea[0][1]);
    }

    @Test
    public void seaIsEmptyReturnsTrueWhenEmpty() {
        assertTrue(player.seaIsEmpty());
    }

    @Test
    public void seaIsEmptyReturnsFalseWhenNotEmpty() {
        player.addShipToTheSea(0, 1, 1);

        assertFalse(player.seaIsEmpty());
    }

}
