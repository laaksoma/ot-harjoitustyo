package battleships;

import java.util.ArrayList;

//In the sea, 
//0 stands for empty area
//1 stands for area with a ship of size 1
//Thus X stands for area with a ship of size X

public class Player {

    String name;
    int[][] sea;
    ArrayList<Integer> ships;

    public Player(String playerName) {
        this.name = playerName;
        this.sea = createSea();
        this.ships = new ArrayList<Integer>();
    }

    public int[][] createSea() {
        int[][] array = new int[5][5];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = 0;
            }
        }

        return array;
    }
    
    public void addShipToTheSea(int row, int column, int ship) {
        this.sea[row][column] = ship;
    }
    
    public void clearTheSea() {
        this.sea = createSea();
    }
    
    public boolean seaIsEmpty() {
        for (int i = 0; i < this.sea.length; i++) {
            for (int j = 0; j < this.sea[0].length; j++) {
                if (this.sea[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getName() {
        return this.name;
    }

    public int[][] getSea() {
        return this.sea;
    }
    
    public int getSeaSize() {
        return this.sea.length;
    }

    public void printSea() {
        for (int i = 0; i < this.sea.length; i++) {
            for (int j = 0; j < this.sea[0].length; j++) {
                System.out.print(this.sea[i][j]);
            }

            System.out.println();
        }
    }

    public void setShips(int howMany) {
        for(int i = howMany; i > 0; i--) {
            this.ships.add(i);
        }
    }

    public ArrayList<Integer> getShips() {
        return this.ships;
    }

}


