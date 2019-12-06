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
    private String rowValue;
    @FXML
    private TextField column;
    private String colValue;
    @FXML
    private TextField direction;
    private String dirValue;
    @FXML
    private Label shipSize;
    @FXML
    private Label errorMessage;
//    @FXML
//    private Label rowError;
//    @FXML
//    private Label colError;
//    @FXML
//    private Label dirError;
    @FXML
    private Button getCoordsButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setInstructions() {
        instructions.setText("Creating the board for " + "INSERT HERE THE PLAYER" + "\n"
                + "You are given " + "INSERT HERE THE NUMBER OF SHIPS" + " ships. Place them in the sea by giving\n"
                + "the starting coordinates and direction (WASD) of where you'd like to place them.\n"
                + "The placement must follow these rules:\n"
                + " - all parts of the ship must be placed within the visible area,\n"
                + " - no ship is allowed to be stationed directly next to another ship, \n"
                + " - and no ship is allowed to be stationed on top of another ship.");
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

    public void changeLabelVisibility(Label label) {
        label.setVisible(!label.isVisible());
    }

    public void handleCoordinatesButton() {
        //take field values and save them
        this.rowValue = row.getText();
        this.colValue = column.getText();
        this.dirValue = direction.getText();

        if (shouldAskValuesAgain(this.rowValue, this.colValue, this.dirValue)) {
            //here is what happens if the values are NOT VALID
            changeCoordinateButtonVisibility();
            changeLabelVisibility(this.errorMessage);
            row.clear();
            column.clear();
            direction.clear();
        } else {
            //and here the values are valid
            //tell GUI to come and fetch the coordinates
        }
    }

    public boolean shouldAskValuesAgain(String r, String c, String d) {         //If values are incorrect, returns true
        try {
            int intRow = Integer.parseInt(r);
            int intCol = Integer.parseInt(c);

            if (intRow < 1 || intRow > 10) {
                return true;
            } else if (intCol < 1 || intCol > 10) {
                return true;
            }
        } catch (NumberFormatException e) {
            return true;
        }

        String dirTrim = d.trim();

        return !(dirTrim.equalsIgnoreCase("w") || 
                dirTrim.equalsIgnoreCase("a") ||
                dirTrim.equalsIgnoreCase("s") ||
                dirTrim.equalsIgnoreCase("d"));
    }
}
