package battleships.domain;

import java.util.ArrayList;
import java.util.Random;

public class BotPlayer extends Player {

    final ArrayList<String> listOfDirections;
    final int listSize;         
    ArrayList<String> listCopy;
    Random random;

    public BotPlayer() {
        super("Bot");
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
        direction = listCopy.get(i);

        return direction;
    }

    public int getRowOrColumn(int min, int max) {
        return this.random.nextInt(max) + 1;
    }

    @Override
    public PlacementInfo decideCoordinates(int ship, boolean needForDirection) {
        int row = getRowOrColumn(1, Game.getGameBoardSize());
        int column = getRowOrColumn(1, Game.getGameBoardSize());
        String dir = null;
        
        if (needForDirection) {
            dir = getDirection();
        }

        return new PlacementInfo(row, column, dir);
    }

}
