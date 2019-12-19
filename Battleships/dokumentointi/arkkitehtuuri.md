# Arkkitehtuurikuvaus
## Rakenne
Ohjelma noudattaa kolmitasoista kerrosarkkitehtuuria seuraavanlaisella rakenteella: 

![Kerrosarkkitehtuuri](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/pakkauskaavio.png)

Pakkauksesta _battleships.ui_ löytyvät käyttöliittymäluokat, ja _battleships.domain_ sisältää sovelluslogiikan luokat. 
Tietojen pysyväistallennus hoidetaan _battleships.dao_-pakkauksen kautta.

## Käyttöliittymä
Graafinen käyttöliittymä sisältää kolme näkymää:
 - pelin aloitusnäkymä
 - laudan luomisen näkymä pelaajakohtaisesti
 - pelinäkymä, 
 
joista jokainen on toteutettu omana Scene-olionaan. Näkymät ovat yksi kerrallaan näkyvillä, ja näitä päivitetään asettamalla GraphicalUserInterface-luokassa aina haluttu Scene sopivan set-muotoisen metodin avulla sovelluksen Stagelle. 

## Sovelluslogiikka 
Sovelluksen looginen datamalli muodostuu luokkien Player, Sea ja PlacementInfo välille. Luokka Player edustaa abstraktia luokkaa, jolla on kaksi toteuttajaa: HumanPlayer ja BotPlayer. 

[tietokantataulukko]((https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/datamalli-tietokantataulut.png))

Toiminnallisista kokonaisuuksista vastaa luokka Game, joka vuorovaikuttaa käyttöliittymän kanssa ja hallinnoi sovelluksen loogista toimintamallia. Eri toiminnallisuuksista vastaavat eri metodit, kuten esimerkiksi 

- createBoard(int numberOfShips)
- playGame()
- gameOver(Player inTurn, Player notInTurn). 

Jotkin luokan metodeista, kuten esimerkiksi metodi _turn_, ovat itsessään hallinnollisia kokonaisuuksia, jotka vastaavat yhdestä ohjelman toiminnallisuudesta pyörittämällä muiden metodien (tässä tapauksessa esimerkiksi _getIndexForAnotherPlayer(Player playerNotWanted)_ ja _areCoordinatesAlreadyUsed(PlacementInfo info, Player otherPlayer)_) avulla tätä kokonaisuutta ja sen logiikkaa. 

![Pakkauskaavio](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/sovelluslogiikka_laajempi.png)

Käytettävissä on kolme erilaista käyttöliittymää: TestUserInterface, TextUserInterface ja GraphicalUserInterface. Luokka TestUserInterface on vain testien käytettävissä. GraphicalUserInterface on tällä hetkellä asetettu sovelluksessa oletukseksi, mutta TextUserInterface on mahdollista ottaa käyttöön parametrilla _--text_ komentorivikutsun yhteydessä. 

GraphicalUserInterface vuorovaikuttaa battleships.ui-packagen sisällä kolmen FXML-controllerin kanssa: FXMLStartController, FXMLSetUpController ja FXMLPlayGameController, joiden avulla on luotu sovelluksen eri näkymät.

## Tietojen pysyväistallennus
Ei vielä saatavilla.

## Päätoiminnallisuudet
### Pelin aloittaminen
Ei vielä saatavilla.

### Laudan luonti
Ei vielä saatavilla.

### Vuoron toiminta pelaajalla
Peliä pelataan kutsumalla luokan Game metodia _playGame_, joka arpoo aloittajan ja käynnistää ensimmäisen vuoron kutsumalla vuorostaan metodia _turn_. Vuoroja ylläpidetään while(true)-loopilla, josta päästään pois vain joko pysäyttämällä looppi pelaajan osuttua ohi tai pelin päättyessä, jolloin metodi palauttaa _playGame_-metodille boolean-arvon false. Mikäli peli on yhä käynnissä, palautetaan true, ja kutsutaan metodia _turn_ uudestaan seuraavalla pelaajalla.

![Sekvenssikaavio](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/sekvenssikaavio_vuoro.png)

## Ohjelmaan jääneet heikkoudet
Ohjelman sovelluslogiikka toimii tässä kyseisessä ohjelmassa siten, että Game tuntee käyttöliittymän ja käyttää tätä, eikä toisin päin, mikä paikoitellen tuottaa hieman ongelmia etenkin testattavuuden suhteen. 

Toinen selkeä heikkous on Singletonien käyttö - sekä luokka Game että UserInterface ovat Singleton-luokkia, mikä periytyy myös UserInterfacea edustaville luokille. Tämä vaikeuttaa testaamista, vaikka kiertääkin joitakin pelin toiminnallisia ongelmia toimien ratkaisuna niihin. Loppupeleissä vaihtokauppa ei kuitenkaan ehkä ollut paras mahdollinen. 

Ohjelman konfiguroitavuus on myös melko matala, sillä pelin koko ja laivojen määrä ovat tällä hetkellä kovakoodattuja koodiin. Etenkin graafisen käyttöliittymän kanssa pelin koo'on muokkaaminen olisi hieman isompi työ. 

Graafisen käyttöliittymän kanssa on myös paikoitellen sekavuutta siinä, mitkä asiat ovat luokan GraphicalUserInterface ja mitkä taas FXMLControllerien hallittavissa. Tätä jaottelua olisi syytä parantaa ja hiota selkeämmäksi. 
