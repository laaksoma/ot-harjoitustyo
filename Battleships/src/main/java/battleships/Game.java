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

    public void addPlayers(String playerName) {
        listOfPlayers.add(new Player(playerName));
    }

    public void createBoard() {

        for (Player player : this.listOfPlayers) {
            player.setShips(2);                     //FOR NOW THE AMOUNT IS SET ON 1
            setUpBoard(player);
        }
    }

    public void setUpBoard(Player player) {         //MOVE TO USERINTERFACE?    
        System.out.println("Creating board for " + player.getName() + "\n");
        UI.printRulesForPlayerSetUp(player.getShips().size());

        int row;
        int column;

        for (int ship : player.getShips()) {
            player.printSea();

            System.out.println("The ship to be placed is " + ship);
            System.out.println("Where would you like to place it?");
            System.out.print("Row: ");
            row = UI.getANumber(1, player.getSeaSize());
            System.out.print("Column: ");
            column = UI.getANumber(1, player.getSeaSize());
            String dir = UI.getDirection();

            //make sure the coordinates are allowed!
            player.addShipToTheSea(row, column, ship);
        }

        //print the state of the sea
        //print ship is to be placed
        //ask for coordinates for the ship
        //ask for direction for the ship
        //The last thing to do is to check if the player is happy with the ship placements!
    }

    private boolean areCoordinatesAllowed(int row, int column, Player player) {
        //why would they not be? 
        //1. out of bounds (one or both)
        //2. there is already a ship in these coordinates
        //3. there is a neighbouring ship

        if (row < 1 || row > player.getSeaSize()) {
            return false;
        } else if (column < 1 || column > player.getSeaSize()) {
            return false;
        } else if (player.getSea()[row][column] != 0) {
            return false;
        }

        return true;
    }

    public boolean surroundsAreEmpty(int r, int c, int[][] sea, int exception) {

        //what to do if on the edge?        
        //row above = sea[r-1][c-1], sea[r-1][c], sea[r-1][c+1]
        //row below = sea[row][]
        //column to the right
        //column to the left
        
        //add a method that checks whether surrounding area is empty
        //remember to add an exception number
        //i.e. when probing for ship 2, number 2 is allowed
        //remember to check whether the given coordinates are on the edge
        // if yes, then only check the areas on the sea
        //should the direction be checked here, too? 
        //    if the coordinates are on the edge and the direction is not allowed, ask the coordinates again
        //add a method that repeats the ship for the length of the ship, to the given direction
        return true;
    }

    public void playGame() {

    }

}
