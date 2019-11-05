
package battleships;

import java.util.Scanner;


public class BattleshipsGame {
    
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        
        UserInterface gameInterface = new UserInterface(inputScanner);
        
        gameInterface.start();
    }
}
