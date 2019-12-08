package battleships.domain;

import battleships.ui.GraphicalUserInterface;
import battleships.ui.TextUserInterface;
import battleships.ui.UserInterface;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The functioning logic is implemented within this class.
 * <p><strong>This is a singleton class.</strong></p>
 */

public class Game {

    UserInterface userInterface;
    private ArrayList<Player> listOfPlayers;
    private static int gameBoardSize;
    /**
     * The mode of how the game will be played; 
     * 0 stands for alone, as a {@link HumanPlayer} against a {@link BotPlayer}, and 
     * 1 stands for together with another {@link HumanPlayer}.
     */
    public int gameMode;
    private static Game instance = null;
    private Random random = new Random();
    //private static boolean isHit = false;

    /**
     * Creates a new instance of Game.
     * <p>Creates an ArrayList of {@link Player}s called listOfPlayers<br>
     * sets the gameBoardSize as 10, and <br>
     * sets the {@link UserInterface} and its instance.</p>
     * @throws IllegalStateException If Game instance is not null when calling the class constructor
     */
    
    public Game() throws IllegalStateException {
        if (instance != null) {
            throw new IllegalStateException("Multiple singletons attempted with class Game!");
        }

        listOfPlayers = new ArrayList<Player>();
        this.gameBoardSize = 10;
        this.userInterface = GraphicalUserInterface.getInstance();
    }

    /**
     * The instance of the Game object is set here as new {@link #Game()} if current instance is null.
     * @return The instance of the Game object
     */
    
    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }
    
    /**
     * Sets current instance as null.
     */

    //THIS METHOD IS NOT USED?
    public void abandonInstance() {
        this.instance = null;
    }
    
    /**
     * @return Size of the game board as int
     */

    public static int getGameBoardSize() {
        return gameBoardSize;
    }
    
    /**
     * @return The ArrayList containing the Players
     */

    public ArrayList<Player> getListOfPlayers() {
        return this.listOfPlayers;
    }

    //DO YOU NEED THIS? IF NOT, REMOVE FROM ABOVE
//    public static boolean getIsHit() {
//        return isHit;
//    }
    
    /**
     * Calls (ADD LINK TO UI.welcome() HERE).
     */
    
    public void beginStartMethod() {
        System.out.println("About to call welcome");
        userInterface.welcome();
        System.out.println("Called welcome");
    }

    /**
     * Checks that the current {@link UserInterface} and the corresponding gameMode are set, 
     * calls for LINK ADDPLAYERS? and forwards the game by calling {@link #createBoard}.
     */
    
    //THE NUMBER OF SHIPS IS SET HERE AS 1 FOR NOW
    public void finishStartMethod() {
        refreshUserInterface();
        System.out.println("Got here!");
        this.gameMode = userInterface.getGamemode();                            //THIS WAS CHANGED FROM userInterface.getGameMode()
        System.out.println("Game mode is: " + this.gameMode);
        addPlayers();
        System.out.println("Now we have the players: ");
        listOfPlayers.forEach(player -> {
            System.out.println("\t" + player.name);

        });

        createBoard(1);             

    }

    private void refreshUserInterface() {
        this.userInterface = UserInterface.getInstance();
    }
    
    //NOT NEEDED FOR PRIVATE METHODS
