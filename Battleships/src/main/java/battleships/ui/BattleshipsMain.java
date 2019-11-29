package battleships.ui;

import battleships.domain.Game;

public class BattleshipsMain {

    public static void main(String[] args) throws Exception {
//        GraphicalUserInterface gui = new GraphicalUserInterface();
//        gui.startGUI();

        Game battleships = Game.getInstance();

        battleships.beginStartMethod();
        
 //       battleships.createBoard(2);

 //       battleships.playGame();
    }
}
