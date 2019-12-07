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
    private GraphicalUserInterface GraphUserInterface;
    public Boolean namesSet = false;
    public String p1Name = null;
    public String p2Name = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.GraphUserInterface = (GraphicalUserInterface) GraphicalUserInterface.getInstance();
    }

    public void setDefaultValuesForControllerAnnotations() {
        System.out.println("Starting to reset StartController.");
        setWelcome();
        changeRadioButtonEnable(false);
        this.btnAlone.setSelected(false);
        this.btnFriend.setSelected(false);
        changeHBoxEnable(this.hBox1, true, false);
        changeHBoxEnable(this.hBox2, true, false);
        setTextForChooseSomething(null);
        this.gameModeValueSet = false;
        //reset gameModeValue here
        this.namesSet = false;
        this.p1Name = null;
        this.p2Name = null;
        this.playGameButton.setText("OK!");
        this.playGameButton.setDisable(false);
        System.out.println("StartController reset done!");
    }

    public void playGameButtonHandling() {
        if (gameModeValueSet) {
            return;
        }

        if (btnFriend.isSelected() == btnAlone.isSelected()) {
            setTextForChooseSomething("You need to choose a mode to play!");
            return;
        }

        Toggle toggle = btnAlone.getToggleGroup().getSelectedToggle();
        changeRadioButtonEnable(true);
        playGameButton.setText("Set up game!");

        if (toggle == btnAlone) {
            gameModeValue = 0;
            changeHBoxEnable(this.hBox1, false, true);
        } else {
            gameModeValue = 1;
            changeHBoxEnable(this.hBox1, false, true);
            changeHBoxEnable(this.hBox2, false, true);
        }

        gameModeValueSet = true;
        playGameButton.setOnAction((e) -> handlePlayGameButtonPressAfterGameModeSelected());
    }

    public void handlePlayGameButtonPressAfterGameModeSelected() {
        if (player1Name.getText().trim().isEmpty()) {
            return;
        }

        if (gameModeValue == 1 && player2Name.getText().trim().isEmpty()) {
            setTextForChooseSomething("Not ready yet!");
            return;
        }

        playGameButton.setDisable(true);
        p1Name = player1Name.getText().trim();
        p2Name = player2Name.getText().trim();
        namesSet = true;

        this.GraphUserInterface.setSetUpScene();
    }

    private void changeRadioButtonEnable(boolean value) {
        btnAlone.setDisable(value);
        btnFriend.setDisable(value);
    }

    private void changeHBoxEnable(HBox h, boolean isDisabled, boolean isVisible) {
        h.setDisable(isDisabled);
        h.setVisible(isVisible);
    }

    private void setTextForChooseSomething(String text) {
        this.chooseSomething.setText(text);
    }

    public void setWelcome() {
        System.out.println("About to set texts");
        welcome.setText("Welcome to play the Battlehsips game!");
        aloneOrFriend.setText("Would you like to play alone or with a friend?");
        System.out.println("Set texts");
    }
}
