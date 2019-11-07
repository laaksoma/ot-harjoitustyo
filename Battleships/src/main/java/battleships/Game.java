package battleships;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    UserInterface UI = new UserInterface(new Scanner(System.in));
    ArrayList<Player> players;

    public Game() {
        players = new ArrayList<Player>();
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
        players.add(new Player(playerName));
    }

    public void createBoard() {
        System.out.println("Creating board for Player 1");
        UI.printRulesForPlayerSetUp();
        
        

        //The last thing to do is to check if the player is happy with the ship placement!
    }

    public void playGame() {

    }

}
