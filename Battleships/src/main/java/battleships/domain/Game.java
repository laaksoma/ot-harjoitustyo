package battleships.domain;

import battleships.dao.ScoreDao;
import battleships.ui.UserInterface;
import java.util.ArrayList;
import java.util.Random;

/**
 * The functioning logic is implemented within this class.
 * <p>
 * <strong>This is a singleton class.</strong></p>
 */
public class Game {

    UserInterface userInterface;
    private ArrayList<Player> listOfPlayers;
    private static int gameBoardSize;
    /**
     * The mode of how the game will be played; 0 stands for alone, as a
     * {@link HumanPlayer} against a {@link BotPlayer}, and 1 stands for
     * together with another {@link HumanPlayer}.
     */
    public int gameMode;
    private static Game instance = null;
    private ScoreDao dao;
    private Random random = new Random();
    private boolean isHit = false;
    private float hitPointModifier = 1;

    /**
     * Creates a new instance of Game.
     * <p>
     * Creates an ArrayList of {@link Player}s called listOfPlayers<br>
     * sets the gameBoardSize as 10, and <br>
     * sets the {@link UserInterface} and its instance.</p>
     *
     * @throws IllegalStateException If Game instance is not null when calling
     * the class constructor
     */
    public Game() throws IllegalStateException {
        if (instance != null) {
            throw new IllegalStateException("Multiple singletons attempted with class Game!");
        }

        this.listOfPlayers = new ArrayList<Player>();
        this.gameBoardSize = 10;
        this.userInterface = UserInterface.getInstance();
    }

    /**
     * The instance of the Game object is set here as new {@link #Game()} if
     * current instance is null.
     *
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
     * <p>
     * This method is primarly used with tests.</p>
     */
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

    /**
     * Calls {@link UserInterface#welcome()}.
     */
    public void beginStartMethod() {
        this.dao = new ScoreDao("mongodb+srv://battleships_user:Lc8UDi0R1auovSIx@studies-db-zjjj3.gcp.mongodb.net/test?retryWrites=true&w=majority");
        userInterface.welcome();
    }

    /**
     * Checks that the current {@link UserInterface} and the corresponding
     * gameMode are set, calls for {@link #addPlayers()} and forwards the game
     * by calling {@link #createBoard}.
     */
    public void finishStartMethod() {
        refreshUserInterface();
        userInterface.printHighScores(dao.getHighScores());
        this.gameMode = userInterface.getGamemode();
        addPlayers();

        createBoard(5);

    }

    private void refreshUserInterface() {
        this.userInterface = UserInterface.getInstance();
    }

