# Arkkitehtuurikuvaus
. . . 
## Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria, jonka koodin pakkausrakenne on seuraava:

![Image of Yaktocat](https://raw.githubusercontent.com/vendiiro/ot.harjoitustyo/master/dokumentaatio/kuvat/Untitled%20Diagram.png)

Pakkaus paivakirja.ui sisältää tekstikäyttöliittymänä toteutetun käyttöliittymän, paivakirja.domain sovelluslogiikan ja paivakirja.dao sisältää koodin, joka vastaa tietojen pysyväistalletuksesta.

## Sovelluslogiikka
Sovelluksen loogisen datamallin muodostavat luokat User ja Note, jotka kuvaavat käyttäjiä ja käyttäjien muistiinpanoja:
![Image of Yaktocat](https://yuml.me/e220d1f7.png)

NoteServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:
![Image of Yaktocat](https://raw.githubusercontent.com/vendiiro/ot.harjoitustyo/master/dokumentaatio/kuvat/pakkauskaavio.png)

## Päätoiminnallisuudet
Kuvaillaan seuraavaksi sovelluksen toimintalogiikkaa, muutaman päätoiminnallisuuden oasalta, hyödyntäen viikolla 3 opittuja sekvenssikaavioita.

**Käyttäjän sisäänkirjautuminen**

Kun käyttäjä syöttää tekstinkäyttöliittymään login-toiminnallisuutta vastaavan komennon 1 ja painaa enter, etenee sovelluksen kontrolli seuraavan sekvenssikaavon kuvaamalla tavalla:
![kuva](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/kuvat/K%C3%A4ytt%C3%A4j%C3%A4n%20kirjaantuminen(1).png)

Kun käyttäjä on syöttänyt komennon 1, tekstikäyttöliittymä tarkastaa NoteService:n tarjoamalla isUserLoggedIn-metodialla, että järjestelmässä ei ole ketään kirjautuneena sisään. Jos ketään ei ole kirjautuneena sisään niin, tekstikäyttöliittymän login-metodi kutsuu NoteService-olion login-metodia antaen sille parametriksi käyttäjän antaman käyttäjänimen.

NoteService-olio kutsuu DaoUser:in metodia getUsingUsername, antaen sille parametriksi äsken käyttöliittymästä saadun käyttäjänimen. Jos käyttäjänimeä vastaava käyttäjä on olemassa, UserDao antaa sen paluuarvona NoteService:lle. Tämän jälkeen NoteService asetettaa kyseisen käyttäjän kirjautuneeksi sisään, ja palauttaa tekstinkäyttöliittymään arvon true.

**Uuden päivittäisen muistiinpanon luominen**

Kun käyttäjä syöttää tekstinkäyttöliittymään create new note-toiminnallisuutta vastaavan komennon 3 ja painaa enter, etenee sovelluksen kontrolli seuraavasti:
![kuva](https://github.com/vendiiro/ot.harjoitustyo/blob/master/dokumentaatio/kuvat/Uuden%20p%C3%A4iv%C3%A4kohtaisen%20muistiinpanon%20luominen(2).png)
Aluksi tekstikäyttöliittymä tarkastaa NoteService:n isUserLoggedIn-metodia kutsumalla, että syötteen 3 antanut käyttäjä on  kirjautunut sisään järjestelmään. Mikäli hän on niin, tekstikäyttöliittymän createNote-metodi kutsuu NoteService-olion createNote-metodia, antaen sille parametriksi käyttäjän antamat tiedot: päivämäärän, treenin pituuden, ja käyttäjän muistiinpanot treenistä . NoteService-oliolla on tieto siitä, kuka on sovelluksen tämän hetkinen sisäänkirjautunut käyttäjä.

NoteService-olion createNote-metodi kutsuu DaoNote:n metodia create ja antaa tälle parametriksi kaikki käyttäjän syöttämät tiedot, jotka se itse äsken sai käyttöliittymästä. Tämän lisäksi se myös saa tiedon tämän hetkisestä käyttäjästä. DaoNote luo näitä tietoja vastaavan muistiinpanon (Note) tietokantaan, ja palauttaa muistiinpanon NoteService-oliolle. Mikäli tapahtuma onnistuu, lähettää NoteService paluuarvona sitä kutsuneelle tekstinkäyttöliittymän metodille arvon “true”.
