package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertTrue(1000 == kortti.saldo());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        assertTrue(2000 == kortti.saldo());
    }
    
    @Test
    public void rahaaTarpeeksiSaldoVaheneeOikein() {
        kortti.otaRahaa(420);
        assertTrue(580 == kortti.saldo());
    }
    
    @Test
    public void josEiRahaaSaldoEiMuutu() {
        kortti.otaRahaa(2000);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void katsotaanOikeinKunRahaEiRiita() {
        assertFalse(kortti.otaRahaa(2000));
    }
    
    @Test
    public void katsotaanOikeinKunRahaRiittaa() {
        assertTrue(kortti.otaRahaa(900));
    }
}
