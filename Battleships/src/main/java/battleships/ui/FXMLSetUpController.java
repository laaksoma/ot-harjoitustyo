package battleships.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FXMLSetUpController implements Initializable {

    @FXML
    private Label instructions;
    @FXML
    private TextField row;
    public String rowValue;
    @FXML
    private TextField column;
    public String colValue;
    @FXML
    private TextField direction;
    public String dirValue;
    @FXML
    private Label shipSize;
    @FXML
    private Label errorMessage;
    @FXML
    private Button getCoordsButton;
    @FXML
    public GridPane seaGridPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        row.textProperty().addListener((ob, oldValue, newValue) -> {

            getCoordsButton.setDisable(newValue.isEmpty()
                    || column.getText().isEmpty()
                    || direction.getText().isEmpty());
        });

        column.textProperty().addListener((ob, oldValue, newValue) -> {

            getCoordsButton.setDisable(row.getText().isEmpty()
                    || newValue.isEmpty()
                    || direction.getText().isEmpty());
        });

        direction.textProperty().addListener((ob, oldValue, newValue) -> {

            getCoordsButton.setDisable(row.getText().isEmpty()
                    || column.getText().isEmpty()
                    || newValue.isEmpty());
        });

    }
   
    public void setInstructions(String playerName, int numberOfShips) {
        instructions.setText("Creating the board for " + playerName + "\n"
                + "You are given " + numberOfShips + " ships. Place them in the sea by giving\n"
                + "the starting coordinates and direction (WASD) of where you'd like to place them.\n"
                + "The placement must follow these rules:\n"
                + " - all parts of the ship must be placed within the visible area,\n"
                + " - no ship is allowed to be stationed directly next to another ship, \n"
                + " - and no ship is allowed to be stationed on top of another ship.");
    }

    public void updateShipSize(int sizeOfNewShip) {
        shipSize.setText("" + sizeOfNewShip);
    }

    public void changeCoordinateButtonEnable() {
        getCoordsButton.setDisable(!getCoordsButton.isVisible());
    }

    public void changeErrorMessageVisibility(boolean value) {
        errorMessage.setVisible(value);
    }

    public boolean areCoordinatesSet;

    public void handleCoordinatesButton() {
        areCoordinatesSet = false;
        this.rowValue = row.getText();
        this.colValue = column.getText();
        this.dirValue = direction.getText();

        if (shouldAskValuesAgain(this.rowValue, this.colValue, this.dirValue)) {
            changeErrorMessageVisibility(true);
        } else {
            this.areCoordinatesSet = true;
            changeErrorMessageVisibility(false);
        }

        changeCoordinateButtonEnable();
        row.clear();
        column.clear();
        direction.clear();
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

        return !(dirTrim.equalsIgnoreCase("w")
                || dirTrim.equalsIgnoreCase("a")
                || dirTrim.equalsIgnoreCase("s")
                || dirTrim.equalsIgnoreCase("d"));
    }
}
