package battleships.domain;

import battleships.ui.UserInterface;

public class HumanPlayer extends Player {

    public HumanPlayer(String playerName) {
        super(playerName);
    }

    @Override                               
    public PlacementInfo decideCoordinates(int ship, boolean needForDirection, int gameSize) {
        if (ship == 0) {       //during playing the game; turn
            UserInterface.getInstance().printRulesForPlayerTurn(getName());        //move this somewhere so it doesn't print again when guessing same coordinates?
        } else {                //when setting up the game
            UserInterface.getInstance().printSea(getSea());
            UserInterface.getInstance().printForShipPlacement(ship);
        }

        int row = UserInterface.getInstance().getRow(gameSize);
        int column = UserInterface.getInstance().getColumn(gameSize);
        String direction = null;

        if (needForDirection) {
            direction = UserInterface.getInstance().getDirection(ship);
        }

        return new PlacementInfo(row, column, direction);
    }

}
