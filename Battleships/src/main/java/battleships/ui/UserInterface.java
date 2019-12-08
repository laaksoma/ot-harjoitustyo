package battleships.ui;

import battleships.domain.Player;
import battleships.domain.Sea;
import java.util.Scanner;

/**
 * Interface class to be implemented by different types of UI's. 
 * <p><strong>This is a singleton class.</strong></p>
 */

public interface UserInterface {

    void setUpScanner(Scanner scanner);

    /**
     * @return The type of the created UserInterface, if no UserInterface has been created upon calling, returns null
     */
    
    static UserInterface getInstance() {
        if(GraphicalUserInterface.singletonHasBeenCreated){
            return GraphicalUserInterface.getInstance();
        } else if(TextUserInterface.singletonHasBeenCreated){
            return TextUserInterface.getInstance();
        } else if (TestUserInterface.singletonHasBeenCreated) {
            return TestUserInterface.getInstance();
        } else {
            System.out.println("This should not be called ever");
            return null;
        }
    }

    void abandonInstance();

    void welcome();

    int getGamemode();

    String getPlayerName(int number);

    void printRulesForPlayerSetUp(int numberOfShips, String name);

    void printRulesForPlayerTurn(String name);

    void printForNoNewCoordinates(int row, int column);

    void printForShipPlacement(int ship);

    void printSea(Sea sea);

    void printMaskedSea(Player player, String missOrHit, int index);

    void gameOver(String name);

    int getRow(int seaSize);

    int getColumn(int seaSize);

    String getDirection(int ship);

    boolean directionNotAllowed(String direction);
}
