package battleships.ui;

import battleships.domain.Game;

/**
 * The MainClass for Battleships.
 */
public class BattleshipsMain {

    /**
     * Connects to database, creates the {@link Game} instance and calls for
     * {@link Game#beginStartMethod()}.
     *
     * @param args Array including the given parameters
     * @throws Exception In case of {@link Game} instance Singleton exception.
     */
    public static void main(String[] args) throws Exception {
        if(args.length > 0 && args[0].equals("--text")){
            UserInterface gameUserInterface = new GraphicalUserInterface().getInstance();
        } else {
            UserInterface gameUserInterface = new TextUserInterface().getInstance();
        }      
        
        Game battleships = Game.getInstance();
        battleships.beginStartMethod();
    }
}
