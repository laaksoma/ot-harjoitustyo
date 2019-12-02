# Arkkitehtuurikuvaus
## Rakenne
Ohjelma noudattaa tällä hetkellä kaksitasoista kerrosarkkitehtuuria seuraavanlaisella rakenteella: 

![Kerrosarkkitehtuuri](https://github.com/laaksoma/ot-harjoitustyo/blob/refactoring/Battleships/dokumentointi/kaaviot/pakkauskaavio_ilmandao.png)

Pakkauksesta _battleships.ui_ löytyvät käyttöliittymäluokat, ja _battleships.domain_ sisältää sovelluslogiikan luokat. 
Tietojen tallennus hoidetaan tulevaisuudessa _battleships.dao_-pakkauksen kautta.

## Käyttöliittymä
Ei vielä saatavilla; toistaiseksi käytössä on vain tekstikäyttöliittymä.

## Sovelluslogiikka 
Sovelluksen looginen toimintamalli koostuu luokasta Game, ja tämän kanssa vuorovaikuttavan Player-luokan 
ilmentymistä HumanPlayer ja BotPlayer. 
(lisää tähän luokkakaavio)

![Pakkauskaavio](https://github.com/laaksoma/ot-harjoitustyo/blob/refactoring/Battleships/dokumentointi/kaaviot/sovelluslogiikka_laajempi_ilmandao.png)

## Tietojen pysyväistallennus
Ei vielä saatavilla.

## Päätoiminnallisuudet
### Laudan luonti
Ei vielä saatavilla.

### Vuoron toiminta pelaajalla
Peliä pelataan kutsumalla luokan Game metodia _playGame_, joka arpoo aloittajan ja käynnistää ensimmäisen vuoron kutsumalla vuorostaan metodia _turn_. Vuoroja ylläpidetään while(true)-loopilla, josta päästään pois vain joko pysäyttämällä looppi pelaajan osuttua ohi tai pelin päättyessä, jolloin metodi palauttaa _playGame_-metodille boolean-arvon false. Mikäli peli on yhä käynnissä, palautetaan true, ja kutsutaan metodia _turn_ uudestaan seuraavalla pelaajalla.

![Sekvenssikaavio](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/sekvenssikaavio_vuoro.png)

## Ohjelmaan jääneet heikkoudet
Ei vielä saatavilla.
