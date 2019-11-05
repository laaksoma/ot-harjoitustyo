package battleships;


import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;

    public UserInterface(Scanner scanner) {
        this.scanner = scanner;
    }

    public static void welcome() {
        System.out.println("Welcome to the Battleships game!");
        System.out.println("Would you like to play alone or with a friend?");
    }

    public int getInput() {
        int playerInput = 3;

        while (playerInput != 1 && playerInput != 0) {
            System.out.println("Alone (0) or with a friend(1)?");
            
            try {
                playerInput = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("I didn't quite catch that, would you try again?");
            }
        }
        return playerInput;
    }

    public void start() {
        welcome();

        int gameMode = getInput();
    }

}
