package battleships.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class FXMLStartController implements Initializable {

    @FXML
    private Label welcome;
    @FXML
    private Label chooseSomething;
    @FXML
    private Label aloneOrFriend;
    @FXML
    private RadioButton btnAlone;
    @FXML
    private RadioButton btnFriend;
    @FXML
    private Button playGameButton;
    @FXML
    private HBox hBox1;
    @FXML
    private HBox hBox2;
    @FXML
    private TextField player1Name;
    @FXML
    private TextField player2Name;

    public int gameModeValue;
    public boolean gameModeValueSet;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void playGameButtonHandling() {
        //TESTING CHECK-UP
        if (gameModeValueSet) {
            return;
        }

        if (btnFriend.isSelected() == btnAlone.isSelected()) {
            chooseSomething.setText("You need to choose a mode to play!");
            return;
        }

        Toggle toggle = btnAlone.getToggleGroup().getSelectedToggle();
        disableButtons();
        playGameButton.setText("Set up game!");

        if (toggle == btnAlone) {
            gameModeValue = 0;
            hBox1.setVisible(true);
            //hBox1.setDisable(false);
        } else {
            gameModeValue = 1;
            hBox1.setVisible(true);
            hBox2.setVisible(true);
           // hBox1.setDisable(false);
            //hBox2.setDisable(false);
        }

        gameModeValueSet = true;
        //call for a method with boolean parameter
        playGameButton.setOnAction((e) -> handlePlayGameButtonPressAfterGameModeSelected());
    }

    public Boolean namesSet = false;
    public String p1Name = null;
    public String p2Name = null;

    public void handlePlayGameButtonPressAfterGameModeSelected() {
        if (player1Name.getText().trim().isEmpty()) {
            return;
        }
        
        if (gameModeValue == 1 && player2Name.getText().trim().isEmpty()) {
            return;
        }
        
        playGameButton.setDisable(true);
        p1Name = player1Name.getText().trim();
        p2Name = player2Name.getText().trim();
        namesSet = true;
    }

    private void disableButtons() {
        btnAlone.setDisable(true);
        btnFriend.setDisable(true);
    }

    public void setWelcome() {
        System.out.println("About to set texts");
        welcome.setText("Welcome to play the Battlehsips game!");
        aloneOrFriend.setText("Would you like to play alone or with a friend?");
        System.out.println("Set texts");
    }

}
