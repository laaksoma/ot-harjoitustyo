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

public class UserInterfaceTest {

    private ByteArrayOutputStream contentOutput = new ByteArrayOutputStream();
    private PrintStream originalOutput = System.out;
    private String input;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(contentOutput));
    }

    @After
    public void restoreDefaultStreams() {
        System.out.flush();
        System.setOut(originalOutput);
    }

    @Test
    public void welcomePrintsCorrectly() {
        UserInterface.welcome();

        assertTrue(contentOutput.toString().contains("Welcome to the Battleships game!"));
        assertTrue(contentOutput.toString().contains("Would you like to play alone or with a friend?"));
    }

    @Test
    public void getInputReturnsCorrectlyWhenInputIsOne() {
        input = "1";
        UserInterface UI = new UserInterface(new Scanner(input));

        assertEquals(UI.getInput(), 1);
    }
    
    @Test
    public void getInputReturnsCorrectlyWhenInputIsZero() {
        input = "0";
        UserInterface UI = new UserInterface(new Scanner(input));

        assertEquals(UI.getInput(), 0);
    }
    
    @Test
    public void getInputDoesNotCrashWhenGivenANonNumber() {
        input = "k";
        UserInterface UIk = new UserInterface(new Scanner(input));
        
        input = "f";
        UserInterface UIf = new UserInterface(new Scanner(input));
        
        input = "1";
        UserInterface UI1 = new UserInterface(new Scanner(input));
        
        assertEquals(UI1.getInput(), 1);
    }

}
