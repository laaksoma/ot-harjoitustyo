package battleships.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SeaTest {

    Sea sea;

    @Before
    public void setUp() {
        this.sea = new Sea(5);
    }

    @Test
    public void createSeaCreatesArrayCorrectly() {
        int[][] array = new int[5][5];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = 0;
            }
        }

        Assert.assertArrayEquals(array, this.sea.getSea());
    }

    @Test
    public void createMaskedSeaCreatesArrayCorrectly() {
        String[][] array = new String[5][5];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = " -";
            }
        }

        Assert.assertArrayEquals(array, this.sea.getMaskedSea());
    }

//    @Test
//    public void modifySeaModifiesSeaCorrectly() {
//        this.sea.addShipToTheSea(1, 1, 2);
//
//        assertEquals(-2, this.sea.modifySea(1, 1));
//    }

    @Test
    public void modifyMaskedSeaModifiesCorrectlyWhenValueIsZero() {
        assertEquals(" O", this.sea.modifyMaskedSea(1, 1, 0));
    }

    @Test
    public void modifyMaskedSeaModifiesMaskedSeaCorrectlyWhenValueIsNotZero() {
        assertEquals(" X", this.sea.modifyMaskedSea(1, 1, 1));
    }

    @Test
    public void modifyMaskedSeaModifiesSeaAreaCorrectlyWhenValueIsNotZero() {
        this.sea.modifyMaskedSea(1, 1, 1);

        assertEquals(0, this.sea.getSea()[1][1]);
    }

    @Test
    public void getSeaGetsArrayCorrectly() {
        int[][] expected = new int[5][5];
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                expected[i][j] = 0;
            }
        }

        Assert.assertArrayEquals(expected, this.sea.getSea());
    }

    @Test
    public void getMaskedSeaGetsArrayCorrectly() {
        String[][] expected = new String[5][5];
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                expected[i][j] = " -";
            }
        }

        Assert.assertArrayEquals(expected, this.sea.getMaskedSea());
    }

    @Test
    public void isAreaEmptyReturnsTrueWhenAreaIsEmpty() {
        assertTrue(this.sea.isAreaEmpty(1, 1));
    }

    @Test
    public void isAreaEmptyReturnsFalseWhenAreaIsNotEmpty() {
        this.sea.addShipToTheSea(1, 1, 3);
        assertFalse(this.sea.isAreaEmpty(1, 1));
    }

    @Test
    public void clearTheSeaCreatesAnArrayFilledWithZeros() {
        int[][] expected = new int[5][5];
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[0].length; j++) {
                expected[i][j] = 0;
            }
        }

        this.sea.addShipToTheSea(1, 1, 2);
        this.sea.clearTheSea();

        Assert.assertArrayEquals(expected, this.sea.getSea());
    }

    @Test
    public void seaIsEmptyReturnsTrueWhenEmpty() {
        assertTrue(this.sea.seaIsEmpty());
    }

    @Test
    public void seaIsEmptyReturnsFalseWhenNotEmpty() {
        this.sea.addShipToTheSea(1, 1, 2);

        assertFalse(this.sea.seaIsEmpty());
    }

    @Test
    public void addShipToTheSeaAddsShipCorrectly() {
        this.sea.addShipToTheSea(1, 1, 3);

        assertEquals(3, this.sea.getSea()[1][1]);
    }

    @Test
    public void getSeaSizeReturnsSizeCorrectly() {
        assertEquals(5, this.sea.getSeaSize());
    }

    @Test
    public void equalsReturnsTrueIfObjectIsSame() {
        assertTrue(this.sea.getSea().equals(this.sea.getSea()));
    }

    @Test
    public void equalsReturnsFalseWhenDifferentClass() {
        assertFalse(this.sea.equals("hello"));
    }

    @Test
    public void equalsReturnsFalseWhenSeaArrayIsDifferent() {
        Sea compared = new Sea(5);
        compared.addShipToTheSea(1, 1, 1);
        assertFalse(this.sea.equals(compared));
    }

    @Test
    public void equalsReturnsFalseWhenSizeDoesNotMatch() {
        Sea compared = new Sea(2);
        assertFalse(this.sea.equals(compared));
    }

    @Test
    public void equalsReturnsTrueWhenArrayMaskAndSizeMatch() {
        Sea copy = this.sea;

        assertTrue(this.sea.equals(copy));
    }

//    @Test
//    public void equalsReturnsFalseWhenArraysMatchButMasksDiffer() {
//        //to make this.array==array, and this.mask!=mask
//        //one needs to modify mask with number other than 0, 
//        //then modify array back
//
//        Sea copy = this.sea;
//        copy.modifyMaskedSea(1, 1, 1);
//        copy.modifySea(1, 1);
//        assertEquals(false, this.sea.equals(copy));
//    }
}
