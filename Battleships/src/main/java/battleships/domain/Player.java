package battleships.domain;

import java.util.ArrayList;

public abstract class Player {
    String name;
    Sea sea;
    ArrayList<Integer> ships;

    public Player(String playerName) {
        this.name = playerName;
        this.sea = new Sea(5);
        this.ships = new ArrayList<Integer>();
    }

    public Sea getSea() {
        return this.sea;
    }

    public String getName() {
        return this.name;
    }
    
    public void addShipForPlayer(int row, int column, int ship) {
        this.sea.addShipToTheSea(row, column, ship);
    }

    public void setShips(int howMany) {
        for (int i = howMany; i > 0; i--) {
            this.ships.add(i);
        }
    }

    public ArrayList<Integer> getShips() {
        return this.ships;
    }
    
    public abstract PlacementInfo decideCoordinates(int ship, boolean needForDirection, int gameSize);

}
