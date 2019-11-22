package battleships;

import battleships.domain.PlacementInfo;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlacementInfoTest {

    PlacementInfo info;

    @Before
    public void createPlacementInfo() {
        info = new PlacementInfo(1, 1, "w");
    }

    @Test
    public void constructorSetsRowCorrectly() {
        assertEquals(1, info.getRow());
    }

    @Test
    public void constructorSetsColumnCorrectly() {
        assertEquals(1, info.getColumn());
    }

    @Test
    public void constructorSetsDirectionCorrectly() {
        assertEquals("w", info.getDirection());
    }

    @Test
    public void setRowSetsRowCorrectly() {
        info.setRow(2);

        assertEquals(2, info.getRow());
    }

    @Test
    public void setColumnSetsColumnCorrectly() {
        info.setColumn(2);

        assertEquals(2, info.getColumn());
    }

    @Test
    public void setDirectionSetsDirectionCorrectly() {
        info.setDirection("s");

        assertEquals("s", info.getDirection());
    }
}
