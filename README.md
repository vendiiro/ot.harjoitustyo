# Ohjelmistotekniikan harjoitustyö

**Treenipäivis-sovellus**

**Sovelluksen nimi pending**


# Harjoitustyö
Ohjelmalla pystyy pitämään kirjaa treenaamiseen käyttämäänsä aikaa ja kirjaamaan treenissä sattuvia muistiinpanoja kuten loukkaantumisia treeni muotoja yms.

# Dokumentaatio
[Työaikakirjanpito](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Vaatimusmäärittely](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/arkkitehtuurikuvaus.md)

 # Komentorivikomennot
 **Svelluksen ajaminen komentoriviltä**
 - Voit suorittaa sovelluksen ajamisen komennolla 
    - mvn compile exec:java -Dexec.mainClass=paivakirja.Main
 
**Testaus**
- suorita testit komennolla
    - mvn test
- suorita testikattavuus raportti komennolla 
     - mvn jacoco:report
- Raportti löytyy kansiosta 
     - target/site/jacoco/index.html 
- Checkstyle-raportin voi luoda komennolla
     - mvn jxr:jxr checkstyle:checkstyle
- Raportti löytyy kansiosta
     - target/site/checkstyle.html

