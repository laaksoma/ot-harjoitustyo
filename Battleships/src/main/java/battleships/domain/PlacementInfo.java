package battleships.domain;

public class PlacementInfo {
    int row;
    int column;
    String direction;

    public PlacementInfo(int r, int c, String d) {
        this.row = r;
        this.column = c;
        this.direction = d;
    }

    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }
    
    public String getDirection() {
        return this.direction;
    }
}
