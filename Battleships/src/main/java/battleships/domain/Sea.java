package battleships.domain;

public class Sea {

    int[][] array;
    String[][] mask;
    //Player player;

    public Sea() {
        this.array = createSea();
        this.mask = createMaskedSea();
        //this.player = player;
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

    public int modifySea(int row, int column) {
        addShipToTheSea(row, column, -this.array[row][column]);
        return this.array[row][column];
    }

    public String modifyMaskedSea(int row, int column, int value) {
        if (value == 0) {
            this.mask[row][column] = " O"; //MISS
        } else {
            this.mask[row][column] = " X"; //HIT
            this.array[row][column] = 0;
        }
        return this.mask[row][column];
    }

    public int[][] getSea() {
        return this.array;
    }
    
    public String[][] getMaskedSea() {
        return this.mask;
    }

    public boolean isAreaEmpty(int row, int column) {
        return this.array[row][column] == 0;
    }

    public void clearTheSea() {
        this.array= createSea();
    }

    public boolean seaIsEmpty() {
        for (int i = 0; i < this.array.length; i++) {
            for (int j = 0; j < this.array[0].length; j++) {
                if (this.array[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addShipToTheSea(int row, int column, int ship) {
        this.array[row][column] = ship;
    }

    public int getSeaSize() {
        return this.array.length;
    }

}
