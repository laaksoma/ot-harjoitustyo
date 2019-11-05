package battleships;

import java.util.ArrayList;

public class Player {

    String name;
    int[][] sea;
    ArrayList<Integer> ships;

    public Player(String playerName) {
        this.name = playerName;
        this.sea = createSea();
        this.ships = new ArrayList<Integer>();
    }

    // 0 for empty sea
    public int[][] createSea() {
        int[][] array = new int[5][5];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = 0;
            }
        }

        return array;
    }

    public String getName() {
        return this.name;
    }

    public int[][] getSea() {
        return this.sea;
    }

    public void printSea() {
        for (int i = 0; i < this.sea.length; i++) {
            for (int j = 0; j < this.sea[0].length; j++) {
                System.out.print(this.sea[i][j]);
            }
            
            System.out.println();
        }
    }

    public ArrayList<Integer> getShips() {
        return this.ships;
    }
}
