package battleships;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    UserInterface UI = new UserInterface(new Scanner(System.in));
    private ByteArrayOutputStream contentOutput = new ByteArrayOutputStream();
    private PrintStream originalOutput = System.out;
    private String input;
    private Game game;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(contentOutput));
        game = new Game();
    }

    @After
    public void restoreDefaultStreams() {
        System.out.flush();
        System.setOut(originalOutput);
    }

//    @Test
//    public void startPrintsCorrectlyWhenPlayingAlone() {
//        input = "0";
//        UserInterface UI = new UserInterface(new Scanner(input));
//        game.start();
//        
//        assertTrue(contentOutput.toString().contains("Cannot play with an AI player at the given time."));
//    }
//    
//    @Test
//    public void startReturnsFalseWhenPlayingAlone() {
//        //assertFalse();
//    }

}
