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

        int i = ThreadLocalRandom.current().nextInt(1, this.listSize) - 1;
        direction = listCopy.get(i);

        return direction;
    }

    public int getRowOrColumn(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
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
