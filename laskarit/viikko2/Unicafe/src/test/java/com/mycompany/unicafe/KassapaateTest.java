/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author iiro
 */
public class KassapaateTest {

   
    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void kassaRahatJaLounaat() {
        assertEquals(kassa.kassassaRahaa(), 100000);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void maksuOnnistuuJosRahaaTarpeeksiEdullinen() {
        assertEquals(kassa.syoEdullisesti(1000), 760);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
        assertEquals(kassa.kassassaRahaa(), 100240);
    }

    @Test
    public void maksuOnnistuuJosRahaaTarpeeksiMaukas() {
        assertEquals(kassa.syoMaukkaasti(1000), 600);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 1);
        assertEquals(kassa.kassassaRahaa(), 100400);
    }

    @Test
    public void maksuEiOnnistuJosRahatEiRiita() {
        assertEquals(kassa.syoEdullisesti(200), 200);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);
        assertEquals(kassa.kassassaRahaa(), 100000);

        assertEquals(kassa.syoMaukkaasti(200), 200);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }

    @Test
    public void korttiOstoToimiiJosTarpeeksiRahaaEdullinen() {
        assertEquals(kassa.syoEdullisesti(kortti), true);
        assertEquals(kortti.saldo(), 760);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }

    @Test
    public void korttiOstoToimiiJosTarpeeksiRahaaMaukas() {
        assertEquals(kassa.syoMaukkaasti(kortti), true);
        assertEquals(kortti.saldo(), 600);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 1);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }

    @Test
    public void korttiMaksuEiOnnistuJosEiRahaa() {
        kortti.otaRahaa(800);

        assertEquals(kassa.syoEdullisesti(kortti), false);
        assertEquals(kortti.saldo(), 200);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 0);

        assertEquals(kassa.syoMaukkaasti(kortti), false);
        assertEquals(kortti.saldo(), 200);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void kortilleLataaminenToimii() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(kortti.saldo(), 2000);
        assertEquals(kassa.kassassaRahaa(), 101000);

        kassa.lataaRahaaKortille(kortti, -1);
        assertEquals(kortti.saldo(), 2000);
        assertEquals(kassa.kassassaRahaa(), 101000);
    }
}