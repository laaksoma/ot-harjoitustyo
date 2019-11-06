package battleships;

import java.lang.reflect.Array;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {

    Player player;

    @Before
    public void setUpTestPlayer() {
        player = new Player("Matt");
    }

    @Test
    public void createSeaCreatesArrayCorrectly() {

    }

    @Test
    public void printSeaPrintsSeaCorrectly() {
           
    }

    @Test
    public void getNameReturnsNameCorrectly() {
        assertEquals("Matt", player.getName());
    }

    @Test
    public void getShipsReturnsShipsCorrectly() {
    }
    
    @Test
    public void setShipsSetsCorrectAmount() {
        this.player.setShips(5);
        
        assertEquals(5, this.player.getShips().size());
    }

//    @Test
//    public void setShipsSetsCorrectShipLenghts() {
//        this.player.setShips(3);
//        ArrayList<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        
//        //assertTrue(list.equals(this.player.getShips()));
//    }

//    @Test
//    public void getSeaReturnsSeaCorrectly() {
//        int[][] array = this.player.createSea();
//
//        assertTrue(listArray(array).equals(listArray(this.player.getSea())));
//    }
//
//    public ArrayList<Integer> listArray(int[][] array) {
//        ArrayList<Integer> list = new ArrayList<>();
//
//        for (int i = 0; i < array.length; i++) {
//            for (int j = 0; j < array[0].length; j++) {
//                list.add(array[i][j]);
//            }
//        }
//
//        return list;
//    }
}
