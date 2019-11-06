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

    public int getGamemode() {
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

    public void printRulesForPlayerSetUp() {
        System.out.println("You are given one (1) ship. Place them on the sea by giving");
        System.out.println("the starting and ending coordinates of where you'd like to place your ships.");
        System.out.println("The given coordinates must follow these rules:");
        System.out.println("- all parts of the ship must be placed within the visible area");
        System.out.println("- no ship is allowed to be stationed directly next to another");
        System.out.println("- no ship is allowed to be stationed on top of another ship.");
    }
}