//    /**
//     * Adds two {@link Player}s for the game. 
//     * <p>If gameMode is set as zero, the method adds first a {@link HumanPlayer} and then a {@link BotPlayer}.<br>
//     * If gameMode is set as something else(1), the method adds two {@link HumanPlayer}s.<br>
//     * In both cases the name to be given to create a {@link HumanPlayer} is asked from the {@link UserInterface}.</p>
//     */

    private void addPlayers() {
        if (this.gameMode == 0) {
            listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(1)));
            listOfPlayers.add(new BotPlayer());
        } else {
            listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(1)));
            listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(2)));
        }
    }

    /**
     * Creates boards for both {@link Player}s. 
     * <p>Uses a for-loop to iterate through listOfPlayers and calls ADD LINK TO PLAYER.SETSHIPS
     * and ADD LINK TO SETUPBOARD.</p>
     * <p>After the method is done with both {@link Player}s, calling for {@link #playGame()}.</p>
     * @param numberOfShips How many ships per {@link Player}
     */
    
    //FOR NOW THE AMOUNT OF SHIPS IS SET IN FINISHSTARTMETHOD
    public void createBoard(int numberOfShips) {
        for (Player player : this.listOfPlayers) {
            
            player.setShips(numberOfShips);

            // weird thread-problem when calling the below     
            setUpBoard(player);
        }
        
        playGame();
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void setUpBoard(Player player) {
        if (player.getClass() == HumanPlayer.class) {
            userInterface.printRulesForPlayerSetUp(player.getShips().size(), player.getName());
        }

        setShipsForPlayer(player);
    }

    private void setShipsForPlayer(Player player) {
        for (int i = 0; i < player.getShips().size(); i++) {
            int ship = player.getShips().get(i);
            System.out.println("The ship to be set is: " + ship);

            if (!askForShips(player, ship)) {
                if (player.getClass() == HumanPlayer.class) {
                    this.userInterface.directionNotAllowed("You must choose another placement!");
                }
                
                i--;
            }
        }
        
        System.out.println("All ships are set.");
    }

    private boolean askForShips(Player player, int ship) {
        //this.userInterface.printSea(player.getSea());
        PlacementInfo info = player.decideCoordinates(ship, true, this.gameBoardSize);

        if (areCoordinatesAllowed(info.getRow(), info.getColumn(), player, ship, info.getDirection(), "create")) {
            placeShips(info.getRow(), info.getColumn(), player, ship, info.getDirection());
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Handles the turn order and keeps the turns changing.
     * <p>Draws the {@link Player} for the first turn and sets isGameGoing as boolean value returned from ADD LINK TO TURN?.<br>
     * Handles the turn order with a while-loop with isGameGoing as its condition.<br>
     * The loop changes the {@link Player} in turn by always calling the other index from listOfPlayers, and calling ADD LINK TO TURN? with the given {@link Player}.
     * </p>
     */

    public void playGame() {
        int i = this.random.nextInt(2);
        boolean isGameGoing = turn(listOfPlayers.get(i));

        while (isGameGoing) {
            i = getIndexForAnotherPlayer(listOfPlayers.get(i));
            isGameGoing = turn(listOfPlayers.get(i));
        }

    }

    private boolean areCoordinatesAlreadyUsed(PlacementInfo info, Player otherPlayer) {
        int row = info.getRow();
        int column = info.getColumn();
        String mask = otherPlayer.getSea().getMaskedSea()[row][column];

        return (mask.equalsIgnoreCase(" O") || mask.equalsIgnoreCase(" X"));
    }

    //CLEAN THIS UP
    private boolean turn(Player player) {
        int i = getIndexForAnotherPlayer(player);
        userInterface.printMaskedSea(this.listOfPlayers.get(i), null, i);

        while (true) {                                                          //THE TURN GOES ON WHILE THIS IS TRUE
            PlacementInfo info = player.decideCoordinates(0, false, this.gameBoardSize);
            int row = info.getRow();
            int column = info.getColumn();

            if (areCoordinatesAlreadyUsed(info, this.listOfPlayers.get(i))) {
                if (player.getClass() == HumanPlayer.class) {
                    userInterface.printForNoNewCoordinates(row, column);
                }
                continue;
            }

            if (this.listOfPlayers.get(i).getSea().isAreaEmpty(row, column)) {
                this.listOfPlayers.get(i).getSea().modifyMaskedSea(row, column, 0);
                userInterface.printMaskedSea(this.listOfPlayers.get(i), "miss", i);
                //this.isHit = false;
                break;
            } else {
                this.listOfPlayers.get(i).getSea().modifyMaskedSea(row, column, 1);
                userInterface.printMaskedSea(this.listOfPlayers.get(i), "hit", i);
                //this.isHit = true;
                if (this.listOfPlayers.get(i).getSea().seaIsEmpty()) {
                    userInterface.gameOver(player.getName());
                    return false;
                }
            }
            userInterface.printMaskedSea(this.listOfPlayers.get(i), null, i);
        }
        return true;
    }

    private int getIndexForAnotherPlayer(Player playerNotWanted) {
        if (this.listOfPlayers.indexOf(playerNotWanted) == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    private void placeShips(int row, int column, Player player, int ship, String dir) {
        int r = row;
        int c = column;

        for (int i = ship; i > 0; i--) {
            player.addShipForPlayer(r, c, ship);

            if (dir.equals("w")) {
                r--;
            } else if (dir.equals("s")) {
                r++;
            } else if (dir.equals("a")) {
                c--;
            } else {
                c++;
            }
        }
    }

    private boolean areCoordinatesAllowed(int row, int column, Player player, int ship, String dir, String mode) {

        if (row < 0 || row > (this.gameBoardSize - 1)) {
            return false;
        } else if (column < 0 || column > (this.gameBoardSize - 1)) {
            return false;
        } else if (player.getSea().getSea()[row][column] != 0) {
            return false;
        } else if (mode.equals("create")) {
            return isPlacementAllowed(row, column, player.getSea().getSea(), ship, dir);
        } else {
            return true;
        }
    }

    private boolean surroundsAreEmpty(int row, int column, int[][] sea, int ship) {
        int r = row - 1;

        while (r <= (row + 1)) {
            int c = column - 1;

            while (c <= (column + 1)) {
                try {
                    if (sea[r][c] != 0 && sea[r][c] != ship) {
                        return false;
                    }
                    c++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    c++;
                }
            }
            r++;
        }
        return true;
    }

    private boolean eachSurroundingIsEmpty(int row, int column, int[][] sea, int ship, int rowChange, int colChange) {
        int r = row;
        int c = column;

        for (int i = ship; i > 0; i--) {
            if (surroundsAreEmpty(r, c, sea, ship)) {
                r = r + rowChange;
                c = c + colChange;
                continue;
            }
            return false;
        }
        return true;
    }

    private boolean isDirectionAllowed(int row, int column, int[][] sea, int ship, String dir) {
        if (dir.equals("w")) {
            return eachSurroundingIsEmpty(row, column, sea, ship, -1, 0);
        } else if (dir.equals("s")) {
            return eachSurroundingIsEmpty(row, column, sea, ship, 1, 0);
        } else if (dir.equals("a")) {
            return eachSurroundingIsEmpty(row, column, sea, ship, 0, -1);
        } else {
            return eachSurroundingIsEmpty(row, column, sea, ship, 0, +1);
        }
    }

    private boolean isPlacementAllowed(int row, int column, int[][] sea, int ship, String dir) {
        if (ship != 1) {
            if ((row == 0 && dir.equals("w"))
                    || (row == (sea.length - 1) && dir.equals("s"))
                    || (column == 0 && dir.equals("a"))
                    || (column == (sea.length - 1) && dir.equals("d"))) {
                return false;
            }
            int s = ship - 1;
            if (dir.equals("w") && (row - s < 0)
                    || (dir.equals("s") && (row + s > sea.length - 1))
                    || (dir.equals("a") && (column - s < 0))
                    || (dir.equals("d")) && (column + s > sea.length - 1)) {
                return false;
            }
        } else {                                                                //if ship size is one, only check the surroundings
            return surroundsAreEmpty(row, column, sea, ship);
        }
        return isDirectionAllowed(row, column, sea, ship, dir);
    }
}
