package battleships.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    }
    
    private void handlePaneClicked(Node node) {
        this.row = GridPane.getRowIndex(node);
        this.column = GridPane.getColumnIndex(node);
        this.areCoordinatesSet = true;
        System.out.println("Pane at (" + this.row + ", " + this.column + ") was clicked!");
    }
    
    public void changeLabelVisibility(Label label, boolean value) {
        label.setVisible(!value);
    }
    
    public void changeGridPaneEnable(GridPane gp, boolean value) {
        gp.setDisable(!value);
    }
    
    public void updateTryAnotherLocationLabel(int row, int column) {
        changeLabelVisibility(this.tryAnotherLocation, true);
        this.tryAnotherLocation.setText("You have already tried row " + row + ", column " + column + "!\n" + 
                "Won't you try another location?");
    }
    
    public void updateTurnForPlayerLabel(String name) {
        this.turnForPlayer.setText("It's turn for player " + name);
    }
    
    public void setSeaLabels(String name1, String name2) {
        seaLabelPlayer1.setText("Sea for " + name1);
        seaLabelPlayer2.setText("Sea for " + name2);
    }
}
