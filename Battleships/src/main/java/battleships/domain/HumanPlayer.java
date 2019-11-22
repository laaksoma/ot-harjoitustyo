package battleships.domain;

import battleships.ui.UserInterface;

public class HumanPlayer extends Player {

    public HumanPlayer(String playerName) {
        super(playerName);
    }

    @Override                               //0 means no ship to be placed
    public PlacementInfo decideCoordinates(int ship, boolean needForDirection) {
        if (ship == 0) {       //during playing the game; turn
            UserInterface.getInstance().printRulesForPlayerTurn(getName());
        } else {                //when setting up the game
            UserInterface.getInstance().printSea(getSea());
            UserInterface.getInstance().printForShipPlacement(ship);
        }

        int row = UserInterface.getInstance().getRow(getSea().getSeaSize());
        int column = UserInterface.getInstance().getColumn(getSea().getSeaSize());
        String direction = null;

        if (needForDirection) {
            direction = Game.getInstance().getDirection(ship);
        }

        return new PlacementInfo(row, column, direction);
    }

}
