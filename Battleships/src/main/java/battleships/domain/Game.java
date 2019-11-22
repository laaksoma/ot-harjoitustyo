package battleships.domain;

import battleships.ui.UserInterface;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    UserInterface userInterface = new UserInterface(new Scanner(System.in));
    ArrayList<Player> listOfPlayers;

    public Game() {
        listOfPlayers = new ArrayList<Player>();
    }

    public boolean start() {
        userInterface.welcome();

        int gameMode = userInterface.getGamemode();

        if (gameMode == 0) {            //alone
            System.out.println("Cannot play with an AI player at the given time.");
            return false;
        } else {                        //together
            addPlayer("Player1");
            addPlayer("Player2");

            return true;
        }
    }

    private void addPlayer(String playerName) {
        listOfPlayers.add(new Player(playerName));
    }

    public void createBoard() {                                 //FOR NOW THE AMOUNT OF SHIPS IS SET ON 1

        for (Player player : this.listOfPlayers) {
            player.setShips(3);
            setUpBoard(player);
        }
    }

    public void setUpBoard(Player player) {
        System.out.println("Creating board for " + player.getName() + "\n");
        userInterface.printRulesForPlayerSetUp(player.getShips().size());
        
        askForShips(player);
    }

    private void askForShips(Player player) {
        int row;
        int column;
        
        for (int i = 0; i < player.getShips().size(); i++) {
            int ship = player.getShips().get(i);
            player.printSea();

            userInterface.printForShipPlacement(ship);

            row = userInterface.getRow(player.getSeaSize());
            column = userInterface.getColumn(player.getSeaSize());

            String dir = userInterface.getDirection().toLowerCase().substring(0, 1);

            if (areCoordinatesAllowed(row, column, player, ship, dir, "create")) {
                placeShips(row, column, player, ship, dir);
            } else {
                i--;
                System.out.println("You must choose another placement!");
            }
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

    private boolean turn(Player player) {
        int i = getIndexForAnotherPlayer(player);
        this.listOfPlayers.get(i).printMaskedSea();

        while (true) {
            userInterface.printRulesForPlayerTurn(player.getName());
            int row = userInterface.getRow(player.getSeaSize());
            int column = userInterface.getColumn(player.getSeaSize());

            if (this.listOfPlayers.get(i).isAreaEmpty(row, column)) {
                this.listOfPlayers.get(i).modifyMaskedSea(row, column, 0);
                this.listOfPlayers.get(i).printMaskedSea();
                System.out.println("It's a miss!");
                break;
            } else {
                this.listOfPlayers.get(i).modifyMaskedSea(row, column, 1);
                System.out.println("It's a hit!");
                if (this.listOfPlayers.get(i).seaIsEmpty()) {
                    userInterface.gameOver(player.getName());
                    return false;
                }

            }

            this.listOfPlayers.get(i).printMaskedSea();

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
            if (dir.equals("w")) {
                player.addShipToTheSea(r, c, ship);
                r--;
            } else if (dir.equals("s")) {
                player.addShipToTheSea(r, c, ship);
                r++;
            } else if (dir.equals("a")) {
                player.addShipToTheSea(r, c, ship);
                c--;
            } else {
                player.addShipToTheSea(r, c, ship);
                c++;
            }
        }
    }

    private boolean areCoordinatesAllowed(int row, int column, Player player, int ship, String dir, String mode) {

        if (row < 0 || row > (player.getSeaSize() - 1)) {
            return false;
        } else if (column < 0 || column > (player.getSeaSize() - 1)) {
            return false;
        } else if (player.getSea()[row][column] != 0) {
            return false;
        } else if (mode.equals("create")) {
            return isPlacementAllowed(row, column, player.getSea(), ship, dir);
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
                    continue;
                }
            }

            r++;
        }

        return true;
    }

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

    private boolean isPlacementAllowed(int row, int column, int[][] sea, int ship, String dir) {

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
