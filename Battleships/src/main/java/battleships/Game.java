package battleships;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    UserInterface UI = new UserInterface(new Scanner(System.in));
    ArrayList<Player> listOfPlayers;

    public Game() {
        listOfPlayers = new ArrayList<Player>();
    }

    public boolean start() {
        UI.welcome();

        int gameMode = UI.getGamemode();

        if (gameMode == 0) {            //alone
            System.out.println("Cannot play with an AI player at the given time.");
            return false;
        } else {                        //together
            addPlayers("Player1");
            addPlayers("Player2");

            return true;
        }
    }

    private void addPlayers(String playerName) {
        listOfPlayers.add(new Player(playerName));
    }

    public void createBoard() {                                 //FOR NOW THE AMOUNT OF SHIPS IS SET ON 2

        for (Player player : this.listOfPlayers) {
            player.setShips(2);                     
            setUpBoard(player);
        }
    }

    public void setUpBoard(Player player) {                           
        System.out.println("Creating board for " + player.getName() + "\n");
        UI.printRulesForPlayerSetUp(player.getShips().size());

        int row;
        int column;

        for (int i = 0; i < player.getShips().size(); i++) {
            int ship = player.getShips().get(i);
            player.printSea();

            System.out.println("The ship to be placed is " + ship);
            System.out.println("Where would you like to place it?");
            System.out.print("Row: ");                                  
            row = UI.getANumber(1, player.getSeaSize()) - 1;            
            System.out.print("Column: ");
            column = UI.getANumber(1, player.getSeaSize()) - 1;
            String dir = UI.getDirection().toLowerCase().substring(0, 1);

            if (areCoordinatesAllowed(row, column, player, ship, dir)) {
                placeShips(row, column, player, ship, dir);
            } else {
                i--;
                System.out.println("You must choose another placement!");
                continue;
            }
        }
    }
    
    public void playGame() {
        //print the seas
            //player always sees their own sea
            //the other player's sea is coded as only zeros?
                //when a ship is hit, it is shown as -X
                    //when one's own "ship count" hits zero, they lose the game
            //ask for coordinates where to hit
            //update the map
                //if the player hit a ship, modify the value of the 
                //given cell (player.modifySea() returns the value)
                    //let them continue
                //if they didn't hit, show the cell and let the other player take the turn
    }

    private void placeShips(int row, int column, Player player, int ship, String dir) {
        int r = row;
        int c = column;
        
        for (int i = ship; i > 0; i--) {
            if (dir.equals("w")) { 
                player.addShipToTheSea(row, column, ship);
                r--;
            } else if (dir.equals("s")) { 
                player.addShipToTheSea(row, column, ship);
                r++;
            } else if (dir.equals("a")) { 
                player.addShipToTheSea(row, column, ship);
                c--;
            } else 
                player.addShipToTheSea(row, column, ship);
                c++;
        }
    }

    //add a game mode; for creating board 0, for playing 1?
    //the "isPlacementAllowed" is only checked when creating the board?
    private boolean areCoordinatesAllowed(int row, int column, Player player, int ship, String dir) {

        if (row < 0 || row > (player.getSeaSize() - 1)) {
            return false;
        } else if (column < 0 || column > (player.getSeaSize() - 1)) {
            return false;
        } else if (player.getSea()[row][column] != 0) {
            return false;
        } else {
            return isPlacementAllowed(row, column, player.getSea(), ship, dir);
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
