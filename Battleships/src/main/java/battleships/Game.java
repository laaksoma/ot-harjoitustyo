package battleships;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    UserInterface UI = new UserInterface(new Scanner(System.in));
    ArrayList<Player> players;
    
    public Game() {
        players = new ArrayList<Player>();
    }
    
    public void start() {
        UI.welcome();

        int gameMode = UI.getGamemode();
        
        if (gameMode == 0) { //alone
            System.out.println("Cannot play with an AI player at the given time.");
        } else { //together
            addPlayers("Player1");
            addPlayers("Player2");
        }
    }
    
    public void addPlayers(String playerName) {
        players.add(new Player(playerName));
    }
    
    public void createBoard() {
        System.out.println("Creating board for Player 1");
        UI.printRulesForPlayerSetUp();
        
    }
    
    public void playGame() {
        
    }
    
}
