# Testausdokumentti
Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla kuin myös manuaalisesti tapahtuvien järjestelmätason testien avulla.

## Yksikkö- ja integraatiotestaus

**Sovelluslogiikka**

Sovelluslogiikan toteuttavat luokat sijaitsevat pakkauksessa [paivakirja.domain](https://github.com/vendiiro/ot.harjoitustyo/tree/master/Paivakirja/src/main/java/paivakirja/domain). Näitä luokkia on testattu yksikkötesteillä [NoteServiceUserTest](https://github.com/vendiiro/ot.harjoitustyo/blob/master/Paivakirja/src/test/java/domain/NoteServiceUserTest.java) ja [NoteServiceNoteTest](https://github.com/vendiiro/ot.harjoitustyo/blob/master/Paivakirja/src/test/java/domain/NoteServiceNoteTest.java). Näissä luokissa on hyödynnetty [Mockito](https://site.mockito.org/)-valekomponentteja. Kun tetstejä tarkastellaan tästä näkökulmasta niin DAO-luokkien tekemien tietokantahakujen tilalla on ns. Mock-olioita, jolloin näiden olioiden paluuarvot voidaan määritellä jokaiseen testiin sopiviksi. Esimerkki Mock-oliosta: oletetaan että userDao:n metodia x kutsutaan arvolla y, niin annetaan paluuarvona z.

Integraatiotesti [NoteServiceTest](https://github.com/vendiiro/ot.harjoitustyo/blob/master/Paivakirja/src/test/java/domain/NoteServiceTest.java) käyttää hyväksi keskusmuistiin tallennettavaa testitietokantaa. Sovelluslogiikan testitapaukset simuloivat toiminnallisuuksia, joita käyttöliittymä suorittaa NoteService-olion avulla.

Luokille User ja Note ei ole tehty erikseen montaa omaa testiä (equals- metodit vain testattu), koska ne sisältävät vain yksinkertaisia gettereitä ja settereitä.

**DAO-luokat**

Molemmat DAO-luokat on testattu luomalla keskusmuistiin tallennettava testitietokanta.

**Testauskattavuus**

Sovelluksen testauksen rivikattavuus on 87% ja haaraumakattavuus 75%. Käyttöliittymää ei sisällytetty testeihin, kurssin ohjeiden mukaisesti.
![kuva](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/kuvat/Kuvakaappaus%20-%202020-05-08%2011-06-03.png)

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

**Asennus ja konfigurointi**

Sovellusta on testattu sekä OSX-että Linux-ympäristössä. Testaus on tapahtunut suorittamalla joko jar-tiedosto että suoraan githubista kloonatussa repositoriossa olevaa sovellusta. Jar-tiedoston tapauksessa asentaminen ja testaus on tapahtunut käyttöohjeen mukaisesti

Sovellusta on testattu niin että [config.properties](https://github.com/vendiiro/ot.harjoitustyo/blob/master/Paivakirja/config.properties)-tiedostossa kuvattu tietokantatiedosto on ollut jo luotuna, sekä niin ettei tiedostoa ole vielä ole luotu, jolloin ohjelma on luonut sen itse.

**Toiminnallisuudet**

Kaikki [määrittelydokumentin](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja [käyttöohjeen](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) listaamat toiminnallisuudet on testattu manuaalisesti. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä kentillä tai kirjaimilla numeroita vaatineissa kentissä.



