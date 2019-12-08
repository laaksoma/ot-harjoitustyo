package battleships.domain;

import java.util.ArrayList;
import java.util.Random;

/**
 * Extension of {@link Player} class made for non-human {@link Player} when playing Battleships alone.
 */
public class BotPlayer extends Player {

    final ArrayList<String> listOfDirections;
    final int listSize;

    /**
     * Copy of the listOfDirections in case for a smarter bot is implemented.
     */
    
    ArrayList<String> listCopy;
    Random random;

    /**
     * Creates a new instance of {@link BotPlayer}.
     * <p>
     * Calls ADD LINK TO PLAYER CONSTRUCTOR? with default name "Bot /84", 
     * sets the listOfDirections, listSize as size of the previous ArrayList, and 
     * creates a new Random for annotation random.</p>
     */
    
    public BotPlayer() {
        super("Bot /84");
        this.listOfDirections = createDirections();
        this.listSize = listOfDirections.size();
        this.listCopy = listOfDirections;
        this.random = new Random();
    }

    private ArrayList<String> createDirections() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("w");
        list.add("a");
        list.add("s");
        list.add("d");

        return list;
    }

    /**
     * @return Direction as String using random int as ArrayList index for listCopy
     */
    
    public String getDirection() {
        String direction;

        int i = this.random.nextInt(this.listSize);
        direction = listCopy.get(i);                                            //copy for the better bot, delete if not in use later

        return direction;
    }

    /**
     * @param max The maximum for the wanted int, usually the size of the gameboard
     * @return A random int between 0 and max
     */
    
    public int getRowOrColumn(int max) {
        int number = this.random.nextInt(max);
        return number;
    }

    /**
     * Overrides method from {@link Player}.
     * <p>Asks for row and column from {@link #getRowOrColumn(int)}.<br>
     * If needForDirection is true, calls for {@link #getDirection()}, if false, sets dir as null.</p>
     * @param ship The ship that the coordinates are fetched for
     * @param needForDirection True when setting up the game, false when playing the game
     * @param gameSize Size of the game board
     * @return new {@link PlacementInfo} with row, column and dir as parameters
     */
    
    @Override
    public PlacementInfo decideCoordinates(int ship, boolean needForDirection, int gameSize) {
        int row = getRowOrColumn(gameSize);
        int column = getRowOrColumn(gameSize);
        String dir = null;

        if (needForDirection) {
            dir = getDirection();
        }
        System.out.println("Bot chose row " + (row + 1) + ", column " + (column + 1) + ", and direction was " + dir);

        return new PlacementInfo(row, column, dir);
    }

}
