package battleships.domain;

import java.util.ArrayList;

/**
 * An abstract class extended by both {@link HumanPlayer} and {@link BotPlayer}.
 */
public abstract class Player {

    String name;
    Sea sea;
    ArrayList<Integer> ships;

    /**
     * Creates a new instance of Player that represents a player of the game.
     * <p>
     * Extended by {@link HumanPlayer} and {@link BotPlayer}.</p>
     * <p>
     * Sets name as playerName given as a parameter,<br>
     * sea as new {@link Sea} by getting the {@link Game} instance, and <br>
     * ships as a new ArrayList containing Integers.</p>
     *
     * @param playerName Name of the player
     */
    public Player(String playerName) {
        this.name = playerName;
        this.sea = new Sea(Game.getGameBoardSize());
        this.ships = new ArrayList<Integer>();
    }

    /**
     * @return The Sea linked with the Player
     */
    public Sea getSea() {
        return this.sea;
    }

    /**
     * @return The name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Forwards the given index and ship information to the
     * {@link Sea#addShipToTheSea(int, int, int)} linked with the player.
     *
     * @param row Index of the row
     * @param column Index of the column
     * @param ship Numeric information of the ship
     */
    public void addShipForPlayer(int row, int column, int ship) {
        this.sea.addShipToTheSea(row, column, ship);
    }

    /**
     * Adds all the ships that are given for Player.
     * <p>
     * Uses a for-loop to add all values to the ArrayList ships in ascending
     * order.</p>
     *
     * @param howMany Int information of how many ships the player will have
     */
    public void setShips(int howMany) {
        for (int i = howMany; i > 0; i--) {
            this.ships.add(i);
        }
    }

    /**
     * @return The ArrayList ships
     */
    public ArrayList<Integer> getShips() {
        return this.ships;
    }

    public abstract PlacementInfo decideCoordinates(int ship, boolean needForDirection, int gameSize);

}
