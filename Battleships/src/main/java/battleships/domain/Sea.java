package battleships.domain;

import java.util.Arrays;

/**
 * Represents the two dimensional array (matrix) that holds values for whether
 * the area has ships or not.
 * <p>Has two arrays called <i>array</i> and <i>mask</i>, which both are two dimensional arrays.</p>
 * <p>The <b>array</b> holds information as binaries, as follows:<br>
 * 0 is an area with no ship on it<br>
 * 1 is an area with a ship that's size is the given number, i.e. 2 means a ship of size 2.<br></p>
 * <p>The <b>mask</b> holds information as Strings, as follows:<br>
 * " -" is an area that hasn't been revealed yet<br>
 * " O" is an area that has been revealed and has no ship<br>
 * " X" is an area that has been revealed and has a ship.</p>
 */

public class Sea {

    int[][] array;
    String[][] mask;
    int size;
    
    /**
     * Creates a new Sea with the given size as dimension.
     * <p>
     * Sets the class variables for array, mask and size by
     * creating a new int array for array, 
     * creating a new String array for mask, and
     * setting the given parameter as size.
     * </p>
     * @param size The dimension for the arrays
     */

    public Sea(int size) {
        this.array = createSea(size);
        this.mask = createMaskedSea(size);
        this.size = size;
    }
    
    /**
     * Creates a square matrix with value 0 at each
     * cell.
     * @param size The size of the matrix 
     * @return The created int array
     */

    private int[][] createSea(int size) {
        int[][] array = new int[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = 0;
            }
        }
        return array;
    }
    
    /**
     * Creates a square matrix with " -" at each
     * cell.
     * @param size The size of the matrix
     * @return The created String array
     */

    private String[][] createMaskedSea(int size) {
        String[][] array = new String[size][size];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = " -";
            }
        }
        return array;
    }
    
    
    //THE BELOW IS NEVER USED? CLEAN THIS UP
//    /**
//     * Takes the index for row and column at the int array matrix 
//     * and passes them forward to {@link #addShipToTheSea} with an additional 
//     * numeric complement of the value at the index.
//     * @param row Index for the row at the array
//     * @param column Index for the column at the array
//     * @return The new value at the index
//     */
//
//    public int modifySea(int row, int column) {
//        addShipToTheSea(row, column, -this.array[row][column]);
//        return this.array[row][column];
//    }
    
    /**
     * Modifies the String array matrix at the index
     * by binary information value.
     * @param row Index for the row at the array
     * @param column Index for the column at the array
     * @param value 0 is a miss, 1 is a hit
     * @return The new value at the index
     */

    public String modifyMaskedSea(int row, int column, int value) {
        if (value == 0) {
            this.mask[row][column] = " O"; 
        } else {
            this.mask[row][column] = " X";
            this.array[row][column] = 0;
        }
        return this.mask[row][column];
    }
    
    /**
     * @return The int array matrix
     */

    public int[][] getSea() {
        return this.array;
    }
    
    /**
     * @return The String array matrix
     */

    public String[][] getMaskedSea() {
        return this.mask;
    }

    /**
     * Checks whether the array matrix cell is of value 0.
     * @param row Index for row
     * @param column Index for column
     * @return True if area is empty, false if not
     */
    
    public boolean isAreaEmpty(int row, int column) {
        return this.array[row][column] == 0;
    }

    /**
     * Sets array as a new empty int array by calling @see createSea().
     */
    
    public void clearTheSea() {
        this.array = createSea(this.size);
    }
    
    /**
     * Checks whether the whole array is filled with zeros.
     * <p>Goes through array with two nested for-loops and compares each value at each point to zero.</p>
     * @return False if any value is other than 0, true otherwise
     */

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
    
    /**
     * Adds the given value to the array at the specified index.
     * @param row Index for row
     * @param column Index for column
     * @param ship Numerical value of the ship
     */

    public void addShipToTheSea(int row, int column, int ship) {
        this.array[row][column] = ship;
    }
    
    /**
     * @return The dimension of the Sea
     */

    public int getSeaSize() {
        return this.size;
    }

    /**
     * Overrides the equals method in class Object.
     * <p>First compares the id's of the two objects,<br>
     * then compares the classes of the two objects,<br>
     * then compares the size of the objects, and finally<br>
     * returns whether both arrays and masks are equal by comparing them with deepEquals.</p>
     * @param compared Object that is compared to the representative of this class
     * @return True if same, false otherwise
     */
    
    @Override
    public boolean equals(Object compared) {
        if (compared == this) {
            return true;
        }

        if (compared.getClass() != this.getClass()) {
            return false;
        }

        Sea compSea = (Sea) compared;

        if (compSea.size != this.size) {
            return false;
        }
        
        return Arrays.deepEquals(this.array, compSea.getSea())
                && Arrays.deepEquals(this.mask, compSea.getMaskedSea());
    }

}
