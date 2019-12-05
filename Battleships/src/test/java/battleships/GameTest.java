package battleships;

import battleships.domain.BotPlayer;
import battleships.domain.Game;
import battleships.domain.HumanPlayer;
import battleships.domain.PlacementInfo;
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
        u = u.getInstance();
    }

    @Before
    public void setUp() {
        Game.getInstance().abandonInstance();
        System.setOut(new PrintStream(contentOutput));
        System.setOut(new PrintStream(contentOutput));
    }

    //SetUp
    public void setUpScannerForUserInterface(String input) {
        u.setUpScanner(new Scanner(input));
    }

    //SetUp
    public Object setAccessible(String methodName) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = Game.class.getDeclaredMethod(methodName);
        method.setAccessible(true);
        return method.invoke(Game.getInstance());
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

//    @Test
//    public void startGetsGameModeCorrectly() {
//        assertEquals(0, Game.getInstance().gameMode);
//    }
//    @Test(expected = IllegalStateException.class)
//    public void constructorAllowsOnlyOneGameSingleton() {
//        Game gameTry = new Game();
//        Game gameTryAgain = new Game();
//    }
//    
//    public void startCallsForAddPlayersCorrectly() {
//        //Game.getInstance().gameMode = 0;
//        setUpScannerForUserInterface("0\nAnnie\n");
//        
//        Game.getInstance().start();
//        
//        assertEquals("Annie", Game.getInstance().getListOfPlayers().get(0).getName());
//    }
    @Test
    public void addPlayersAddsHumanIfGameModeIsZero() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Game.getInstance().gameMode = 0;
        setUpScannerForUserInterface("Annie\n");

        setAccessible("addPlayers");

        assertEquals("Annie", Game.getInstance().getListOfPlayers().get(0).getName());
    }

    @Test
    public void addPlayersAddsBotIfGameModeIsZero() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Game.getInstance().gameMode = 0;
        setUpScannerForUserInterface("Annie\n");

        setAccessible("addPlayers");

        assertEquals("Bot /84", Game.getInstance().getListOfPlayers().get(1).getName());
    }

    @Test
    public void addPlayersAddsFirstHumanPlayerIfGameModeIsNotZero() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Game.getInstance().gameMode = 1;
        setUpScannerForUserInterface("Michael\nJenny\n");

        setAccessible("addPlayers");

        assertEquals("Michael", Game.getInstance().getListOfPlayers().get(0).getName());
    }

    @Test
    public void addPlayersAddsSecondHumanPlayerIfGameModeIsNotZero() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Game.getInstance().gameMode = 1;
        setUpScannerForUserInterface("Michael\nJenny\n");

        setAccessible("addPlayers");

        assertEquals("Jenny", Game.getInstance().getListOfPlayers().get(1).getName());
    }

//    @Test
//    public void areCoordinatesAlreadyUsedReturnsTrueWhenYes() {
//        //gets PlacementInfo for asked coords and otherPlayer so can check the mask
//        //modifyMaskedSea row column 0/miss 1/hit
//        PlacementInfo info = new PlacementInfo(1, 1, null);
//        HumanPlayer player = new HumanPlayer("Alfred");
//        player.getSea().modifyMaskedSea(1, 1, 0);
//
//        assertTrue(Game.getInstance().areCoordinatesAlreadyUsed(0, 0, player));
//
//    }

    @Test
    public void areCoordinatesAlreadyUsedReturnsFalseWhenNotUsed() {
        PlacementInfo info = new PlacementInfo(1, 1, null);
        HumanPlayer player = new HumanPlayer("Alfred");

        assertFalse(Game.getInstance().areCoordinatesAlreadyUsed(0, 0, player));
    }

    @Test
    public void getIndexForAnotherPlayerReturnsOtherPlayersIndex() {
        HumanPlayer p0 = new HumanPlayer("John");
        HumanPlayer p1 = new HumanPlayer("Emma");
        Game.getInstance().getListOfPlayers().add(p0);
        Game.getInstance().getListOfPlayers().add(p1);
        
        assertEquals(0, Game.getInstance().getIndexForAnotherPlayer(p1));
    }
    
    
}
