# Arkkitehtuurikuvaus
## Rakenne
Ohjelma noudattaa kolmitasoista kerrosarkkitehtuuria seuraavanlaisella rakenteella: 

![Kerrosarkkitehtuuri](https://github.com/laaksoma/ot-harjoitustyo/blob/refactoring/Battleships/dokumentointi/kaaviot/pakkauskaavio.png)

Pakkauksesta _battleships.ui_ löytyvät käyttöliittymäluokat, ja _battleships.domain_ sisältää sovelluslogiikan luokat. 
Tietojen pysyväistallennus hoidetaan _battleships.dao_-pakkauksen kautta.

## Käyttöliittymä
Graafinen käyttöliittymä sisältää kolme näkymää:
 - pelin aloitusnäkymä
 - laudan luomisen näkymä pelaajakohtaisesti
 - pelinäkymä, 
 
joista jokainen on toteutettu omana Scene-olionaan. Näkymät ovat yksi kerrallaan näkyvillä, ja näitä päivitetään asettamalla GraphicalUserInterface-luokassa aina haluttu Scene sopivan set-muotoisen metodin avulla sovelluksen Stagelle. 

## Sovelluslogiikka 
Sovelluksen looginen toimintamalli koostuu luokasta Game, ja tämän kanssa vuorovaikuttavan Player-luokan 
ilmentymistä HumanPlayer ja BotPlayer. 

![Pakkauskaavio](https://github.com/laaksoma/ot-harjoitustyo/blob/refactoring/Battleships/dokumentointi/kaaviot/sovelluslogiikka_laajempi_ilmandao.png)

Käytettävissä on kolme erilaista käyttöliittymää: TestUserInterface, TextUserInterface ja GraphicalUserInterface. Luokka TestUserInterface on vain testien käytettävissä. GraphicalUserInterface on tällä hetkellä asetettu sovelluksessa oletukseksi, ja tulevaisuudessa tulee graafinen käyttöliittymä olemaankin ensisijainen tapa pelata, mutta toistaiseksi voi myös tekstikäyttöliittymää käyttää asettamalla tämän Mainissa (joskin tämä ei ole suotavaa). 

GraphicalUserInterface vuorovaikuttaa battleships.ui-packagen sisällä kolmen FXML-controllerin kanssa: FXMLStartController, FXMLSetUpController ja FXMLPlayGameController.

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
