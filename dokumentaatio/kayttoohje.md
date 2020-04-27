# Käyttöohje

Lataa tiedosto [paivakirja.jar](https://github.com/vendiiro/ot.harjoitustyo/releases/tag/viikko6)


## Konfigurointi
Käyttäjän tulee luoda konfiguraatiotiedosto config.properties, joka määrittelee käytettävissä olevan tietokannan nimen.  Tiedoston muoto on seuraava:

`databaseAddress=jdbc:sqlite:tietokanta.db`

## Ohjelman käynnistäminen
Ohjelma käynnistetään komentoriviltä komennolla

`java -jar target/Paivakirja-1.0-SNAPSHOT.jar`


**Uuden käyttäjän luominen**

Sovellus aukeaa näkymään, jossa on annettu kaikki ohjelman tarjoamat toiminnot. Uusi käyttäjä luodaan syöttämällä tekstikenttän 1 ja klikkaamalla enter painiketta. Tämän jälkeen käyttäjän tulee antaa oma nimensä ja jokin uniikki käyttäjä nimi. Näiden kohtien jälkeen voidaan siirtyä käyttäjän sisään kirjautumiseen

**Kirjautuminen järjestelmään**

Jos käyttäjä on jo kertaalleen luonut käyttäjätunnuksen, yllä olevan esimerkin mukaisesti, voidaan nyt onnistuneesti kirjauta järjestelmään. Jos kättäjä seuraa ohjeita, tulee hänen seuraavaksi syöttää 2, jonka jälkeen hänen tulee syöttää keksimänsä uniikki käyttäjänimi. Onnistuessaan tässä järjestelmä kertoo käyttäjän sisäänkirjautumisesta. 

**Uuden muistiinpanon kirjaaminen**

Uusi muistiinpano luodaan syöttämällä komento 3, ja syöttämällä rajoitusten mukaiset tiedot kuhunkin käyttöliittymän kysymään kohtaan: päivämäärä (halutussa muodossa) , treeniin kulutettu aika = x (10<x<240), sekä muut huomiot tai muistiinpanot treenistä. Jokaisen kohdan jälkeen käyttäjän tulee painaa enter-painiketta.


