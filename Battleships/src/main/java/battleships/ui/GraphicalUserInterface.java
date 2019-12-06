package battleships.ui;

import battleships.domain.Game;
import battleships.domain.Player;
import battleships.domain.Sea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraphicalUserInterface extends Application implements UserInterface {

    static boolean singletonHasBeenCreated = false;
    private Stage stage;
    public FXMLLoader startLoader;
    public FXMLLoader setUpLoader;
    public FXMLStartController startController;
    public FXMLSetUpController setUpController;
    private Scene gameStartScene;
    private Scene gameSetUpScene;
    private static UserInterface instance = null;
    private Parent startRoot;
    private Parent setUpRoot;

    @Override
    public void init() throws Exception {
        System.out.println("Starting! (in start method)");
        System.out.println("Instance is null?" + (instance == null));

        if (instance != null && (instance != this)) {
            this.startLoader = ((GraphicalUserInterface) instance).startLoader;
            this.startController = ((GraphicalUserInterface) instance).startController;
            instance = this;
            //System.out.println("Changed GUI singleton instance");
        }

        try {
            this.startLoader = new FXMLLoader(GraphicalUserInterface.class.getResource("/fxml/InstructionsFXML.fxml"));
            this.startRoot = startLoader.load();
            this.startController = this.startLoader.getController();
            System.out.println("Controller created for start.");
            this.gameStartScene = new Scene(startRoot, 300, 250);
        } catch (IOException e) {
            System.out.println("Could not find the file for starting.");
            e.printStackTrace();
        }

        try {
            this.setUpLoader = new FXMLLoader(GraphicalUserInterface.class.getResource("/fxml/FXMLSetUpController.fxml"));
            this.setUpRoot = setUpLoader.load();
            this.setUpController = this.setUpLoader.getController();
            System.out.println("Controller created for set up.");
            this.gameSetUpScene = new Scene(setUpRoot, 550, 600);
        } catch (IOException e) {
            System.out.println("Could not find the file for set up.");
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Battleships");

        setStartScene();
        stage.show();
        System.out.println("Did show!");

        //TESTING
        if (this.setUpController == null) {
            System.out.println("Controller is null!");
        }

        this.startController.setWelcome();
        System.out.println("Set welcome!");

        Thread t = new Thread(() -> Game.getInstance().finishStartMethod());
        t.start();

        t.setUncaughtExceptionHandler((e, ei) -> {
            ei.printStackTrace();
            System.exit(0);
        });
    }

    public void setStartScene() {
        this.stage.setScene(this.gameStartScene);
    }

    public void setSetUpScene() {
        this.stage.setScene(this.gameSetUpScene);
        //this.setUpController.setInstructions();
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }

    public static UserInterface getInstance() {
        if (instance == null) {
            GraphicalUserInterface newInterface = new GraphicalUserInterface();
            singletonHasBeenCreated = true;
            instance = newInterface;
        }
        return instance;
    }

    @Override
    public void setUpScanner(Scanner scanner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void abandonInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void welcome() {
        System.out.println("Starting GUI...");                                  //STARTGUI
        launch(GraphicalUserInterface.class);                                   //STARTGUI
    }

    private boolean gameModeChosen = false;

    @Override
    public int getGamemode() {
        while (!this.startController.gameModeValueSet) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int result = this.startController.gameModeValue;
        return result;
    }

    @Override
    public String getPlayerName(int number) {
        while (!this.startController.namesSet) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (number == 1) {
            System.out.println("Returning the name " + this.startController.p1Name);
            return this.startController.p1Name;
        } else if (number == 2) {
            System.out.println("Returning the name " + this.startController.p2Name);
            return this.startController.p2Name;
        } else {
            System.out.println("Apparently this can be called with an int other than 1 or 2...");
            return null;
        }
    }

    private <T> T getVariableAfterItHasBeenSetInController(Supplier<T> variableSupplier, BooleanSupplier condition) {
        while (!condition.getAsBoolean()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return variableSupplier.get();
    }

    @Override
    public void printRulesForPlayerSetUp(int numberOfShips, String name) {
        Platform.runLater(() -> {
            this.setUpController.setInstructions(name, numberOfShips);
        });
    }

    @Override
    public void printRulesForPlayerTurn(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printForNoNewCoordinates(int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printForShipPlacement(int ship) {
        Platform.runLater(() -> {
            this.setUpController.updateShipSize(ship);
        });
    }

    // DO NOT CHANGE PANE ORDER IN GRAPH BUILDER!!!
    public int getListIndex(int row, int column, int length) {
        return row * length + column;
    }

    @Override
    public void printSea(Sea sea) {
        //connect the Sea and the visual "sea"
        Platform.runLater(() -> {
            GridPane gridPane = this.setUpController.seaGridPane;

            //Print sea for testing
            System.out.println("  1 2 3 4 5 6 7 8 9 10");
            for (int i = 0; i < sea.getSea().length; i++) {
                System.out.print((i + 1) + " ");
                for (int j = 0; j < sea.getSea()[0].length; j++) {
                    System.out.print(sea.getSea()[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            //print testing ends

            for (int i = 0; i < sea.getSea().length; i++) {
                for (int j = 0; j < sea.getSea()[0].length; j++) {
                    Node child = gridPane.getChildren().get(getListIndex(i + 2, j, 10));

                    if (sea.getSea()[i][j] != 0) {
                        child.setId("sea-with-ship");
                    } else {
                        child.setId("open-sea");
                    }
                }
            }
            
            //this.setUpController.changeNextPlayerButtonVisibility(true);
        });
    }

    @Override
    public void printMaskedSea(Player player, String missOrHit) {
        //almost like printSea, except for 
            //if area is " -", it's sea-under-mist
            //if area is " O", it's open-sea
            //if area is " X", it's sea-with-sunken-ship
    }

    @Override
    public void gameOver(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRow(int seaSize) {
        //first wait till all row,col and dir are set, then return
        String result = this.getVariableAfterItHasBeenSetInController(() -> this.setUpController.rowValue,
                () -> this.setUpController.areCoordinatesSet);
        return Integer.parseInt(result) - 1;
    }

    @Override
    public int getColumn(int seaSize) {
        String result = this.getVariableAfterItHasBeenSetInController(() -> this.setUpController.colValue,
                () -> this.setUpController.areCoordinatesSet);
        return Integer.parseInt(result) - 1;
    }

    @Override
    public String getDirection(int ship) {
        String result = this.getVariableAfterItHasBeenSetInController(() -> this.setUpController.dirValue,
                () -> this.setUpController.areCoordinatesSet);
        this.setUpController.areCoordinatesSet = false;
        return result;
    }

    @Override
    public boolean directionNotAllowed(String direction) {
        Platform.runLater(() -> {
            this.setUpController.changeErrorMessageVisibility(true);
        });
        System.out.println(direction);
        return true;
    }
}
