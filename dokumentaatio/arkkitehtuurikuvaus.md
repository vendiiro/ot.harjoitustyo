# Arkkitehtuurikuvaus
. . . 
# Rakenne

Ohjelman rakenne noudattaa kolmitasoista kerrosarkkitehtuuria, jonka koodin pakkausrakenne on seuraava:

![Image of Yaktocat](https://raw.githubusercontent.com/vendiiro/ot.harjoitustyo/master/dokumentaatio/kuvat/Untitled%20Diagram.png)

Pakkaus paivakirja.ui sisältää tekstikäyttöliittymänä toteutetun käyttöliittymän, paivakirja.domain sovelluslogiikan ja paivakirja.dao sisältää koodin, joka vastaa tietojen pysyväistalletuksesta.

# Sovelluslogiikka
Sovelluksen loogisen datamallin muodostavat luokat User ja Note, jotka kuvaavat käyttäjiä ja käyttäjien muistiinpanoja:
![Image of Yaktocat](https://yuml.me/e220d1f7.png)

NoteServicen ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:
![Image of Yaktocat](https://raw.githubusercontent.com/vendiiro/ot.harjoitustyo/master/dokumentaatio/kuvat/pakkauskaavio.png)
