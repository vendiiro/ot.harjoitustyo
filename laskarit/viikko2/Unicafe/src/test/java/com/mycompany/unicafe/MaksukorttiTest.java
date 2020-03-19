package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void saldoOikein() {
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void lataaminenOikein() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }

    @Test
    public void vaheneminenOikein() {
        kortti.otaRahaa(5);
        assertEquals("Kortilla on rahaa 5.0 euroa", kortti.toString());

    }

    @Test
    public void josRahaaEiTarpeeksi() {
        kortti.otaRahaa(10);
        kortti.otaRahaa(3);
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }
    @Test 
    public void riittaaRahat(){
        assertTrue(kortti.otaRahaa(2));
    }
    @Test
    public void eiRiitaRahat(){
        assertFalse(kortti.otaRahaa(20));
    }

}
