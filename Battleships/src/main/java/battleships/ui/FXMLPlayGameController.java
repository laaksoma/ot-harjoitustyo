package battleships.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class FXMLPlayGameController implements Initializable {

    @FXML
    private Label turnForPlayer;
    @FXML
    public Label whereToHitPlayer1;    //default: not visible
    @FXML
    public Label whereToHitPlayer2;    //default: not visible
    @FXML
    public GridPane gridPanePlayer1;   //default: disabled
    @FXML
    public GridPane gridPanePlayer2;   //default: disabled
    @FXML
    private Label tryAnotherLocation;   //default: not visible
    @FXML
    private Label seaLabelPlayer1;
    @FXML
    private Label seaLabelPlayer2;
    @FXML
    private Button newGameButton;       //default: not visible
    public int row;
    public int column;
    public boolean areCoordinatesSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Node node : this.gridPanePlayer1.getChildren()) {
            if (node.getClass() == Pane.class) {
                System.out.println("Node was pane");
                node.setOnMouseClicked((e) -> {
                    handlePaneClicked(node);
                });
            }
        }

        for (Node node : this.gridPanePlayer2.getChildren()) {
            if (node.getClass() == Pane.class) {
                node.setOnMouseClicked((e) -> {
                    handlePaneClicked(node);
                });
            }
        }

        this.newGameButton.setOnAction((e) -> handleNewGameButtonPress());
    }

    public void setDefaultValuesForControllerAnnotations() {
        System.out.println("Starting to reset PlayGameController");
        this.tryAnotherLocation.setVisible(false);
        this.areCoordinatesSet = false;
        changeNewGameButtonVisibility(false);
        System.out.println("PlayGameController reset done");
    }

    private void handlePaneClicked(Node node) {
        this.row = GridPane.getRowIndex(node);
        this.column = GridPane.getColumnIndex(node);
        this.areCoordinatesSet = true;
        this.tryAnotherLocation.setVisible(false);
        System.out.println("Pane at (" + this.row + ", " + this.column + ") was clicked!");
    }

    private void handleNewGameButtonPress() {
        ((GraphicalUserInterface) GraphicalUserInterface.getInstance()).resetGameValues();
        ((GraphicalUserInterface) GraphicalUserInterface.getInstance()).setStartScene();
    }

    public void changeLabelVisibility(Label label, boolean value) {
        label.setVisible(!value);
    }

    public void changeNewGameButtonVisibility(boolean value) {
        this.newGameButton.setVisible(value);
        this.newGameButton.setDisable(!value);
    }

    public void changeGridPaneEnable(GridPane gp, boolean value) {
        gp.setDisable(!value);
    }

    public void updateAbilityValuesForGameOver() {
        this.gridPanePlayer1.setDisable(true);
        this.gridPanePlayer2.setDisable(true);
        this.whereToHitPlayer1.setVisible(false);
        this.whereToHitPlayer2.setVisible(false);
    }

    public void updateTryAnotherLocationLabel(int row, int column, boolean value) {
        changeLabelVisibility(this.tryAnotherLocation, !value);
        int r = row + 1;
        int c = column + 1;
        this.tryAnotherLocation.setText("You have already tried row " + r + ", column " + c + "!\n"
                + "Won't you try another location?");
    }

    public void updateTurnForPlayerLabel(String name, boolean isGameGoing) {
        if (isGameGoing) {
            this.turnForPlayer.setText("It's turn for " + name + "!");
        } else {
            this.turnForPlayer.setText("Congratulation's " + name + ", you won!\n");
        }
    }

    public void setSeaLabels(String name1, String name2) {
        seaLabelPlayer1.setText(name1 + "'s sea");
        seaLabelPlayer2.setText(name2 + "'s sea");
    }
}
