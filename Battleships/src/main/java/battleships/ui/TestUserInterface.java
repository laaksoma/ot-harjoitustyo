package battleships.ui;

import battleships.domain.Player;
import battleships.domain.Sea;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A UserInterface Class created for testing the other classes.
 * <p><strong>This is a singleton class.</strong></p>
 */

public class TestUserInterface implements UserInterface {

    private static Scanner scanner;
    private static UserInterface instance;
    static boolean singletonHasBeenCreated = false;
    private ByteArrayOutputStream contentOutput = new ByteArrayOutputStream();
    private PrintStream originalOutput = System.out;
    private String input;
    private boolean exceptionCaught;
    private boolean inputNotAllowed;
    private String missOrHit;
    private ArrayList<String> listOfMasked = new ArrayList<>();
    private ArrayList<Integer> listOfClearSea = new ArrayList<>();
    private String playerName;
    private int gameMode;

    public TestUserInterface() throws IllegalStateException {
        if (instance != null) {
            throw new IllegalStateException("Multiple singletons attempted with class UserInterface!");
        }
        
//        this.input = "";
//        this.scanner = new Scanner(input);
    }
    
    public void setAnnotations() {
        this.exceptionCaught = false;
        this.inputNotAllowed = false;
        this.missOrHit = null;
        this.listOfMasked = new ArrayList<>();
        this.listOfClearSea = new ArrayList<>();
        this.playerName = "TestName";
        this.gameMode = 3;
    }
    
    public boolean getExceptionCaught() {
        return this.exceptionCaught;
    }
    
    public boolean getInputNotAllowed() {
        return this.inputNotAllowed;
    }
    
    public String getMissOrHit() {
        return this.missOrHit;
    }
    
    public ArrayList<String> getListOfMasked() {
        return this.listOfMasked;
    }
    
    public ArrayList<Integer> getListOfClearSea() {
        return this.listOfClearSea;
    }

    @Override
    public void setUpScanner(Scanner scanner) {
        this.scanner = scanner;
        //u.setUpScanner(new Scanner(input));
    }

    public static UserInterface getInstance() {
        if (instance == null) {
            instance = new TestUserInterface();
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
//        System.out.println("Welcome to the Battleships game!");
//        System.out.println("Would you like to play alone or with a friend?");
    }
    
    public void setGameMode(int mode) {
        this.gameMode = mode;
    }

    @Override
    public int getGamemode() {
        return this.gameMode;
    }

    @Override
    public String getPlayerName(int number) {
        return this.playerName;
    }

    @Override
    public void printRulesForPlayerSetUp(int numberOfShips, String name) {
//        System.out.println();
//        System.out.print("Creating board for " + name + "\n");
//        System.out.println("You are given " + numberOfShips + " ships. Place them in the sea by giving");
//        System.out.println("the starting coordinates and direction (WASD) of where you'd like to place them.");
//        System.out.println("The placement must follow these rules:");
//        System.out.println(" - all parts of the ship must be placed within the visible area,");
//        System.out.println(" - no ship is allowed to be stationed directly next to another ship, ");
//        System.out.println(" - and no ship is allowed to be stationed on top of another ship.\n");
    }

    @Override
    public void printRulesForPlayerTurn(String name) {
        this.exceptionCaught = true;
    }

    @Override
    public void printForNoNewCoordinates(int row, int column) {
//        System.out.println("You have already tried row " + (row + 1) + " and column " + (column + 1) + "!");
//        System.out.println("Try some other location.");
    }

    @Override
    public void printForShipPlacement(int ship) {
//        System.out.println("The length of the ship to be placed is " + ship + ".");
//        System.out.println("Where would you like to place it?");
    }

    @Override
    public void printSea(Sea sea) {
        for (int i = 0; i < sea.getSea().length; i++) {
            for (int j = 0; j < sea.getSea()[0].length; j++) {
                this.listOfClearSea.add(sea.getSea()[i][j]);
            }
        }
    }

    @Override
    public void printMaskedSea(Player player, String missOrHit, int index) {
        if (missOrHit != null) {
            this.missOrHit = missOrHit;
        }
        
        for (int i = 0; i < player.getSea().getMaskedSea().length; i++) {
            for (int j = 0; j < player.getSea().getMaskedSea()[0].length; j++) {
                this.listOfMasked.add(player.getSea().getMaskedSea()[i][j] + " ");
            }
        }
    }

    @Override
    public void gameOver(String name) {
        System.out.print("Congratulations " + name + ", you won!");
    }

    @Override
    public int getRow(int seaSize) {
        return getANumber(1, seaSize) - 1;
    }

    @Override
    public int getColumn(int seaSize) {
        return getANumber(1, seaSize) - 1;
    }

    public int getANumber(int min, int max) {
        int playerInput = -1;

        while (playerInput < min || playerInput > max) {
            try {
                playerInput = Integer.parseInt(scanner.nextLine());

                if (playerInput < min || playerInput > max) {
                    this.inputNotAllowed = true;
                }
            } catch (Exception e) {
                this.exceptionCaught = true;
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
                    direction = scanner.nextLine();
                } catch (Exception e) {
                    this.exceptionCaught = true;
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
        
        if (direction.equalsIgnoreCase("w")) {
            return false;
        } else if (direction.equalsIgnoreCase("a")) {
            return false;
        } else if (direction.equalsIgnoreCase("s")) {
            return false;
        } else if (direction.equalsIgnoreCase("d")) {
            return false;
        }
        return true;
    }
}
