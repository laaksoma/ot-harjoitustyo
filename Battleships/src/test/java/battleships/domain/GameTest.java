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
    public void beginStartMethodCallsForWelcome() {
        game.beginStartMethod();
        verify(mockUser).welcome();
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

    @Test
    public void getIndexForAnotherPlayerReturnsOneWhenGivenPlayerAtZero() {
        game.gameMode = 0;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Annie");

        game.addPlayers();

        assertEquals(1, game.getIndexForAnotherPlayer(game.getListOfPlayers().get(0)));
    }

    @Test
    public void getIndexForAnotherPlayerReturnsZeroWhenGivenPlayerAtOne() {
        game.gameMode = 0;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Jack");

        game.addPlayers();

        assertEquals(0, game.getIndexForAnotherPlayer(game.getListOfPlayers().get(1)));
    }

    @Test
    public void placeShipsPlacesTheWholeShipWhenDirectionIsUp() {
        game.gameMode = 0;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Jenny");
        game.addPlayers();
        game.placeShips(8, 8, game.getListOfPlayers().get(0), 2, "w");
        int sum = game.getListOfPlayers().get(0).getSea().getSea()[8][8]
                + game.getListOfPlayers().get(0).getSea().getSea()[7][8];

        assertEquals(4, sum);
    }

    @Test
    public void placeShipsPlacesTheWholeShipWhenDirectionIsRight() {
        game.gameMode = 0;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Anne");
        game.addPlayers();
        game.placeShips(1, 1, game.getListOfPlayers().get(0), 3, "d");
        int sum = game.getListOfPlayers().get(0).getSea().getSea()[1][1]
                + game.getListOfPlayers().get(0).getSea().getSea()[1][2]
                + game.getListOfPlayers().get(0).getSea().getSea()[1][3];

        assertEquals(9, sum);
    }

    @Test
    public void placeShipsPlacesTheWholeShipWhenDirectionIsDown() {
        game.gameMode = 0;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Jane");
        game.addPlayers();
        game.placeShips(3, 4, game.getListOfPlayers().get(0), 2, "s");
        int sum = game.getListOfPlayers().get(0).getSea().getSea()[3][4]
                + game.getListOfPlayers().get(0).getSea().getSea()[4][4];

        assertEquals(4, sum);
    }

    @Test
    public void placeShipsPlacesTheWholeShipWhenDirectionIsLeft() {
        game.gameMode = 0;
        when(mockUser.getPlayerName(anyInt())).thenReturn("Jane");
        game.addPlayers();
        game.placeShips(1, 9, game.getListOfPlayers().get(0), 2, "a");
        int sum = game.getListOfPlayers().get(0).getSea().getSea()[1][9]
                + game.getListOfPlayers().get(0).getSea().getSea()[1][8];

        assertEquals(4, sum);
    }

    @Test
    public void areCoordinatesAllowedReturnsFalseWhenRowIsTooSmall() {
        assertFalse(game.areCoordinatesAllowed(-1, 2, new BotPlayer(), 2, "s", null));
    }

    @Test
    public void areCoordinatesAllowedReturnsFalseWhenRowIsTooBig() {
        assertFalse(game.areCoordinatesAllowed(15, 2, new BotPlayer(), 2, "s", null));
    }

    @Test
    public void areCoordinatesAllowedReturnsFalseWhenColumnIsTooSmall() {
        assertFalse(game.areCoordinatesAllowed(2, -1, new BotPlayer(), 2, "s", null));
    }

    @Test
    public void areCoordinatesAllowedReturnsFalseWhenColumnIsTooBig() {
        assertFalse(game.areCoordinatesAllowed(2, 150, new BotPlayer(), 2, "s", null));
    }

    @Test
    public void areCoordinatesAllowedReturnsFalseWhenPlacementIsAlreadyUsed() {
        BotPlayer testBot = new BotPlayer();
        testBot.getSea().addShipToTheSea(1, 1, 3);
        assertFalse(game.areCoordinatesAllowed(1, 1, testBot, 3, "s", null));
    }

    @Test
    public void areCoordinatesAllowedReturnsFalseWhenModeIsCreateAndPlacementIsNotAllowed() {
        assertFalse(game.areCoordinatesAllowed(1, 1, new BotPlayer(), 6, "w", "create"));
    }

    @Test
    public void areCoordinatesAllowedReturnsTrueWhenModeIsCreateAndPlacementIsAllowed() {
        assertTrue(game.areCoordinatesAllowed(1, 1, new BotPlayer(), 6, "s", "create"));
    }

    @Test
    public void areCoordinatesAllowedReturnsTrueWhenYes() {
        assertTrue(game.areCoordinatesAllowed(2, 2, new BotPlayer(), 2, "s", "no"));
    }

    @Test
    public void surroundsAreEmptyReturnsFalseWhenNo() {
        BotPlayer testBot = new BotPlayer();
        testBot.getSea().addShipToTheSea(1, 1, 1);

        assertFalse(game.surroundsAreEmpty(0, 0, testBot.getSea().array, 2));
    }

    @Test
    public void surroundsAreEmptyDoesNotCrashWhenShipIsOnTheEdge() {
        BotPlayer testBot = new BotPlayer();

        assertTrue(game.surroundsAreEmpty(0, 0, testBot.getSea().array, 1));
    }

    @Test
    public void eachSurroundingIsEmptyReturnsFalseWhenNo() {
        BotPlayer testBot = new BotPlayer();
        testBot.getSea().addShipToTheSea(3, 3, 2);

        assertFalse(game.eachSurroundingIsEmpty(3, 4, testBot.getSea().array, 3, 0, -1));
    }

    @Test
    public void isDirectionAllowedReturnsTrueWithUpWhenYes() {
        BotPlayer testBot = new BotPlayer();

        assertTrue(game.isDirectionAllowed(3, 3, testBot.getSea().array, 2, "w"));
    }

    @Test
    public void isDirectionAllowedReturnsFalseWithUpWhenNo() {
        BotPlayer testBot = new BotPlayer();
        testBot.getSea().addShipToTheSea(2, 1, 1);

        assertFalse(game.isDirectionAllowed(2, 2, testBot.getSea().array, 3, "w"));
    }

    @Test
    public void isDirectionAllowedReturnsTrueWithLeftWhenYes() {
        BotPlayer testBot = new BotPlayer();

        assertTrue(game.isDirectionAllowed(2, 4, testBot.getSea().array, 3, "a"));
    }

    @Test
    public void isDirectionAllowedReturnsFalseWithLeftWhenNo() {
        BotPlayer testBot = new BotPlayer();
        testBot.getSea().addShipToTheSea(2, 1, 1);

        assertFalse(game.isDirectionAllowed(2, 2, testBot.getSea().array, 3, "a"));
    }

    @Test
    public void isDirectionAllowedReturnsTrueWithDownWhenYes() {
        BotPlayer testBot = new BotPlayer();

        assertTrue(game.isDirectionAllowed(1, 1, testBot.getSea().array, 3, "s"));
    }

    @Test
    public void isDirectionAllowedReturnsFalseWithDownWhenNo() {
        BotPlayer testBot = new BotPlayer();
        testBot.getSea().addShipToTheSea(1, 1, 2);

        assertFalse(game.isDirectionAllowed(1, 2, testBot.getSea().array, 3, "s"));
    }

    @Test
    public void isDirectionAllowedReturnsTrueWithRightWhenYes() {
        BotPlayer testBot = new BotPlayer();

        assertTrue(game.isDirectionAllowed(2, 1, testBot.getSea().array, 3, "d"));
    }

    @Test
    public void isDirectionAllowedReturnsFalseWithRightWhenNo() {
        BotPlayer testBot = new BotPlayer();
        testBot.getSea().addShipToTheSea(1, 1, 2);

        assertFalse(game.isDirectionAllowed(1, 2, testBot.getSea().array, 3, "d"));
    }

    @Test
    public void isPlacementAllowedReturnsTrueWhenShipIsOneAndIsAllowed() {
        assertTrue(game.isPlacementAllowed(1, 1, new int[5][5], 1, "s"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenShipIsOneAndIsNotAllowed() {
        int[][] testSea = new int[5][5];
        testSea[1][1] = 2;

        assertFalse(game.isPlacementAllowed(1, 0, testSea, 1, "w"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenShipHasNoSpaceAndGoesUp() {
        assertFalse(game.isPlacementAllowed(0, 0, new int[5][5], 2, "w"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenShipHasNoSpaceAndGoesLeft() {
        assertFalse(game.isPlacementAllowed(2, 0, new int[5][5], 2, "a"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenShipHasNoSpaceAndGoesDown() {
        assertFalse(game.isPlacementAllowed(4, 4, new int[5][5], 2, "s"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenShipHasNoSpaceAndGoesRight() {
        assertFalse(game.isPlacementAllowed(3, 4, new int[5][5], 2, "d"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenRowIsZeroAndGoesUp() {
        assertFalse(game.isPlacementAllowed(0, 3, new int[5][5], 3, "w"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenColumnIsZeroAndGoesLeft() {
        assertFalse(game.isPlacementAllowed(3, 0, new int[5][5], 3, "a"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenRowIsMaxAndGoesDown() {
        assertFalse(game.isPlacementAllowed(4, 2, new int[5][5], 3, "s"));
    }

    @Test
    public void isPlacementAllowedReturnsFalseWhenColumnIsMaxAndGoesRight() {
        assertFalse(game.isPlacementAllowed(1, 4, new int[5][5], 3, "d"));
    }
}
