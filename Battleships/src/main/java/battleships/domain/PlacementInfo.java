package battleships.domain;

/**
 * Class that holds information of a set of coordinates containing row, column and direction.
 */

public class PlacementInfo {
    int row;
    int column;
    String direction;
    
    /**
     * Creates a new instance of PlacementInfo with the given row, column and direction.
     * @param r Row 
     * @param c Column
     * @param d Direction
     */

    public PlacementInfo(int r, int c, String d) {
        this.row = r;
        this.column = c;
        this.direction = d;
    }

    /**
     * Creates a new instance of PlacementInfo by calling {@link #PlacementInfo(int, int, String)}
     * with parameters row as 0, column as 0 and direction as null.
     */
    
    public PlacementInfo() {
        this(0, 0, null);
    }

    /**
     * @param row The int value to be set as row within the method
     */
    
    public void setRow(int row) {
        this.row = row;
    }
    
    /**
     * @param column The int value to be set as column within the method
     */

    public void setColumn(int column) {
        this.column = column;
    }
    
    /**
     * @param direction The String value to be set as direction within the method
     */

    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return The row of the instance
     */
    
    public int getRow() {
        return this.row;
    }

    /**
     * @return The column of the instance
     */
    
    public int getColumn() {
        return this.column;
    }
    
    /**
     * @return The direction of the instance
     */
    
    public String getDirection() {
        return this.direction;
    }
}
