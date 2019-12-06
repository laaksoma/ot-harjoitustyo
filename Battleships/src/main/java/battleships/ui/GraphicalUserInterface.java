package battleships.ui;

import battleships.domain.Game;
import battleships.domain.Player;
import battleships.domain.Sea;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GraphicalUserInterface extends Application implements UserInterface {

    static boolean singletonHasBeenCreated = false;
    public FXMLLoader loader;
    public FXMLStartController startController;
    private static UserInterface instance = null;

    @Override
    public void init() throws Exception {

    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Starting! (in start method)");
        String fileName = "/fxml/InstructionsFXML.fxml";

        System.out.println("Instance is null?" + (instance == null));
        if (instance != null && (instance != this)) {
            this.loader = ((GraphicalUserInterface) instance).loader;
            this.startController = ((GraphicalUserInterface) instance).startController;
            instance = this;
            //System.out.println("Changed GUI singleton instance");
        }

        try {
            this.loader = new FXMLLoader(GraphicalUserInterface.class.getResource(fileName));
            Parent root = loader.load();
            startController = this.loader.getController();
            System.out.println("Controller created.");
            Scene scene = new Scene(root, 300, 250);

            primaryStage.setTitle("Battleships");

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Could not find the file " + fileName);
            e.printStackTrace();
        }

        this.startController.setWelcome();

        Thread t = new Thread(() -> Game.getInstance().finishStartMethod());
        t.start();
        t.setUncaughtExceptionHandler((e, ei) -> {
            ei.printStackTrace();
            System.exit(0);
        });
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

        /*
            OTHER POSSIBILITY:
              return getVariableAfterItHasBeenSetInController(() -> startController.gameModeValue,
                () -> startController.gameModeValueSet);
         */
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

        /*
            String p1Name = getVariableAfterItHasBeenSetInController(() -> startController.p1Name,
             () -> startController.namesSet);
            String p2Name = getVariableAfterItHasBeenSetInController(() -> startController.p1Name,
             () -> startController.namesSet);
            if(number == 1){
                return p1Name;
            } else if(number == 2) {
                return p2Name;
            } else {
                return null;
            }
         */
    }
/*
    private <T> T getVariableAfterItHasBeenSetInController(Supplier<T> variableSupplier, BooleanSupplier condition) {
        while (!condition.getAsBoolean()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return variableSupplier.get();
    }*/

    @Override
    public void printRulesForPlayerSetUp(int numberOfShips, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printSea(Sea sea) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printMaskedSea(Player player, String missOrHit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gameOver(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getRow(int seaSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumn(int seaSize) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getANumber(int min, int max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDirection(int ship) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean directionNotAllowed(String direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
