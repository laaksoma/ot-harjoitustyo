package battleships.domain;

import java.util.ArrayList;

/**
 * An abstract class extended by both {@link HumanPlayer} and {@link BotPlayer}.
 */
public abstract class Player {

    private String name;
    private Sea sea;
    private ArrayList<Integer> ships;
    float points;

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
     * Updates the state of player's points.
     * @param byHowMuch By how much the points will be updated
     */
    
    public void updatePoints(float byHowMuch) {
        this.points = this.points + byHowMuch;
    }
    
    /**
     * Adds all player's points to one number.
     * <p>Player's points consist of the following:<br>
     * how many areas did the player reveal from the other, the lesser the better,<br>
     * how quickly did the player find the other player's ships (added during the game turns),<br>
     * how great is the value of ships still hidden for the player, and<br>
     * how many areas did the other player reveal, once again less is better.</p>
     * @param openedForOther How many areas are revealed of other player's {@link Sea}
     * @return points rounded to int
     */
    
    public int setFinalPoints(int openedForOther) {
        this.points = this.points + (100 - openedForOther) + this.sea.pointValue + (100 - getSea().getOpenedArea());

        return Math.round(this.points);
    }
    
    /**
     * @return Amount of player points as int
     */
    
    public int getPointsAsInt() {
        return Math.round(this.points);
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
