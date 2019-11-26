package battleships.domain;

import battleships.ui.UserInterface;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    UserInterface userInterface = new UserInterface(new Scanner(System.in));
    private ArrayList<Player> listOfPlayers;
    private static int gameBoardSize;
    public int gameMode;
    private static Game instance = null;
    //private static boolean isHit = false;

    public Game() throws Exception {
        listOfPlayers = new ArrayList<Player>();
        this.gameBoardSize = 5;

        if (instance != null) {
            throw new Exception("Multiple singletons attempted with class Game!");
        } else {
            this.instance = this;
        }
    }

    public static Game getInstance() {
        return instance;
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
    public void start() {
        userInterface.welcome();

        this.gameMode = userInterface.getGamemode();

        addPlayers();

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

    //FOR NOW THE AMOUNT OF SHIPS IS SET
    public void createBoard() {
        for (Player player : this.listOfPlayers) {
            player.setShips(2);
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

//    public String getDirection(int ship) {
//        if (ship != 1) {
//            return userInterface.getDirection().toLowerCase().substring(0, 1);
//        } else {
//            return "w";
//        }
//    }
    public void playGame() {
        System.out.println("The game is on!");

        int i = ThreadLocalRandom.current().nextInt(0, 1);
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

        while (true) { //THE TURN GOES ON WHILE THIS IS TRUE
            PlacementInfo info = player.decideCoordinates(0, false, 5);
            int row = info.getRow();
            int column = info.getColumn();

            if (areCoordinatesAlreadyUsed(info, this.listOfPlayers.get(i))) {
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

//CLEAN THIS UP
    public boolean isPlacementAllowed(int row, int column, int[][] sea, int ship, String dir) {

        if (ship != 1) {
            if (row == 0 && dir.equals("w")) {
                return false;
            } else if (row == (sea.length - 1) && dir.equals("s")) {
                return false;
            } else if (column == 0 && dir.equals("a")) {
                return false;
            } else if (column == (sea.length - 1) && dir.equals("d")) {
                return false;
            }

            int s = ship - 1;
            switch (dir) {
                case "w":
                    if (row - s < 0) {
                        return false;
                    }
                    break;
                case "s":
                    if (row + s > sea.length - 1) {
                        return false;
                    }
                    break;
                case "a":
                    if (column - s < 0) {
                        return false;
                    }
                    break;
                default:
                    if (column + s > sea.length - 1) {
                        return false;
                    }
                    break;
            }
        } else {
            return surroundsAreEmpty(row, column, sea, ship);
        }

        return isDirectionAllowed(row, column, sea, ship, dir);
    }
}
