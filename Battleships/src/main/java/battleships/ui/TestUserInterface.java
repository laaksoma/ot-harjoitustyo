package battleships.ui;

import battleships.domain.Player;
import battleships.domain.Score;
import battleships.domain.Sea;
import java.util.ArrayList;

/**
 * A class made for mocking the {@link UserInterface}.
 * Only methods concerning the instance are needed, the others are mocked.
 */

public class TestUserInterface implements UserInterface {

    static boolean singletonHasBeenCreated = false;

    public static void setInstance(UserInterface ui) {
        instance = ui;
        singletonHasBeenCreated = true;
    }

    private static UserInterface instance;

    static UserInterface getInstance() {
        if (instance == null) {
            throw new IllegalStateException("For Test UI, you must set instance before getting it.");
        }
        return instance;
    }

    // The rest are not needed, since the instance is mocked

    public void welcome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printHighScores(ArrayList<Score> highScores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getGamemode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPlayerName(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printRulesForPlayerSetUp(int numberOfShips, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printRulesForPlayerTurn(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printForNoNewCoordinates(int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printForShipPlacement(int ship) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printSea(Sea sea) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printMaskedSea(Player player, String missOrHit, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void printPoints(Player playerInTurn, Player playerNotInTurn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void gameOver(Player player) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getRow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getColumn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDirection(int ship) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean directionNotAllowed(String direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
