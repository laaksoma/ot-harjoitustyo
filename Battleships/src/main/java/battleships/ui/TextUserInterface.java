package battleships.ui;

import battleships.domain.Player;
import battleships.domain.Sea;
import java.util.Scanner;

public class TextUserInterface implements UserInterface {

    private static Scanner scanner;
    private static UserInterface instance;
    static boolean singletonHasBeenCreated = false;

    public TextUserInterface() throws IllegalStateException {
        if (instance != null) {
            throw new IllegalStateException("Multiple singletons attempted with class UserInterface!");
        }
        
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setUpScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public static UserInterface getInstance() {
        if (instance == null) {
            instance = new TextUserInterface();
            singletonHasBeenCreated = true;
        }
        return instance;
    }

    @Override
    public void abandonInstance() {
        this.instance = null;
    }

    @Override
    public void welcome() {
        System.out.println("Welcome to the Battleships game!");
        System.out.println("Would you like to play alone or with a friend?");
    }

    @Override
    public int getGamemode() {
        int playerInput = 3;

        while (playerInput != 1 && playerInput != 0) {
            System.out.println("Alone (0) or with a friend(1)?");
            playerInput = getANumber(0, 1);
        }
        return playerInput;
    }

    @Override
    public String getPlayerName(int number) {
        System.out.print("Please enter name for player" + number + ": ");

        String name;

        try {
            name = scanner.nextLine();
            System.out.println();
        } catch (Exception e) {
            System.out.println("I'm sorry, I couldn't catch your name. I shall call you player" + number);
            name = "Player" + number;
        }

        return name;
    }

    @Override
    public void printRulesForPlayerSetUp(int numberOfShips, String name) {
        System.out.println();
        System.out.print("Creating board for " + name + "\n");
        System.out.println("You are given " + numberOfShips + " ships. Place them in the sea by giving");
        System.out.println("the starting coordinates and direction (WASD) of where you'd like to place them.");
        System.out.println("The placement must follow these rules:");
        System.out.println(" - all parts of the ship must be placed within the visible area,");
        System.out.println(" - no ship is allowed to be stationed directly next to another ship, ");
        System.out.println(" - and no ship is allowed to be stationed on top of another ship.\n");
    }

    @Override
    public void printRulesForPlayerTurn(String name) {
        System.out.println();
        System.out.println("It's turn for " + name + "!");
        System.out.println("Where would you like to hit?");
    }

    @Override
    public void printForNoNewCoordinates(int row, int column) {
        System.out.println("You have already tried row " + (row + 1) + " and column " + (column + 1) + "!");
        System.out.println("Try some other location.");
    }

    @Override
    public void printForShipPlacement(int ship) {
        System.out.println("The length of the ship to be placed is " + ship + ".");
        System.out.println("Where would you like to place it?");
    }

    @Override
    public void printSea(Sea sea) {
        System.out.println("  1 2 3 4 5");
        for (int i = 0; i < sea.getSea().length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < sea.getSea()[0].length; j++) {
                System.out.print(sea.getSea()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void printMaskedSea(Player player, String missOrHit) {
        if (missOrHit != null) {
            System.out.println("It's a " + missOrHit + "!");
        }

        System.out.println("  1  2  3  4  5");
        for (int i = 0; i < player.getSea().getMaskedSea().length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < player.getSea().getMaskedSea()[0].length; j++) {
                System.out.print(player.getSea().getMaskedSea()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void gameOver(String name) {
        System.out.print("Congratulations " + name + ", you won!");
    }

    @Override
    public int getRow(int seaSize) {
        System.out.print("Row: ");

        return getANumber(1, seaSize) - 1;
    }

    @Override
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
                System.out.println("Please enter a number between " + min + " and " + max + "!");
            }
        }

        return playerInput;
    }

    @Override
    public String getDirection(int ship) {
        String direction = "not allowed";

        if (ship != 1) {
            while (directionNotAllowed(direction)) {
                try {
                    System.out.print("Alignment: ");
                    direction = scanner.nextLine();
                } catch (Exception e) {
                    System.out.println("Please enter either W, A, S or D.");
                }
            }
        } else {
            direction = "w";
        }

        return direction;
    }

    @Override
    public boolean directionNotAllowed(String direction) {
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
