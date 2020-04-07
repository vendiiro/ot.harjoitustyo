# Ohjelmistotekniikan harjoitustyö

**Treenipäivis-sovellus**

**Sovelluksen nimi pending**


# Harjoitustyö
Ohjelmalla pystyy pitämään kirjaa treenaamiseen käyttämäänsä aikaa ja kirjaamaan treenissä sattuvia muistiinpanoja kuten loukkaantumisia treeni muotoja yms.

# Dokumentaatio
[vaatimusmaarittely.md](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[tuntikirjanpito.md](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[arkkitehtuuri.md](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/arkkitehtuurikuvaus.md)

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
     - mvn jxr:jxr checkstyle:checkstyle, tosin tätä en oikein saanut toimimaan vaikka on omasta milestä aika oikean näköinen.
- Raportti löytyy kansiosta
     - target/site/checkstyle.html, jos sen olisi saaatu tehtyä

