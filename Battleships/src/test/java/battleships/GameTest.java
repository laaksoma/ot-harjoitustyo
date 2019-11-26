package battleships;

import battleships.domain.BotPlayer;
import battleships.domain.Game;
import battleships.domain.HumanPlayer;
import battleships.ui.UserInterface;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class GameTest {

    private static UserInterface u;
    private ByteArrayOutputStream contentOutput = new ByteArrayOutputStream();
    private PrintStream originalOutput = System.out;
    private static Game game;

    @BeforeClass
    public static void createUserInterfaceAndGameForTests() throws Exception {
        u = u.getInstance();
        GameTest.game = new Game();
    }

    @Before
    public void setUp() {
        System.setOut(new PrintStream(contentOutput));
        System.setOut(new PrintStream(contentOutput));
    }

    //SetUp
    public void setUpScannerForUserInterface(String input) {
        u.setUpScanner(new Scanner(input));
    }

    @After
    public void restoreDefaultStreams() {
        System.out.flush();
        System.setOut(originalOutput);
    }

    @Test
    public void constructorCreatesEmptyListOfPlayers() {
        assertTrue(this.game.getListOfPlayers().isEmpty());
    }

    @Test
    public void constructorSetsGameBoardSizeCorrectly() {
        assertEquals(5, this.game.getGameBoardSize());
    }

    @Test
    public void getInstanceReturnsInstanceCorrectly() {
        assertEquals(GameTest.game.getInstance(), game.getInstance());
    }

    @Test
    public void startGetsGameModeCorrectly() {
        assertEquals(0, GameTest.game.getInstance().gameMode);
    }

//    @Test
//    public void addPlayersAddsHumanIfGameModeIsZero() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
//        GameTest.game.getInstance().gameMode = 0;
//        setUpScannerForUserInterface("Annie\n");
//        
//        Method method = Game.class.getDeclaredMethod("addPlayers");
//        method.setAccessible(true);
//        method.invoke(this.game);
//        
//        assertEquals("Annie", this.game.getListOfPlayers().get(0).getName());
//    }
//
//    @Test
//    public void addPlayersAddsBotIfGameModeIsZero() {
//
//    }
//
//    @Test
//    public void addPlayersAddsTwoHumanPlayersIfGameModeIsNotZero() {
//
//    }
    
    @Test
    public void areCoordinatesAlreadyUsedReturnsFalseWhenYes() {
        
    }

}
