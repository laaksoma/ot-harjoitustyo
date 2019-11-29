package battleships.domain;

import battleships.ui.GraphicalUserInterface;
import battleships.ui.TextUserInterface;
import battleships.ui.UserInterface;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    UserInterface userInterface;
    private ArrayList<Player> listOfPlayers;
    private static int gameBoardSize;
    public int gameMode;
    private static Game instance = null;
    private Random random = new Random();
    //private static boolean isHit = false;

    public Game() throws IllegalStateException {
        if (instance != null) {
            throw new IllegalStateException("Multiple singletons attempted with class Game!");
        }

        listOfPlayers = new ArrayList<Player>();
        this.gameBoardSize = 5;
        this.userInterface = GraphicalUserInterface.getInstance();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void abandonInstance() {
        this.instance = null;
    }

    public static int getGameBoardSize() {
        return gameBoardSize;
    }

    public ArrayList<Player> getListOfPlayers() {
        return this.listOfPlayers;
    }

    //DO YOU NEED THIS? IF NOT, REMOVE FROM ABOVE
//    public static boolean getIsHit() {
//        return isHit;
//    }
    public void beginStartMethod() {
        System.out.println("About to call welcome");
        userInterface.welcome();
        System.out.println("Called welcome");
    }

    public void finishStartMethod() {
        refreshUserInterface();
        System.out.println("Got here!");
        this.gameMode = userInterface.getGamemode();                            //THIS WAS CHANGED FROM userInterface.getGameMode()
        System.out.println("Game mode is: " + this.gameMode);
        addPlayers();
    }
    
    private void refreshUserInterface() {
        this.userInterface = UserInterface.getInstance();
    }

    private void addPlayers() {
        if (this.gameMode == 0) {
            listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(1)));
            listOfPlayers.add(new BotPlayer());
        } else {
            listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(1)));
            listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(2)));
        }
    }

    //FOR NOW THE AMOUNT OF SHIPS IS SET IN MAIN WHEN CALLING THE METHOD
    public void createBoard(int numberOfShips) {
        for (Player player : this.listOfPlayers) {
            player.setShips(numberOfShips);
            setUpBoard(player);
        }
    }

    public void setUpBoard(Player player) {
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
                    System.out.println("You must choose another placement!");
                }

                i--;
            }

        }
    }

    private boolean askForShips(Player player, int ship) {
        PlacementInfo info = player.decideCoordinates(ship, true, 5);

        if (areCoordinatesAllowed(info.getRow(), info.getColumn(), player, ship, info.getDirection(), "create")) {
            placeShips(info.getRow(), info.getColumn(), player, ship, info.getDirection());
            return true;
        } else {
            return false;
        }
    }

    public void playGame() {
        System.out.println("The game is on!");

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
        userInterface.printMaskedSea(this.listOfPlayers.get(i), null);

        while (true) {                                                          //THE TURN GOES ON WHILE THIS IS TRUE
            PlacementInfo info = player.decideCoordinates(0, false, 5);
            int row = info.getRow();
            int column = info.getColumn();

            if (areCoordinatesAlreadyUsed(info, this.listOfPlayers.get(i))) {
                if (this.listOfPlayers.get(i).getName().equals("Bot")) {
                    userInterface.printForNoNewCoordinates(row, column);
                }
                continue;
            }

            if (this.listOfPlayers.get(i).getSea().isAreaEmpty(row, column)) {
                this.listOfPlayers.get(i).getSea().modifyMaskedSea(row, column, 0);
                userInterface.printMaskedSea(this.listOfPlayers.get(i), "miss");
                //this.isHit = false;
                break;
            } else {
                this.listOfPlayers.get(i).getSea().modifyMaskedSea(row, column, 1);
                userInterface.printMaskedSea(this.listOfPlayers.get(i), "hit");
                //this.isHit = true;
                if (this.listOfPlayers.get(i).getSea().seaIsEmpty()) {
                    userInterface.gameOver(player.getName());
                    return false;
                }
            }
            userInterface.printMaskedSea(this.listOfPlayers.get(i), null);
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
