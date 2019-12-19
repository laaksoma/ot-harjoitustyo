package battleships.ui;

import battleships.domain.Game;
import battleships.domain.Player;
import battleships.domain.Score;
import battleships.domain.Sea;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * {@link UserInterface} class for textUI.
 * <strong>This is a Singleton class.</strong>
 */
public class TextUserInterface implements UserInterface {

    private static Scanner scanner;
    private static UserInterface instance;
    static boolean singletonHasBeenCreated = false;

    /**
     * Constructor that sets the Scanner for the class.
     *
     * @throws IllegalStateException If class instance is not null when calling
     * the method.
     */
    public TextUserInterface() throws IllegalStateException {
        if (instance != null) {
            throw new IllegalStateException("Multiple singletons attempted with class UserInterface!");
        }

        this.scanner = new Scanner(System.in);
    }

    /**
     * If the class instance is null, then sets a new instance.
     *
     * @return Instance of the class
     */
    public static UserInterface getInstance() {
        if (instance == null) {
            instance = new TextUserInterface();
            singletonHasBeenCreated = true;
        }
        return instance;
    }

    /**
     * Prints the welcoming message and instructions.
     */
    @Override
    public void welcome() {
        System.out.println("Welcome to the Battleships game!");
        System.out.println("Would you like to play alone or with a friend?");

        Game.getInstance().finishStartMethod();
    }

    /**
     * Prints top 10 highest scores.
     * <p>
     * If the highScores list does not have all ten scores, then prints
     * "---".</p>
     *
     * @param highScores An ArrayList of Scores
     */
    @Override
    public void printHighScores(ArrayList<Score> highScores) {
        System.out.println("Name     Scores");
        int i = 1;

        for (Score score : highScores) {
            System.out.println(score.getName() + "  " + score.getPoints());
            i++;
        }

        if (i < 10) {
            while (i <= 10) {
                System.out.println(" ---    ---");
                i++;
            }
        }
    }

    /**
     * Prints instructions and asks {@link #getANumber(int, int)} for a number
     * between 0 and 1.
     *
     * @return The number given via Scanner through
     * {@link #getANumber(int, int)}
     */
    @Override
    public int getGamemode() {
        int playerInput = 3;

        while (playerInput != 1 && playerInput != 0) {
            System.out.println("Alone (0) or with a friend(1)?");
            playerInput = getANumber(0, 1);
        }
        return playerInput;
    }

    /**
     * Prints instructions and asks Scanner for the next line for player's name.
     * <p>
     * If player gives nothing, sets "player1/player2" as the name.</p>
     *
     * @param number Index for the player
     * @return String marking player name
     */
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

    /**
     * Prints rules for set up scene.
     *
     * @param numberOfShips How many ships are in the play
     * @param name Who is in turn
     */
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

    /**
     * Prints rules for the turn.
     *
     * @param name Who is in turn
     */
    @Override
    public void printRulesForPlayerTurn(String name) {
        System.out.println("The game is on!");
        System.out.println();
        System.out.println("It's turn for " + name + "!");
        System.out.println("Where would you like to hit?");
    }

    /**
     * Prints an error message if a pair of already used coordinates is tried.
     *
     * @param row Index of the tried row
     * @param column Index of the tried column
     */
    @Override
    public void printForNoNewCoordinates(int row, int column) {
        System.out.println("You have already tried row " + (row + 1) + " and column " + (column + 1) + "!");
        System.out.println("Try some other location.");
    }

    /**
     * Prints the questions for the given ship placement.
     *
     * @param ship The length of the ship
     */
    @Override
    public void printForShipPlacement(int ship) {
        System.out.println("The length of the ship to be placed is " + ship + ".");
        System.out.println("Where would you like to place it?");
    }

    /**
     * Prints the sea with indexing.
     *
     * @param sea The given sea
     */
    @Override
    public void printSea(Sea sea) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < sea.getSea().length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < sea.getSea()[0].length; j++) {
                System.out.print(sea.getSea()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints the masked sea with indexing.
     *
     * @param player Player who is in turn
     * @param missOrHit Was the previous try a miss or a hit
     * @param index Not needed with this class
     */
    @Override
    public void printMaskedSea(Player player, String missOrHit, int index) {
        if (missOrHit != null) {
            System.out.println("It's a " + missOrHit + "!");
        }

        System.out.println("  1  2  3  4  5 6 7 8 9 10");
        for (int i = 0; i < player.getSea().getMaskedSea().length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < player.getSea().getMaskedSea()[0].length; j++) {
                System.out.print(player.getSea().getMaskedSea()[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Prints how much points the player has.
     *
     * @param playerInTurn Player whose points are to be printed
     * @param playerNotInTurn Not needed with this class
     */
    @Override
    public void printPoints(Player playerInTurn, Player playerNotInTurn) {
        System.out.println("You have " + playerInTurn.getPointsAsInt() + "points.");
    }

    /**
     * Congratulates the winner and tells them their points.
     *
     * @param player The winning Player
     */
    @Override
    public void gameOver(Player player) {
        System.out.print("Congratulations " + player.getName() + ", you won!");
        System.out.println("You got " + player.getPointsAsInt() + " points!");
    }

    /**
     * @return A number to be used as row index
     */
    @Override
    public int getRow() {
        System.out.print("Row: ");

        return getANumber(1, Game.getGameBoardSize()) - 1;
    }

    /**
     * @return A number to be used as column index
     */
    @Override
    public int getColumn() {
        System.out.print("Column: ");

        return getANumber(1, Game.getGameBoardSize()) - 1;
    }

    /**
     * Gets a number between the given parameters.
     * <p>
     * If the playerInput is not a number within the given range, prints a
     * proper error message.</p>
     *
     * @param min The minimum
     * @param max The maximum
     * @return The given number within the range
     */
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

    /**
     * Asks the player for a direction.
     * <p>
     * In case the given String is not usable, prints an error message and asks
     * again. If the ship size is 1, just sets direction as up.</p>
     *
     * @param ship Size of the ship
     * @return Direction given by the player
     */
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

    /**
     * Checks is the given direction a proper one and asks for proper input if not.
     * @param direction The String given by the player
     * @return True if direction was not W, A, S or D (equalsIgnoreCase)
     */
    
    @Override
    public boolean directionNotAllowed(String direction) {
        direction = direction.trim();

        if (direction.equalsIgnoreCase("w")) {
            return false;
        } else if (direction.equalsIgnoreCase("a")) {
            return false;
        } else if (direction.equalsIgnoreCase("s")) {
            return false;
        } else if (direction.equalsIgnoreCase("d")) {
            return false;
        }

        System.out.println("\nPlease enter either W, A, S or D.");

        return true;
    }
}
