# Ohjelmistotekniikan harjoitustyö

**Treenipäivis-sovellus**

**Sovelluksen nimi pending**


# Harjoitustyö
Ohjelmalla pystyy pitämään kirjaa treenaamiseen käyttämäänsä aikaa ja kirjaamaan treenissä sattuvia muistiinpanoja kuten loukkaantumisia treeni muotoja yms.

# Dokumentaatio
[Työaikakirjanpito](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/arkkitehtuurikuvaus.md)

[Testausdokumentti](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Käyttöohje](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

 # Komentorivikomennot
 **Svelluksen ajaminen komentoriviltä**
 - Voit suorittaa sovelluksen ajamisen komennolla 
    - mvn compile exec:java -Dexec.mainClass=paivakirja.Main
 
**Testaus**

- suorita testit komennolla
   - mvn test
- suorita testikattavuus raportti komennolla 
     - mvn jacoco:report
     
Raportti löytyy kansiosta 
- target/site/jacoco/index.html 
     
**Checkstyle-raportti**

Tiedostoon [checkstyle.xml](https://github.com/vendiiro/ot.harjoitustyo/blob/master/Paivakirja/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla
 -  mvn jxr:jxr checkstyle:checkstyle
 
 Raportti löytyy kansiosta
  - target/site/checkstyle.html

**Suoritettavan jarin generointi**

- Komento 
   - mvn package
 
generoi hakemistoon target suoritettavan jar-tiedoston Paivakirja-1.0-SNAPSHOT.jar
