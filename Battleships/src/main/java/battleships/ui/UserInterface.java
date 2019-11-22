package battleships.ui;

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
            playerInput = getANumber(0, 1);
        }
        return playerInput;
    }

    public void printRulesForPlayerSetUp(int numberOfShips) {
        System.out.println("You are given " + numberOfShips + " ships. Place them on the sea by giving");
        System.out.println("the starting coordinates and direction (WASD) of where you'd like to place them.");
        System.out.println("The placement must follow these rules:");
        System.out.println(" - all parts of the ship must be placed within the visible area,");
        System.out.println(" - no ship is allowed to be stationed directly next to another ship, ");
        System.out.println(" - and no ship is allowed to be stationed on top of another ship.\n");
    }

    public void printRulesForPlayerTurn(String name) {
        System.out.println();
        System.out.println("It's turn for " + name + "!");
        System.out.println("Where would you like to hit?");
    }

    public void printForShipPlacement(int ship) {
        System.out.println("The length of the ship to be placed is " + ship + ".");
        System.out.println("Where would you like to place it?");
    }
    
    public void gameOver(String name) {
        System.out.println("Congratulations " + name + ", you won!");
    }

    public int getRow(int seaSize) {
        System.out.print("Row: ");

        return getANumber(1, seaSize) - 1;
    }

    public int getColumn(int seaSize) {
        System.out.print("Column: ");
        
        return getANumber(1, seaSize) - 1;
    }

    public int getANumber(int min, int max) {
        int playerInput = -1;

        while (playerInput < min || playerInput > max) {
            try {
                playerInput = Integer.parseInt(scanner.nextLine());

                if (playerInput < min || playerInput > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("I didn't quite catch that, would you try again?");
            }
        }

        return playerInput;
    }

    public String getDirection() {
        String direction = "not allowed";

        while (directionNotAllowed(direction)) {
            try {
                System.out.print("Alignment: ");
                direction = scanner.nextLine() + "\n";
            } catch (Exception e) {
                System.out.println("Please enter either W, A, S or D.");
            }
        }

        return direction;
    }

    private boolean directionNotAllowed(String direction) {
        direction = direction.trim();

        if (direction.equals("w") || direction.equals("w")) {
            return false;
        } else if (direction.equals("A") || direction.equals("a")) {
            return false;
        } else if (direction.equals("S") || direction.equals("s")) {
            return false;
        } else if (direction.equals("D") || direction.equals("d")) {
            return false;
        }

        System.out.println("\nPlease enter either W, A, S or D.");

        return true;
    }
}