    /**
     * Adds two {@link Player}s for the game.
     * <p>
     * If gameMode is set as zero, the method adds first a {@link HumanPlayer}
     * and then a {@link BotPlayer}.<br>
     * If gameMode is set as something else(1), the method adds two
     * {@link HumanPlayer}s.<br>
     * In both cases the name to be given to create a {@link HumanPlayer} is
     * asked from the {@link UserInterface}.</p>
     */
    void addPlayers() {
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
     * <p>
     * Uses a for-loop to iterate through listOfPlayers and calls
     * {@link Player#setShips(int)} and {@link #setUpBoard(Player)}.</p>
     * <p>
     * After the method is done with both {@link Player}s, calling for
     * {@link #playGame()}.</p>
     *
     * @param numberOfShips How many ships per {@link Player}
     */
    public void createBoard(int numberOfShips) {
        for (Player player : this.listOfPlayers) {

            player.setShips(numberOfShips);

            setUpBoard(player);
        }

        playGame();
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

            if (!askForShips(player, ship)) {
                if (player.getClass() == HumanPlayer.class) {
                    this.userInterface.directionNotAllowed("You must choose another placement!");
                }

                i--;
            }
        }
    }

    private boolean askForShips(Player player, int ship) {
        PlacementInfo info = player.decideCoordinates(ship, true, this.gameBoardSize);
        int row = info.getRow();
        int column = info.getColumn();
        String dir = info.getDirection();

        if (areCoordinatesAllowed(row, column, player, ship, dir, "create")) {
            placeShips(row, column, player, ship, dir);
            return true;
        } else {
            return false;
        }
    }

    private int helper = 0;

    /**
     * Handles the turn order and keeps the turns changing.
     * <p>
     * Draws the {@link Player} for the first turn and sets isGameGoing as
     * boolean value returned from {@link #turn(Player)}.<br>
     * Handles the turn order with a while-loop with isGameGoing as its
     * condition.<br>
     * The loop changes the {@link Player} in turn by always calling the other
     * index from listOfPlayers, and calling {@link #turn(Player)} with the
     * given {@link Player}.
     * </p>
     */
    public void playGame() {
        int i = this.random.nextInt(2);
        userInterface.printMaskedSea(listOfPlayers.get(getIndexForAnotherPlayer(listOfPlayers.get(i))), null, i);
        boolean isGameGoing = turn(listOfPlayers.get(i));

        while (isGameGoing) {
            i = getIndexForAnotherPlayer(listOfPlayers.get(i));
            isGameGoing = turn(listOfPlayers.get(i));
        }

    }

    /**
     * Checks whether the given coordinates are already guessed before by
     * comparing the {@link Sea} and its mask they are linked to.
     *
     * @param info {@link PlacementInfo} containing the coordinates
     * @param otherPlayer The {
     * @Player} whose {@link Sea} the method compares to
     * @return True when already used, false when not
     */
    boolean areCoordinatesAlreadyUsed(PlacementInfo info, Player otherPlayer) {
        int row = info.getRow();
        int column = info.getColumn();
        String mask = otherPlayer.getSea().getMaskedSea()[row][column];

        return (mask.equalsIgnoreCase(" O") || mask.equalsIgnoreCase(" X"));
    }

    private boolean turn(Player player) {
        int i = getIndexForAnotherPlayer(player);
        Player notInTurn = this.listOfPlayers.get(i);
        userInterface.printMaskedSea(notInTurn, null, i);

        while (true) {
            PlacementInfo info = player.decideCoordinates(0, false, this.gameBoardSize);
            int row = info.getRow();
            int column = info.getColumn();
            int shipValue = 0;
            
            turnPoints(player, notInTurn, i);

            if (areCoordinatesAlreadyUsed(info, notInTurn)) {
                if (player.getClass() == HumanPlayer.class) {
                    userInterface.printForNoNewCoordinates(row, column);
                }
                continue;
            }

            shipValue = notInTurn.getSea().getSea()[row][column];

            if (notInTurn.getSea().isAreaEmpty(row, column)) {
                notInTurn.getSea().modifyMaskedSea(row, column, 0);
                userInterface.printMaskedSea(notInTurn, "miss", i);
                this.isHit = false;
                break;
            } else {
                notInTurn.getSea().modifyMaskedSea(row, column, 1);
                userInterface.printMaskedSea(notInTurn, "hit", i);
                this.isHit = true;
            }

            updatePlayerPoints(shipValue, player);

            if (notInTurn.getSea().seaIsEmpty()) {
                gameOver(player, notInTurn);
                return false;
            }

            userInterface.printMaskedSea(notInTurn, null, i);
        }
        return true;
    }
    
    private void turnPoints(Player player, Player notInTurn, int indexOfNotInTurn) {
        if (indexOfNotInTurn == 0) {
            userInterface.printPoints(notInTurn, player); 
        } else {
            userInterface.printPoints(player, notInTurn); 
        }
        
    }

    /**
     * Handles the situation when game is over. Calls for
     * {@link Player#setFinalPoints(int)} and adds a winner to the database via
     * {@link ScoreDao}, and calls for
     * {@link UserInterface#gameOver(battleships.domain.Player)}.
     *
     * @param inTurn Player who won
     * @param notInTurn Player who lost
     */
    void gameOver(Player inTurn, Player notInTurn) {
        inTurn.setFinalPoints(notInTurn.getSea().getOpenedArea());

        if (inTurn.getClass() != BotPlayer.class) {
            this.dao.addWinner(new Score(inTurn.getName(), inTurn.getPointsAsInt()));
        }

        this.listOfPlayers.clear();
        userInterface.gameOver(inTurn);
    }

    private void updatePlayerPoints(int ship, Player player) {
        if (this.isHit) {
            this.hitPointModifier = (float) (this.hitPointModifier * 1.2);
        } else {
            this.hitPointModifier = 1;
        }

        player.updatePoints(this.hitPointModifier * ship);
    }

    /**
     * @param playerNotWanted The player whose index we do not want
     * @return The other player's index at the listOfPlayers, 1 or 0
     */
    int getIndexForAnotherPlayer(Player playerNotWanted) {
        if (this.listOfPlayers.indexOf(playerNotWanted) == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @param row Index for row
     * @param column Index for column
     * @param player Player whose ship the method will set
     * @param ship The ship and its lenght
     * @param dir Direction for the ship
     */
    void placeShips(int row, int column, Player player, int ship, String dir) {
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

    /**
     * @param row Index for row
     * @param column Index for column
     * @param player Player on turn
     * @param ship Ship to be set
     * @param dir Direction for the ship
     * @param mode Mode of whether game is going through set-up phase or already
     * playing
     * @return False if no, true if yes
     */
    boolean areCoordinatesAllowed(int row, int column, Player player, int ship, String dir, String mode) {

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

    /**
     * Takes the coordinates and goes through the surrounding coordinates.
     *
     * @param row Index of the row
     * @param column Index of the column
     * @param sea {@link Sea} which is under inspection
     * @param ship Value of the ship; this value is allowed in the surrounding
     * areas
     * @return True if no ship is next to the given coordinates, false if
     * another ship with a different value is stationed next to the coordinates
     */
    boolean surroundsAreEmpty(int row, int column, int[][] sea, int ship) {
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

    /**
     * Checks that each surrounding is empty for the whole length of the ship by
     * calling {@link #surroundsAreEmpty(int, int, int[][], int)} for each.
     *
     * @param row Index of the row
     * @param column Index of column
     * @param sea {@link Sea} under inspection
     * @param ship Value of the ship that is allowed
     * @param rowChange How much the row value is changed depending on the
     * direction given to the ship
     * @param colChange How much the column value is changed depending on the
     * direction given to the ship
     * @return True if no other ship value was found, false if was found
     */
    boolean eachSurroundingIsEmpty(int row, int column, int[][] sea, int ship, int rowChange, int colChange) {
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

    /**
     * Checks is the direction allowed by checking
     * {@link #eachSurroundingIsEmpty(int, int, int[][], int, int, int)} for the
     * ship.
     *
     * @param row Index of the row
     * @param column Index of the column
     * @param sea {@link Sea} under inspection
     * @param ship Value and length of the ship
     * @param dir Direction given to the ship
     * @return True if no ship interrupts the ship in the given direction, false
     * if a ship was found
     */
    boolean isDirectionAllowed(int row, int column, int[][] sea, int ship, String dir) {
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

    /**
     * Checks if the given coordinate placement is allowed.
     * <p>
     * By comparing the direction of the ship and its length, the method checks
     * is there enough space for the ship with the given length.</p>
     *
     * @param row Index of the row
     * @param column Index of the column
     * @param sea {@link Sea} under inspection
     * @param ship Value and length of the ship
     * @param dir Given direction for the ship
     * @return True if the ship can be placed in the given manner at the given
     * coordinates, false if not
     */
    boolean isPlacementAllowed(int row, int column, int[][] sea, int ship, String dir) {
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
