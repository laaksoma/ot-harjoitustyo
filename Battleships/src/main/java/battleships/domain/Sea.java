package battleships.domain;

import java.util.Arrays;

public class Sea {

    int[][] array;
    String[][] mask;
    int size;

    public Sea(int size) {
        this.array = createSea(size);
        this.mask = createMaskedSea(size);
        this.size = size;
    }

    private int[][] createSea(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = 0;
            }
        }
        return array;
    }

    private String[][] createMaskedSea(int size) {
        String[][] array = new String[size][size];
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
        this.array = createSea(this.size);
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
        return this.size;
    }

    @Override
    public boolean equals(Object compared) {
        if (compared == this) {
            return true;
        }

        if (compared.getClass() != this.getClass()) {
            return false;
        }

        Sea compSea = (Sea) compared;

        return Arrays.deepEquals(this.array, compSea.getSea())
                && Arrays.deepEquals(this.mask, compSea.getMaskedSea())
                && this.size == compSea.getSeaSize();
    }

}
