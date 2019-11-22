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

    public PlacementInfo() {
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setDirection(String direction) {
        this.direction = direction;
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
