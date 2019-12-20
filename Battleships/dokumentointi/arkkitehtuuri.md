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

![tietokantataulukko](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/datamalli-tietokantataulut.png)

Toiminnallisista kokonaisuuksista vastaa luokka Game, joka vuorovaikuttaa käyttöliittymän kanssa ja hallinnoi sovelluksen loogista toimintamallia. Eri toiminnallisuuksista vastaavat eri metodit, kuten esimerkiksi 

- createBoard(int numberOfShips)
- playGame()
- gameOver(Player inTurn, Player notInTurn). 

Jotkin luokan metodeista, kuten esimerkiksi metodi _turn_, ovat itsessään hallinnollisia kokonaisuuksia, jotka vastaavat yhdestä ohjelman toiminnallisuudesta pyörittämällä muiden metodien (tässä tapauksessa esimerkiksi _getIndexForAnotherPlayer(Player playerNotWanted)_ ja _areCoordinatesAlreadyUsed(PlacementInfo info, Player otherPlayer)_) avulla tätä kokonaisuutta ja sen logiikkaa. 

![Pakkauskaavio](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/sovelluslogiikka_laajempi.png)

Käytettävissä on kolme erilaista käyttöliittymää: TestUserInterface, TextUserInterface ja GraphicalUserInterface. Luokka TestUserInterface on vain testien käytettävissä. GraphicalUserInterface on tällä hetkellä asetettu sovelluksessa oletukseksi, mutta TextUserInterface on mahdollista ottaa käyttöön parametrilla _--text_ komentorivikutsun yhteydessä. 

GraphicalUserInterface vuorovaikuttaa battleships.ui-packagen sisällä kolmen FXML-controllerin kanssa: FXMLStartController, FXMLSetUpController ja FXMLPlayGameController, joiden avulla on luotu sovelluksen eri näkymät.

## Tietojen pysyväistallennus
Pakkauksessa battleships.dao luokka ScoreDao huolehtii tietojen tallettamisesta dokumentteihin ja lisäämisestä internettietokantaan. 

Sovelluksessa on noudatettu Data Access Object -suunnittelumallia, ja tietokantayhteydet on eristetty luokkaan ScoreDao, jolloin itse pelin sovelluslogiikka ei toimi näiden kanssa suoraan. Mikäli tietojen pysyväistallennuksen muotoa halutaan muokata, voi muokkaukset toteuttaa luokkaan ScoreDao, joka toimii omana sisäisenä kokonaisuutenaan ollen yhteydessä luokan Game kanssa vain metodien _addWinner(Score score)_ ja _getHighScores()_ kautta. 

### Tiedostot
Sovellus tallettaa pelaajien nimet ja pisteet erillisiin dokumentteihin, jotka tallennetaan online-tietokantaan [MongoDB](https://www.mongodb.com/):hen, joten sovelluksen käyttäjän ei tarvitse huolehtia erillisistä tiedostoista, jotka kulkisivat sovelluksen mukana paikallisesti. 

Tiedot tallennetaan formaatissa 
<pre>
new Document("name", Pelaaja)
        .append("points", 199);
</pre>

jolloin avaimet _name_ ja _points_ vastaavat luokan Score olion vastaavia tietoja, joissa _winnerName_ pitää kirjaa pelaajan nimestä, ja _winnerPoints_ pitää kirjaa tämän saamista pisteistä.

## Päätoiminnallisuudet
### Laudan luonti
Pelin käynnistämisen yhteydessä kukin pelaaja asettaa omat laivansa pelilaudalle. Luokan Game metodi _createBoard()_ on vastuussa tästä, ja kutsuu eteenpäin metodia _setUpBoard()_. Tätä kutsutaan kullekin pelaajalle erikseen. Jokainen pelaaja käsittelee yhden laivan kerrallaan pelin antamassa järjestyksessä suurimmasta laivasta pienimpään. Sovellus tarkistaa ensin annetut koordinaatit ja niiden sopivuuden, ja asettaa vasta sitten laivan pelaajan merelle yksi osa kerrallaan iteroimalla for-loopilla, joka pyörii laivan pituuden verran. Kun laiva, sekvenssikaavion esimerkissä pituudella kolme, on onnistuneesti asetettu ruutuun, siirrytään seuraavaan laivaan. Mikäli käy niin, että laivalle annetut koordinaatit ovat epäsopivat esimerkiksi liian lähellä sijaitsevan laivan takia tai koska laiva ei mahdu annettuun suuntaan, palautetaan false, ja otetaan for-loopin iteraatiokierros uudestaan lisäämättä laivaa.

![Sekvenssikaavio1](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/sekvenssikaavio_laivanasettaminenlaudalle.png)

### Vuoron toiminta pelaajalla
Peliä pelataan kutsumalla luokan Game metodia _playGame_, joka arpoo aloittajan ja käynnistää ensimmäisen vuoron kutsumalla vuorostaan metodia _turn_. Vuoroja ylläpidetään while(true)-loopilla, josta päästään pois vain joko pysäyttämällä looppi pelaajan osuttua ohi tai pelin päättyessä, jolloin metodi palauttaa _playGame_-metodille boolean-arvon false. Mikäli peli on yhä käynnissä, palautetaan true, ja kutsutaan metodia _turn_ uudestaan seuraavalla pelaajalla.

![Sekvenssikaavio2](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/sekvenssikaavio_vuoro.png)

## Ohjelmaan jääneet heikkoudet
Ohjelman sovelluslogiikka toimii tässä kyseisessä ohjelmassa siten, että Game tuntee käyttöliittymän ja käyttää tätä, eikä toisin päin, mikä paikoitellen tuottaa hieman ongelmia etenkin testattavuuden suhteen. 

Toinen selkeä heikkous on Singletonien käyttö - sekä luokka Game että UserInterface ovat Singleton-luokkia, mikä periytyy myös UserInterfacea edustaville luokille. Tämä vaikeuttaa testaamista, vaikka kiertääkin joitakin pelin toiminnallisia ongelmia toimien ratkaisuna niihin. Loppupeleissä vaihtokauppa ei kuitenkaan ehkä ollut paras mahdollinen. 

Ohjelman konfiguroitavuus on myös melko matala, sillä pelin koko ja laivojen määrä ovat tällä hetkellä kovakoodattuja koodiin. Etenkin graafisen käyttöliittymän kanssa pelin koo'on muokkaaminen olisi hieman isompi työ. 

Graafisen käyttöliittymän kanssa on myös paikoitellen sekavuutta siinä, mitkä asiat ovat luokan GraphicalUserInterface ja mitkä taas FXMLControllerien hallittavissa. Tätä jaottelua olisi syytä parantaa ja hiota selkeämmäksi. 
