package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    int ALKUSALDO = 1000;

    @Before
    public void setUp() {
        kortti = new Maksukortti(ALKUSALDO);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoOikein() {
        assertEquals("saldo: 10.0", kortti.toString());

    }

    @Test
    public void lataaminenOikein() {
        kortti.lataaRahaa(2500);
        assertEquals("saldo: 35.0", kortti.toString());
    }

    @Test
    public void vaheneminenOikein() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }

    @Test
    public void josRahaaEiTarpeeksi() {
        kortti.otaRahaa(1000);
        kortti.otaRahaa(30);
        assertEquals("saldo: 0.0", kortti.toString());
    }

    @Test
    public void riittaaRahat() {
        assertTrue(kortti.otaRahaa(200));
    }

    @Test
    public void eiRiitaRahat() {
        assertFalse(kortti.otaRahaa(2000));
    }

    @Test
    public void saldoAlussaOikein() {
        assertEquals(ALKUSALDO, kortti.saldo());
    }
}
