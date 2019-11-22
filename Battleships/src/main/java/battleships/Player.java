package battleships;

import java.util.ArrayList;

public class Player {

    String name;
    int[][] sea;
    String[][] maskedSea;
    ArrayList<Integer> ships;

    public Player(String playerName) {
        this.name = playerName;
        this.sea = createSea();
        this.maskedSea = createMaskedSea();
        this.ships = new ArrayList<Integer>();
    }

    private int[][] createSea() {
        int[][] array = new int[5][5];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = 0;
            }
        }

        return array;
    }
    
    private String[][] createMaskedSea() {
        String[][] array = new String[5][5];
        
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = " -";
            }
        }

        return array;
    }

    public void addShipToTheSea(int row, int column, int ship) {
        this.sea[row][column] = ship;
    }

    public int modifySea(int row, int column) {
        addShipToTheSea(row, column, -this.sea[row][column]);
        return this.sea[row][column];
    }
    
    public String modifyMaskedSea(int row, int column, int value) {
        if (value == 0) { 
            this.maskedSea[row][column] = " O"; //MISS
        } else {
            this.maskedSea[row][column] = " X";  //HIT
            this.sea[row][column] = 0; 
        }
        
        return this.maskedSea[row][column];
    }

    public void clearTheSea() {
        this.sea = createSea();
    }
    
    public boolean isAreaEmpty(int row, int column) {
        return (this.sea[row][column] == 0);
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
        System.out.println("  1 2 3 4 5");
        
        for (int i = 0; i < this.sea.length; i++) {
            System.out.print((i + 1) + " ");
            
            for (int j = 0; j < this.sea[0].length; j++) {
                System.out.print(this.sea[i][j] + " ");
            }

            System.out.println();
        }
        
        System.out.println();
    }

    public void printMaskedSea() {
        System.out.println("  1  2  3  4  5");
        
        for (int i = 0; i < this.maskedSea.length; i++) {
            System.out.print(i + 1);
            
            for (int j = 0; j < this.maskedSea[0].length; j++) {
                System.out.print(this.maskedSea[i][j] + " ");
            }

            System.out.println();
        }
        
        System.out.println();
    }

    public void setShips(int howMany) {
        for (int i = howMany; i > 0; i--) {
            this.ships.add(i);
        }
    }

    public ArrayList<Integer> getShips() {
        return this.ships;
    }

}
