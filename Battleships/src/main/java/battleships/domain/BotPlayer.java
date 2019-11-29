package battleships.domain;

import java.util.ArrayList;
import java.util.Random;

public class BotPlayer extends Player {

    final ArrayList<String> listOfDirections;
    final int listSize;         
    ArrayList<String> listCopy;
    Random random;

    public BotPlayer() {
        super("Bot /84");
        this.listOfDirections = createDirections();
        this.listSize = listOfDirections.size();
        this.listCopy = listOfDirections;
        this.random = new Random();
    }

    private ArrayList<String> createDirections() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("w");
        list.add("a");
        list.add("s");
        list.add("d");

        return list;
    }

    public String getDirection() {
        String direction;

        int i = this.random.nextInt(this.listSize);
        direction = listCopy.get(i);                                            //copy for the better bot, delete if not in use later

        return direction;
    }

    public int getRowOrColumn(int max) {
        int number = this.random.nextInt(max);
        //System.out.println("Number was: " + number);
        return number;
    }
    
    @Override
    public PlacementInfo decideCoordinates(int ship, boolean needForDirection, int gameSize) {
        int row = getRowOrColumn(gameSize);
        int column = getRowOrColumn(gameSize);
        String dir = null;
        
        if (needForDirection) {
            dir = getDirection();
        } 
        //System.out.println("Bot chose row " + (row + 1) + ", column " + (column + 1) + ", and direction was " + dir);
        
        return new PlacementInfo(row, column, dir);
    }

}
