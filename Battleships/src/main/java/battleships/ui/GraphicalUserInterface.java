package battleships.ui;

import battleships.domain.Game;
import battleships.domain.Player;
import battleships.domain.Score;
import battleships.domain.Sea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GraphicalUserInterface extends Application implements UserInterface {

    static boolean singletonHasBeenCreated = false;
    private Stage stage;
    public FXMLLoader startLoader;
    public FXMLLoader setUpLoader;
    public FXMLLoader playGameLoader;
    public FXMLStartController startController;
    public FXMLSetUpController setUpController;
    public FXMLPlayGameController playGameController;
    private Scene gameStartScene;
    private Scene gameSetUpScene;
    private Scene playGameScene;
    private static UserInterface instance = null;
    private Parent startRoot;
    private Parent setUpRoot;
    private Parent playGameRoot;

    @Override
    public void init() throws Exception {
        if (instance != null && (instance != this)) {
            this.startLoader = ((GraphicalUserInterface) instance).startLoader;
            this.startController = ((GraphicalUserInterface) instance).startController;
            instance = this;
        }

        initializeStartScene();
        initializeSetUpScene();
        initializePlayGameScene();
    }

    private void initializeStartScene() {
        try {
            this.startLoader = new FXMLLoader(GraphicalUserInterface.class.getResource("/fxml/InstructionsFXML.fxml"));
            this.startRoot = startLoader.load();
            this.startController = this.startLoader.getController();
            this.gameStartScene = new Scene(startRoot, 610, 480);
        } catch (IOException e) {
            System.out.println("Could not find the file for starting.");
            e.printStackTrace();
        }
    }

    private void initializeSetUpScene() {
        try {
            this.setUpLoader = new FXMLLoader(GraphicalUserInterface.class.getResource("/fxml/FXMLSetUpController.fxml"));
            this.setUpRoot = setUpLoader.load();
            this.setUpController = this.setUpLoader.getController();
            this.gameSetUpScene = new Scene(setUpRoot, 610, 480);
        } catch (IOException e) {
            System.out.println("Could not find the file for set up.");
            e.printStackTrace();
        }
    }

    private void initializePlayGameScene() {
        try {
            this.playGameLoader = new FXMLLoader(GraphicalUserInterface.class.getResource("/fxml/FXMLPlayGameController.fxml"));
            this.playGameRoot = playGameLoader.load();
            this.playGameController = playGameLoader.getController();
            this.playGameScene = new Scene(playGameRoot, 610, 480);
        } catch (IOException e) {
            System.out.println("Could not find the file for play game.");
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        stage.setTitle("Battleships");

        setStartScene();
        stage.show();

        this.startController.setWelcome();

        Thread t = new Thread(() -> Game.getInstance().finishStartMethod());
        t.start();

        t.setUncaughtExceptionHandler((e, ei) -> {
            ei.printStackTrace();
            System.exit(0);
        });
    }

    public void setStartScene() {
        this.stage.setScene(this.gameStartScene);
    }

    public void setSetUpScene() {
        this.stage.setScene(this.gameSetUpScene);
    }

    public void resetGameValues() {
        this.startController.setDefaultValuesForController();
        this.playGameController.setDefaultValuesForController();
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }

    /**
     * If the class instance is null, then sets a new instance.
     *
     * @return Instance of the class
     */
    public static UserInterface getInstance() {
        if (instance == null) {
            GraphicalUserInterface newInterface = new GraphicalUserInterface();
            singletonHasBeenCreated = true;
            instance = newInterface;
        }
        return instance;
    }

    @Override
    public void welcome() {
        launch(GraphicalUserInterface.class);                                   //STARTGUI
    }

    @Override
    public void printHighScores(ArrayList<Score> highScores) {
        Platform.runLater(() -> {
            this.startController.setHighScores(highScores);
        });
    }

    private boolean gameModeChosen = false;

    @Override
    public int getGamemode() {
        while (!this.startController.gameModeValueSet) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int result = this.startController.gameModeValue;
        return result;
    }

    @Override
    public String getPlayerName(int number) {
        while (!this.startController.namesSet) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (number == 1) {
            return this.startController.p1Name;
        } else {
            return this.startController.p2Name;
        }
    }

    @Override
    public void printRulesForPlayerSetUp(int numberOfShips, String name) {
        Platform.runLater(() -> {
            this.setUpController.setInstructions(name, numberOfShips);
        });
    }

    @Override
    public void printRulesForPlayerTurn(String name) {
        Platform.runLater(() -> {
            this.playGameController.updateTurnForPlayerLabel(name, true, 0);
        });
    }

    @Override
    public void printForNoNewCoordinates(int row, int column) {
        Platform.runLater(() -> {
            this.playGameController.updateTryAnotherLocationLabel(row, column, true);
        });
    }

    @Override
    public void printForShipPlacement(int ship) {
        Platform.runLater(() -> {
            this.setUpController.updateShipSize(ship);
        });
    }

    // DO NOT CHANGE PANE ORDER IN GRAPH BUILDER
    public int getListIndex(int row, int column, int length) {
        return row * length + column;
    }

    @Override
    public void printSea(Sea sea) {
        Platform.runLater(() -> {
            GridPane gridPane = this.setUpController.seaGridPane;

            for (int i = 0; i < sea.getSea().length; i++) {
                for (int j = 0; j < sea.getSea()[0].length; j++) {
                    Node child = gridPane.getChildren().get(getListIndex(i + 2, j, 10));

                    if (sea.getSea()[i][j] != 0) {
                        child.setId("sea-with-ship");
                    } else {
                        child.setId("open-sea");
                    }
                }
            }
        });
    }

    private void updateTurnAbilities(boolean v1, boolean v2) {
        this.playGameController.changeLabelVisibility(this.playGameController.whereToHitPlayer1, v1);
        this.playGameController.changeLabelVisibility(this.playGameController.whereToHitPlayer2, v2);
        this.playGameController.changeGridPaneEnable(this.playGameController.gridPanePlayer1, v2);
        this.playGameController.changeGridPaneEnable(this.playGameController.gridPanePlayer2, v1);

    }

    private boolean needToSetPlayGameScene = true;

    private void setSceneForPlayingTheGame() {
        this.playGameController.prepareForReload();
        String name1 = Game.getInstance().getListOfPlayers().get(0).getName();
        String name2 = Game.getInstance().getListOfPlayers().get(1).getName();
        this.playGameController.setPlayerLabels(name1, name2);
        this.stage.setScene(this.playGameScene);
        needToSetPlayGameScene = false;
    }

    @Override
    public void printMaskedSea(Player player, String missOrHit, int index) {
        Platform.runLater(() -> {
            if (needToSetPlayGameScene) {
                setSceneForPlayingTheGame();
            }

            GridPane gridPane;

            if (index == 0) {
                updateTurnAbilities(false, true);
                gridPane = this.playGameController.gridPanePlayer1;
            } else {
                updateTurnAbilities(true, false);
                gridPane = this.playGameController.gridPanePlayer2;
            }

            for (int i = 0; i < player.getSea().getMaskedSea().length; i++) {
                for (int j = 0; j < player.getSea().getMaskedSea()[0].length; j++) {
                    Node child = gridPane.getChildren().get(getListIndex(i + 2, j, 10));

                    if (player.getSea().getMaskedSea()[i][j].equals(" -")) {
                        child.setId("sea-under-mist");
                    } else if (player.getSea().getMaskedSea()[i][j].equals(" O")) {
                        child.setId("open-sea");
                    } else {
                        child.setId("sea-with-sunken-ship");
                    }
                }
            }
        });

        while (needToSetPlayGameScene) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void printPoints(Player playerInTurn, Player playerNotInTurn) {
        Platform.runLater(() -> {
            this.playGameController.setPointsForPlayers(playerInTurn.getPointsAsInt(), playerNotInTurn.getPointsAsInt());
        });
    }

    @Override
    public void gameOver(Player player) {
        Platform.runLater(() -> {
            this.playGameController.updateAbilityValuesForGameOver();
            this.playGameController.updateTurnForPlayerLabel(player.getName(), false, player.getPointsAsInt());
            this.playGameController.changeNewGameButtonVisibility(true);
            resetGameValues();
        });

        this.needToSetPlayGameScene = true;

        while (!this.playGameController.wantsToReplay) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.playGameController.wantsToReplay = false;
        Game.getInstance().finishStartMethod();
    }

    private <T> T getVariableAfterItHasBeenSetInController(Supplier<T> variableSupplier, BooleanSupplier condition) {
        while (!condition.getAsBoolean()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return variableSupplier.get();
    }

    @Override
    public int getRow() {
        if (needToSetPlayGameScene) {
            String result = this.getVariableAfterItHasBeenSetInController(() -> this.setUpController.rowValue,
                    () -> this.setUpController.areCoordinatesSet);
            return Integer.parseInt(result) - 1;
        } else {
            Integer row = this.getVariableAfterItHasBeenSetInController(() -> this.playGameController.row,
                    () -> this.playGameController.areCoordinatesSet);
            return row - 1;
        }
    }

    @Override
    public int getColumn() {
        if (needToSetPlayGameScene) {
            String result = this.getVariableAfterItHasBeenSetInController(() -> this.setUpController.colValue,
                    () -> this.setUpController.areCoordinatesSet);
            return Integer.parseInt(result) - 1;
        } else {
            int column = this.playGameController.column;
            this.playGameController.areCoordinatesSet = false;
            return column - 1;
        }
    }

    @Override
    public String getDirection(int ship) {
        String result = this.getVariableAfterItHasBeenSetInController(() -> this.setUpController.dirValue,
                () -> this.setUpController.areCoordinatesSet);
        this.setUpController.areCoordinatesSet = false;
        return result;
    }

    @Override
    public boolean directionNotAllowed(String direction) {
        Platform.runLater(() -> {
            this.setUpController.changeErrorMessageVisibility(true);
        });
        System.out.println(direction);
        return true;
    }
}
