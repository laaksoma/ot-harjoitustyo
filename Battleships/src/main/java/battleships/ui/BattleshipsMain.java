package battleships.ui;

import battleships.domain.Game;

public class BattleshipsMain {

    public static void main(String[] args) throws Exception {
        Game battleships = new Game();

        battleships.start();
        
        battleships.createBoard();

        battleships.playGame();
    }
}
