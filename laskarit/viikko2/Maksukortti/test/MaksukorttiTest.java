
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {

    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10.0);
    }
    
    @Test
    public void konstruktoriAsettaaSaldonOikein() {        
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kortti.syoEdullisesti();
        
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kortti.syoMaukkaasti();
        
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }
    
    @Test 
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
        Maksukortti kortti2 = new Maksukortti(2.0);
        
        kortti2.syoEdullisesti();
        
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti2.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200.0);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }
    
    @Test
    public void negatiivisenSummanLataaminenEiMuutaSaldoa() {
        kortti.lataaRahaa(-3.0);
        
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
}


