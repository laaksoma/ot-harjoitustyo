
package battleships;

//import java.util.Scanner;

public class BattleshipsMain {
    
    public static void main(String[] args) {
        //Scanner inputScanner = new Scanner(System.in);
        
        Game battleships = new Game();
        
        battleships.start();
        
        battleships.createBoard();
    }
}
