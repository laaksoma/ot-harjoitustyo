package battleships.domain;

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
        GameTest.game = Game.getInstance();
        u = UserInterface.getInstance();
    }

    @Before
    public void setUp() {
        Game.getInstance().abandonInstance();
        GameTest.game = new Game();
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
        assertEquals(true, Game.getInstance().getListOfPlayers().isEmpty());
    }

    @Test
    public void constructorSetsGameBoardSizeCorrectly() {
        assertEquals(5, this.game.getGameBoardSize());
    }

    @Test
    public void getInstanceReturnsInstanceCorrectly() {
        assertEquals(GameTest.game.getInstance(), Game.getInstance());
    }

    @Test
    public void startGetsGameModeCorrectly() {
        assertEquals(0, Game.getInstance().gameMode);
    }

//    @Test(expected = IllegalStateException.class)
//    public void constructorAllowsOnlyOneGameSingleton() {
//        Game gameTry = new Game();
//        Game gameTryAgain = new Game();
//    }
//    
//    public void startCallsForAddPlayersCorrectly() {
//        Game.getInstance().gameMode = 0;
//        setUpScannerForUserInterface("Annie\n");
//        
//        Game.getInstance().start();
//        
//        assertEquals("Annie", Game.getInstance().getListOfPlayers().get(0).getName());
//    }

    @Test
    public void addPlayersAddsHumanIfGameModeIsZero() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Game.getInstance().gameMode = 0;
        setUpScannerForUserInterface("Annie\n");

        Method method = Game.class.getDeclaredMethod("addPlayers");
        method.setAccessible(true);
        method.invoke(Game.getInstance());

        assertEquals("Annie", Game.getInstance().getListOfPlayers().get(0).getName());
    }

    @Test
    public void addPlayersAddsBotIfGameModeIsZero() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Game.getInstance().gameMode = 0;
        setUpScannerForUserInterface("Annie\n");

        Method method = Game.class.getDeclaredMethod("addPlayers");
        method.setAccessible(true);
        method.invoke(Game.getInstance());

        assertEquals("Bot /84", Game.getInstance().getListOfPlayers().get(1).getName());
    }

    @Test
    public void addPlayersAddsFirstHumanPlayerIfGameModeIsNotZero() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Game.getInstance().gameMode = 1;
        setUpScannerForUserInterface("Michael\nJenny\n");

        Method method = Game.class.getDeclaredMethod("addPlayers");
        method.setAccessible(true);
        method.invoke(Game.getInstance());

        assertEquals("Michael", Game.getInstance().getListOfPlayers().get(0).getName());
    }

    @Test
    public void addPlayersAddsSecondHumanPlayerIfGameModeIsNotZero() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Game.getInstance().gameMode = 1;
        setUpScannerForUserInterface("Michael\nJenny\n");

        Method method = Game.class.getDeclaredMethod("addPlayers");
        method.setAccessible(true);
        method.invoke(Game.getInstance());

        assertEquals("Jenny", Game.getInstance().getListOfPlayers().get(1).getName());
    }
//    
//    @Test
//    public void areCoordinatesAlreadyUsedReturnsFalseWhenYes() {
//        
//    }
}
