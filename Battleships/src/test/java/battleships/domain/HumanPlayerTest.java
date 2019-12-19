package battleships.domain;

import battleships.ui.TestUserInterface;
import battleships.ui.UserInterface;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class HumanPlayerTest {

    private HumanPlayer humanTest;
    private static Game game;
    private UserInterface mockUser;
    private PlacementInfo testInfo;

    @BeforeClass
    public static void createUserInterfaceAndGameForTests() throws Exception {
        game = Game.getInstance();
    }

    @Before
    public void setUp() {
        game.abandonInstance();
        game = new Game();
        humanTest = new HumanPlayer("Mr. Test");
        mockUser = mock(UserInterface.class);
        TestUserInterface.setInstance(mockUser);
    }

    @Test
    public void decideCoordinatesExcecutesCorrectlyWhenShipIsSetAsZero() {
        when(mockUser.getRow()).thenReturn(1);
        when(mockUser.getColumn()).thenReturn(2);
        when(mockUser.getDirection(10)).thenReturn("w");

        humanTest.decideCoordinates(0, true, 10);
        verify(mockUser).printRulesForPlayerTurn("Mr. Test");
    }

    @Test
    public void decideCoordinatesExcecutesCorrectlyWhenShipIsNotZeroAndPrintsSea() {
        when(mockUser.getRow()).thenReturn(3);
        when(mockUser.getColumn()).thenReturn(1);
        when(mockUser.getDirection(10)).thenReturn("d");

        humanTest.decideCoordinates(1, true, 10);
        verify(mockUser).printSea(humanTest.getSea());
    }

    @Test
    public void decideCoordinatesCallsForPrintForShipPlacementWhenShipIsNotZero() {
        when(mockUser.getRow()).thenReturn(1);
        when(mockUser.getColumn()).thenReturn(1);
        when(mockUser.getDirection(10)).thenReturn("s");

        humanTest.decideCoordinates(1, true, 10);
        verify(mockUser).printForShipPlacement(1);
    }

    @Test
    public void decideCoordinatesSetsRowCorrectly() {
        when(mockUser.getRow()).thenReturn(4);
        when(mockUser.getColumn()).thenReturn(1);
        when(mockUser.getDirection(10)).thenReturn("s");

        testInfo = humanTest.decideCoordinates(0, true, 10);
        assertEquals(4, testInfo.getRow());
    }

    @Test
    public void decideCoordinatesSetsColumnCorrectly() {
        when(mockUser.getRow()).thenReturn(4);
        when(mockUser.getColumn()).thenReturn(1);
        when(mockUser.getDirection(10)).thenReturn("s");

        testInfo = humanTest.decideCoordinates(0, true, 10);
        assertEquals(1, testInfo.getColumn());
    }

    @Test
    public void decideCoordinatesSetsDirectionAsNullWhenNeedForDirectionIsFalse() {
        when(mockUser.getRow()).thenReturn(4);
        when(mockUser.getColumn()).thenReturn(1);
        when(mockUser.getDirection(10)).thenReturn("s");

        testInfo = humanTest.decideCoordinates(0, false, 10);
        assertEquals(null, testInfo.getDirection());
    }

    @Test
    public void decideCoordinatesSetsDirectionCorrectlyWhenNeedForDirectionIsTrue() {
        when(mockUser.getRow()).thenReturn(4);
        when(mockUser.getColumn()).thenReturn(1);
        when(mockUser.getDirection(0)).thenReturn("s");

        testInfo = humanTest.decideCoordinates(0, true, 10);
        assertEquals("s", testInfo.getDirection());
    }
}
