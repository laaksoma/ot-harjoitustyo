package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    public void kortiltaOtetaanRahaa() {
        kortti.otaRahaa(999);
    }

    //Testien yksinkertaisuuden tähden valitsin laittaa
    //samaa funktionaalisuutta eri metodeilla testaavat testit
    //poikkeuksellisesti samaan metodiin toiston välttämiseksi
    //ja selkeyden lisäämiseksi.
    @Test
    public void luodunKassanRahaOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void myytyjenLounaidenMaaraOikein() {
        assertTrue(0 == kassa.edullisiaLounaitaMyyty());

        setUp();

        assertTrue(0 == kassa.maukkaitaLounaitaMyyty());
    }

    //käteismaksutestit
    @Test
    public void kateismaksunVaihtorahaOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));

        setUp();

        assertEquals(100, kassa.syoMaukkaasti(500));
    }

    @Test
    public void kateismaksuMuuttaaKassanSaldoaOikein() {
        kassa.syoEdullisesti(240);
        assertEquals(100240, kassa.kassassaRahaa());

        setUp();

        kassa.syoMaukkaasti(400);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void kateismaksuLisaaLounaisiinOikein() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());

        setUp();

        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void liianPieniKateismaksuPalauttaaMaksun() {
        assertEquals(10, kassa.syoEdullisesti(10));

        setUp();

        assertEquals(10, kassa.syoMaukkaasti(10));
    }

    @Test
    public void liianPieniKateismaksuEiMuutaKassanRahamaaraa() {
        kassa.syoEdullisesti(10);
        assertEquals(100000, kassa.kassassaRahaa());

        setUp();

        kassa.syoMaukkaasti(10);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void liianPieniKateismaksuEiMuutaMyytyjaLounaita() {
        kassa.syoEdullisesti(10);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());

        setUp();

        kassa.syoMaukkaasti(10);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    //korttimaksutestit
    @Test
    public void kortillaTarpeeksiVeloitusOikein() {
        kassa.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());

        setUp();

        kassa.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
    }

    @Test
    public void kortillaTarpeeksiPalauttaaTrue() {
        assertTrue(kassa.syoEdullisesti(kortti));

        setUp();

        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void kortillaTarpeeksiMyytyjenLounaidenMaaraKasvaaOikein() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());

        setUp();

        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kortillaEiTarpeeksiKortinSaldoEiMuutu() {
        kortiltaOtetaanRahaa();
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kortti.saldo());

        setUp();

        kortiltaOtetaanRahaa();
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kortti.saldo());
    }

    @Test
    public void kortillaEiTarpeeksiMyytyjenLounaidenMaaraEiMuutu() {
        kortiltaOtetaanRahaa();
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());

        setUp();

        kortiltaOtetaanRahaa();
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kortillaEiTarpeeksiPalauttaaFalse() {
        kortiltaOtetaanRahaa();
        assertFalse(kassa.syoEdullisesti(kortti));

        setUp();

        kortiltaOtetaanRahaa();
        assertFalse(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void korttiostoEiMuutaKassanSaldoa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());

        setUp();

        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiaLadatessaKassanRahamaaraKasvaaOikein() {
        kassa.lataaRahaaKortille(kortti, 3000);
        assertEquals(103000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiaLadatessaNegatiivinenSummaEiLisaaKassaanRahaa() {
        kassa.lataaRahaaKortille(kortti, -3);
        assertEquals(100000, kassa.kassassaRahaa());
    }
}
