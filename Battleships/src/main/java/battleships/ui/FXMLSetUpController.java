package battleships.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLSetUpController implements Initializable {
    
    @FXML
    private Label instructions;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setInstructions() {
        instructions.setText("");
    }
}
