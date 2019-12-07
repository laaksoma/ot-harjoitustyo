//package battleships.domain;
//
//import battleships.ui.TestUserInterface;
//import battleships.ui.UserInterface;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.Scanner;
//import org.junit.AfterClass;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class HumanPlayerTest {
//
//    private ByteArrayOutputStream contentOutput = new ByteArrayOutputStream();
//    private static PrintStream originalOutput = System.out;
//    private String input;
//    private HumanPlayer human;
//    private static TestUserInterface u = new TestUserInterface();
//
//    @BeforeClass
//    public static void createUserInterfaceForTests() throws Exception {
//        u = (TestUserInterface)TestUserInterface.getInstance();
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        System.setOut(new PrintStream(contentOutput));
//        this.input = "\n";
//        human = new HumanPlayer("TestName");
//        u.setAnnotations();
//        //call setAnnotations here for TestUserInterface!
//    }
//
//    //SetUp
//    public void setUpScannerForUserInterface(String input) {
//        u.setUpScanner(new Scanner(input));
//    }
//
//    @AfterClass
//    public static void restoreDefaultStreams() {
//        System.out.flush();
//        System.setOut(originalOutput);
//    }
//    
//    @Test
//    public void decideCoordinatesDoesNotCrashWhenShipIsZero() {
//        //setUpScannerForUserInterface("1\n2\n");
//        
//        assertEquals(0, this.human.decideCoordinates(0, false, 5).getRow());
//    }
//
//    @Test
//    public void decideCoordinatesSetsRowCorrectly() {
//        //setUpScannerForUserInterface("1\n2\n");
//
//        assertEquals(0, this.human.decideCoordinates(2, false, 5).getRow());
//    }
//
//    @Test
//    public void decideCoordinatesSetsColumnCorrectly() throws Exception {
//        //setUpScannerForUserInterface("1\n2\n");
//
//        assertEquals(1, this.human.decideCoordinates(2, false, 5).getColumn());
//    }
//
//    @Test
//    public void decideCoordinatesGetsDirectionWhenItIsNeeded() {
//        //setUpScannerForUserInterface("1\n1\nd\n");
//
//        assertTrue("d".contains(this.human.decideCoordinates(2, true, 5).getDirection()));
//    }
//
//}
