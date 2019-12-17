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
     * @param args The default parameter for Main
     * @throws Exception In case of {@link Game} instance Singleton exception.
     */
    public static void main(String[] args) throws Exception {
        UserInterface gameUserInterface = new GraphicalUserInterface().getInstance();
        Game battleships = Game.getInstance();
        battleships.beginStartMethod();
    }
}
