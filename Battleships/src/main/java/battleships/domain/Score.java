package battleships.domain;

/**
 * A class that holds information of game winner.
 */
public class Score {
    
    private String winnerName;
    private int winnerPoints;
    
    /**
     * @param name Name of the winning {@link Player}
     * @param points How many points the {@link Player} got
     */
    
    public Score(String name, int points) {
        this.winnerName = name;
        this.winnerPoints = points;
    }
    
    /**
     * @return Name of the winner
     */
    
    public String getName() {
        return this.winnerName;
    }
    
    /**
     * @return How many points the winner got
     */
    
    public int getPoints() {
        return this.winnerPoints;
    }
}
