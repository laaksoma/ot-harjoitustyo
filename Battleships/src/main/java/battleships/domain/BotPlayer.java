package battleships.domain;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BotPlayer extends Player {

    final ArrayList<String> listOfDirections;
    final int listSize;
    ArrayList<String> listCopy;

    public BotPlayer() {
        super("Bot");
        this.listOfDirections = createDirections();
        this.listSize = listOfDirections.size();
        this.listCopy = listOfDirections;
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

        //BELOW THIS NEEDS TO BE IN A LOOP?
        int i = ThreadLocalRandom.current().nextInt(1, this.listSize) - 1;
        direction = listCopy.get(i);

        return direction;
    }
    
    public int getRowOrColumn(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
    
    
    //use isPlacementAllowed from Game
        //Game.getInstance().isPlacementAllowed(row, column, sea, ship, dir);

    //getDirection
    //get's randomly one direction from listOfDirections
    //remove chosen from the listCopy, so it cannot be chosen again with this iteration
    //getCoordinates
    //gives row and column coordinates
    //get's row and column; both are random's between 1 and 5
    //if misses, keeps guessing randomly
    //if hits
    //tries one below, above or sideways
    //if hit's again, goes in the same line above/below or sideways
    //if w/s was correct, change only row
    //if a/d was correct, change only column
}
