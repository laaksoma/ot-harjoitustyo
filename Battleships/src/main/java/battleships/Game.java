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
            player.setShips(2);                     //FOR NOW THE AMOUNT IS SET ON 2
            setUpBoard(player);
        }
    }

    public void setUpBoard(Player player) {                         //MOVE TO USERINTERFACE?    
        System.out.println("Creating board for " + player.getName() + "\n");
        UI.printRulesForPlayerSetUp(player.getShips().size());

        int row;
        int column;

        for (int i = 0; i < player.getShips().size(); i++) {
            int ship = player.getShips().get(i);
            player.printSea();

            System.out.println("The ship to be placed is " + ship);
            System.out.println("Where would you like to place it?");
            System.out.print("Row: ");                                  //player gives a number between 1 to 5
            row = UI.getANumber(1, player.getSeaSize()) - 1;            //to match the index, it is coded as number-1
            System.out.print("Column: ");
            column = UI.getANumber(1, player.getSeaSize()) - 1;
            String dir = UI.getDirection();
            
            if (areCoordinatesAllowed(row, column, player, ship)) {
                player.addShipToTheSea(row, column, ship);
            } else {
                i--;
                System.out.println("Another ship is too close!");
                continue;
            }   
        }
    }

    private boolean areCoordinatesAllowed(int row, int column, Player player, int ship) {

        if (row < 0 || row > (player.getSeaSize() - 1)) {                                 //1. out of bounds (one or both)
            return false;
        } else if (column < 0 || column > (player.getSeaSize() - 1)) {                    //2. there is already a ship in these coordinates
            return false;
        } else if (player.getSea()[row][column] != 0) {
            return false;
        } else if (surroundsAreEmpty(row, column, player.getSea(), ship) != true) {        //3. there is a neighbouring ship
            return false;
        }

        return true;
    }

    public boolean surroundsAreEmpty(int row, int column, int[][] sea, int shipNumber) {
        int r = row - 1;
        
        while (r <= (row + 1)) {
            int c = column - 1;
            
            while (c <= (column + 1)) {
                try {
                    if (sea[r][c] != 0 && sea[r][c] != shipNumber) {
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

    public void playGame() {

    }

}
