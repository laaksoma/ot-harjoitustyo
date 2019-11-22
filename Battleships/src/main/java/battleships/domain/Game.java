package battleships.domain;

import battleships.ui.UserInterface;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    UserInterface userInterface = new UserInterface(new Scanner(System.in));
    ArrayList<Player> listOfPlayers;
    final int seaSize;
    int gameMode;
    private static Game instance = null;
    //private static boolean isHit = false;

    public Game() throws Exception {
        listOfPlayers = new ArrayList<Player>();
        this.seaSize = 5;

        if (instance != null) {
            throw new Exception("Multiple singletons attempted!");
        } else {
            this.instance = this;
        }
    }

    public static Game getInstance() {
        return instance;
    }

    //DO YOU NEED THIS? IF NOT, REMOVE FROM ABOVE
//    public static boolean getIsHit() {
//        return isHit;
//    }
    //MAKE VOID WHEN AI IS AN OPTION!
    public boolean start() {
        userInterface.welcome();

        this.gameMode = userInterface.getGamemode();

        return addPlayers();

    }

    //MAKE VOID WHEN AI IS AN OPTION
    private boolean addPlayers() {
        if (this.gameMode == 0) {
            //listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(1)));
            //listOfPlayers.add(new BotPlayer());
            System.out.println("Cannot play with an AI player at the given time.");
            return false;
        } else {
            listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(1)));
            listOfPlayers.add(new HumanPlayer(userInterface.getPlayerName(2)));
            return true;
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
        userInterface.printRulesForPlayerSetUp(player.getShips().size(), player.getName());

        setShipsForPlayer(player);
    }

    //CHECK PLAYER TYPE HERE; player.getClass() == HumanPlayer;
    private void setShipsForPlayer(Player player) {
        for (int i = 0; i < player.getShips().size(); i++) {
            int ship = player.getShips().get(i);

            if (player.getClass() == HumanPlayer.class) {
                if (!askForShips(player, ship)) {
                    i--;
                }
            }

        }
    }

    private boolean askForShips(Player player, int ship) {
        userInterface.printSea(player);

        userInterface.printForShipPlacement(ship);

        int row = userInterface.getRow(this.seaSize);
        int column = userInterface.getColumn(this.seaSize);
        String dir = getDirection(ship);

        if (areCoordinatesAllowed(row, column, player, ship, dir, "create")) {
            placeShips(row, column, player, ship, dir);
            return true;
        } else {
            System.out.println("You must choose another placement!");
            return false;
        }
    }

    private String getDirection(int ship) {
        if (ship != 1) {
            return userInterface.getDirection().toLowerCase().substring(0, 1);
        } else {
            return "w";
        }
    }

    public void playGame() {
        int i = ThreadLocalRandom.current().nextInt(0, 1);
        boolean isGameGoing = turn(listOfPlayers.get(i));

        while (isGameGoing) {
            i = getIndexForAnotherPlayer(listOfPlayers.get(i));
            isGameGoing = turn(listOfPlayers.get(i));
        }

    }

    //CLEAN THIS UP
    private boolean turn(Player player) {
        int i = getIndexForAnotherPlayer(player);
        userInterface.printMaskedSea(this.listOfPlayers.get(i));

        while (true) {
            userInterface.printRulesForPlayerTurn(player.getName());
            int row = userInterface.getRow(this.seaSize);
            int column = userInterface.getColumn(this.seaSize);

            if (this.listOfPlayers.get(i).getSea().isAreaEmpty(row, column)) {
                this.listOfPlayers.get(i).getSea().modifyMaskedSea(row, column, 0);
                userInterface.printMaskedSea(this.listOfPlayers.get(i));
                System.out.println("It's a miss!");
                //this.isHit = false;
                break;
            } else {
                this.listOfPlayers.get(i).getSea().modifyMaskedSea(row, column, 1);
                userInterface.printMaskedSea(this.listOfPlayers.get(i));
                //this.isHit = true;
                if (this.listOfPlayers.get(i).getSea().seaIsEmpty()) {
                    userInterface.gameOver(player.getName());
                    return false;
                }

                System.out.println("It's a hit!");

            }

            userInterface.printMaskedSea(this.listOfPlayers.get(i));

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

        if (row < 0 || row > (this.seaSize - 1)) {
            return false;
        } else if (column < 0 || column > (this.seaSize - 1)) {
            return false;
        } else if (player.getSea().getSea()[row][column] != 0) {
            return false;
        } else if (mode.equals("create")) {
            return isPlacementAllowed(row, column, player.getSea().getSea(), ship, dir);
        } else {
            return true;
        }
    }

    //CLEAN THIS UP
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
                    continue;
                }
            }

            r++;
        }

        return true;
    }

    //CLEAN THIS UP
    private boolean isDirectionAllowed(int row, int column, int[][] sea, int ship, String dir) {
        switch (dir) {
            case "w": {
                int r = row;
                for (int i = ship; i > 0; i--) {
                    if (surroundsAreEmpty(r, column, sea, ship)) {
                        r--;
                        continue;
                    }
                    return false;
                }
                break;
            }
            case "s": {
                int r = row;
                for (int i = ship; i > 0; i--) {
                    if (surroundsAreEmpty(r, column, sea, ship)) {
                        r++;
                        continue;
                    }
                    return false;
                }
                break;
            }
            case "a": {
                int c = column;
                for (int i = ship; i > 0; i--) {
                    if (surroundsAreEmpty(row, c, sea, ship)) {
                        c--;
                        continue;
                    }

                    return false;
                }
                break;
            }
            default: {
                int c = column;
                for (int i = ship; i > 0; i--) {
                    if (surroundsAreEmpty(row, c, sea, ship)) {
                        c++;
                        continue;
                    }
                    return false;
                }
                break;
            }
        }

        return true;
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
