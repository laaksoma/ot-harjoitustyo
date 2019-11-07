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
            row = UI.getANumber(1, player.getSea().length);
            System.out.print("Column: ");
            column = UI.getANumber(1, player.getSea().length);
            String dir = UI.getDirection();
            
            //make sure the coordinates are allowed!
            
            player.addShipToTheSea(row, column, ship);
        }
        //print the state of the sea
        //print ship is to be placed
        //ask for coordinates for the ship
        //ask for direction for the ship
        //The last thing to do is to check if the player is happy with the ship placement!
    }

    public void playGame() {

    }

}
