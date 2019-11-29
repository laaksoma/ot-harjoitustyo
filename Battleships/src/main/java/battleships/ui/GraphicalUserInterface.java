package battleships.ui;

import battleships.domain.Player;
import battleships.domain.Sea;
import java.io.IOException;
import java.util.Scanner;
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
    public FXMLLoader loader;
    public FXMLStartController startController;
    private static UserInterface instance = null;

    @Override
    public void init() throws Exception {
        System.out.println("Initialize called (after launch)!");
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Starting! (in start method)");

        
        
        String fileName = "/fxml/InstructionsFXML.fxml";
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
        System.out.println("Exception occurred already?");
        this.startController.setWelcome();
        System.out.println("At the end of start!");

    }

    public static UserInterface getInstance() {
        if (instance == null) {
            GraphicalUserInterface newInterface = new GraphicalUserInterface();
            System.out.println("ONLY ONCE!");
            System.out.println("Instance set for GUI.");
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
        launch(GraphicalUserInterface.class);                                                     //STARTGUI
    }

    @Override
    public int getGamemode() {
        return this.startController.gameModeValue;
    }

    @Override
    public String getPlayerName(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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

    @Override
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
