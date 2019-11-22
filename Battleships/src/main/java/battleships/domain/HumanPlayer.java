package battleships.domain;

import battleships.ui.UserInterface;

public class HumanPlayer extends Player {

    public HumanPlayer(String playerName) {
        super(playerName);
    }

    @Override
    public PlacementInfo decideCoordinates(int ship) {
        int row = UserInterface.getInstance().getRow(getSea().getSeaSize());
        int column = UserInterface.getInstance().getColumn(getSea().getSeaSize());
        String direction = Game.getInstance().getDirection(ship);
        
        PlacementInfo info = new PlacementInfo(row, column, direction);
        
        return info;
    }

}
