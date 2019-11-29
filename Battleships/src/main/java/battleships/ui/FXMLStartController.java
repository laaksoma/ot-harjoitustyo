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
    @FXML
    public int gameModeValue;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void playGameButtonHandling() {
        if(btnFriend.isSelected() == btnAlone.isSelected()){
            chooseSomething.setText("You need to choose a mode to play!");
        }
        
        Toggle toggle = btnAlone.getToggleGroup().getSelectedToggle();
        //set buttons disabled
        
        if (toggle == btnAlone) {
            //System.out.println("Alone!");
            gameModeValue = 0;
            //bring up text field for PLAYER1
        } else {
            //System.out.println("You chose something else!");
            gameModeValue = 1;
            //bring up both text fields
        }
    }

    public void setWelcome() {
        System.out.println("About to set texts");
        welcome.setText("Welcome to play the Battlehsips game!");
        aloneOrFriend.setText("Would you like to play alone or with a friend?");
        System.out.println("Set texts");
    }

}
