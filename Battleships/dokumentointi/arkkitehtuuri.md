# Arkkitehtuurikuvaus
## Rakenne
Ohjelma noudattaa tällä hetkellä kaksitasoista kerrosarkkitehtuuria seuraavanlaisella rakenteella: 

![Kerrosarkkitehtuuri]

Pakkauksesta _battleships.ui_ löytyvät käyttöliittymäluokat, ja _battleships.domain_ sisältää sovelluslogiikan luokat. 
Tietojen tallennus hoidetaan tulevaisuudessa _battleships.dao_-pakkauksen kautta.

## Käyttöliittymä
Ei vielä saatavilla.

## Sovelluslogiikka 
Sovelluksen looginen toimintamalli koostuu luokasta Game, ja tämän kanssa vuorovaikuttavan Player-luokan 
ilmentymistä HumanPlayer ja BotPlayer. 
(lisää tähän luokkakaavio)

![Pakkauskaavio](https://github.com/laaksoma/ot-harjoitustyo/blob/refactoring/Battleships/dokumentointi/sovelluslogiikka_laajempi_ilmandao.png)

## Tietojen pysyväistallennus

## Ohjelmaan jääneet heikkoudet
