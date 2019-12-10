package battleships.domain;

import battleships.ui.UserInterface;

/**
 * Extension of {@link Player} class made for human {@link Player} when playing
 * Battleships.
 */
public class HumanPlayer extends Player {

    /**
     * Creates a new instance of {@link HumanPlayer}.
     * <p>
     * Calls ADD LINK TO PLAYER CONSTRUCTOR? with the given parameter
     * playerName.</p>
     *
     * @param playerName Name of the player
     */
    public HumanPlayer(String playerName) {
        super(playerName);
    }

    /**
     * Overrides method from {@link Player}.
     * <p>
     * The method gets information of game phase through parameter ship; if the
     * ship is 0, there is no need to set it in the {@link Sea}, and the method
     * calls for ADD LINK TO UI.RULESFORTURN.<br>
     * If the ship is set as not zero, then calls for printing the {@link Sea}
     * and rules for placing the ship are excecuted. </p>
     *<p>Asks both row and column through {@link UserInterface}.</p>
     * <p>If needForDirection is true, calls for ADD LINK TO UI.GETDIRECTION, if
     * false, sets dir as null.</p>
     * @param ship The ship that the coordinates are fetched for
     * @param needForDirection True when setting up the game, false when playing
     * the game
     * @param gameSize Size of the game board
     * @return new {@link PlacementInfo} with row, column and dir as parameters
     */
    
    @Override
    public PlacementInfo decideCoordinates(int ship, boolean needForDirection, int gameSize) {
        if (ship == 0) {
            UserInterface.getInstance().printRulesForPlayerTurn(getName());
        } else {
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
