package battleships.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLSetUpController implements Initializable {
    
    @FXML
    private Label instructions;
    @FXML
    private TextField row;
    @FXML
    private TextField column;
    @FXML
    private TextField direction;
    @FXML
    private Label shipSize;
    @FXML
    private Button getCoordsButton;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setInstructions() {
        instructions.setText("Creating the board for " + "INSERT HERE THE PLAYER" + "\n" + 
                "You are given " + "INSERT HERE THE NUMBER OF SHIPS" + " ships. Place them in the sea by giving\n" + 
                "the starting coordinates and direction (WASD) of where you'd like to place them.\n" + 
                "The placement must follow these rules:\n" + 
                " - all parts of the ship must be placed within the visible area,\n" + 
                " - no ship is allowed to be stationed directly next to another ship, \n" + 
                " - and no ship is allowed to be stationed on top of another ship.");
    }
    
    public void updateShipSize(int sizeOfNewShip) {
        shipSize.setText("" + sizeOfNewShip);
    }
    
//    public void setShipToGridPane() {
//        
//    }
    
    public void changeCoordinateButtonVisibility() {
        getCoordsButton.setVisible(!getCoordsButton.isVisible());
    }
    
    public void handleCoordinatesButton() {
        //check if coords are allowed ()
    }
}
