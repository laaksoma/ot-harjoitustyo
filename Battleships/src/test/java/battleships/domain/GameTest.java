package battleships.domain;

import battleships.ui.TestUserInterface;
import static org.mockito.Mockito.*;
import battleships.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class GameTest {

    private static Game game;
    private UserInterface mockUser;

    @BeforeClass
    public static void createUserInterfaceAndGameForTests() throws Exception {
        GameTest.game = Game.getInstance();
    }

    @Before
    public void setUp() {
        game.abandonInstance();
        GameTest.game = new Game();
        mockUser = mock(UserInterface.class);
        game.userInterface = mockUser;
        TestUserInterface.setInstance(mockUser);
    }

    @Test
    public void constructorCreatesEmptyListOfPlayers() {
        assertEquals(true, game.getListOfPlayers().isEmpty());
    }

    @Test
    public void constructorSetsGameBoardSizeCorrectly() {
        assertEquals(10, game.getGameBoardSize());
    }

    @Test
    public void getInstanceReturnsInstanceCorrectly() {
        assertEquals(GameTest.game.getInstance(), Game.getInstance());
    }

    @Test
    public void getGameBoardSizeReturnsCorrectly() {
        assertEquals(10, game.getGameBoardSize());
    }

    @Test
    public void startGetsGameModeCorrectly() {
        assertEquals(0, game.gameMode);
    }

    @Test(expected = IllegalStateException.class)
    public void constructorAllowsOnlyOneGameSingleton() {
        Game.getInstance();
        Game gameTry = new Game();
        Game gameTryAgain = new Game();
    }

    @Test
    public void addPlayersAddsHumanIfGameModeIsZero() {
        game.gameMode = 0;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Annie");

        game.addPlayers();

        assertEquals("Annie", game.getListOfPlayers().get(0).getName());
    }

    @Test
    public void addPlayersAddsBotIfGameModeIsZero() {
        game.gameMode = 0;

        game.addPlayers();

        assertEquals("Bot /84", game.getListOfPlayers().get(1).getName());
    }

    @Test
    public void addPlayersAddsFirstHumanPlayerIfGameModeIsNotZero() {
        game.gameMode = 1;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Michael");

        game.addPlayers();

        assertEquals("Michael", game.getListOfPlayers().get(0).getName());
    }

    @Test
    public void addPlayersAddsSecondHumanPlayerIfGameModeIsNotZero() {
        game.gameMode = 1;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Jenny");

        game.addPlayers();

        assertEquals("Jenny", game.getListOfPlayers().get(1).getName());
    }

    @Test
    public void areCoordinatesAlreadyUsedReturnsTrueWhenYesWhenSeaHasBeenMissed() {
        HumanPlayer testPlayer = new HumanPlayer("testPlayer");
        PlacementInfo testInfo = new PlacementInfo(1, 1, null);
        testPlayer.getSea().modifyMaskedSea(1, 1, 0);

        assertTrue(game.areCoordinatesAlreadyUsed(testInfo, testPlayer));
    }

    @Test
    public void areCoordinatesAlreadyUsedReturnsTrueWhenYesWhenSeaHasBeenHit() {
        HumanPlayer testPlayer = new HumanPlayer("testPlayer");
        PlacementInfo testInfo = new PlacementInfo(1, 1, null);
        testPlayer.getSea().modifyMaskedSea(1, 1, 1);

        assertTrue(game.areCoordinatesAlreadyUsed(testInfo, testPlayer));
    }

    @Test
    public void areCoordinatesAlreadyUsedReturnsFalseWhenNo() {
        HumanPlayer testPlayer = new HumanPlayer("testPlayer");
        PlacementInfo testInfo = new PlacementInfo(1, 1, null);

        assertFalse(game.areCoordinatesAlreadyUsed(testInfo, testPlayer));
    }
}
