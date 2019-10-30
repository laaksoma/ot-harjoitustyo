# Vaatimuusmäärittely

## Sovelluksen tarkoitus
Sovellus tarjoaa käyttäjälle mahdollisuuden pelata _Battleships_-peliä tietokonetta vastaan. 

## Käyttöliittymäluonnos

## Perusversion tarjoama toiminnallisuus

* Ohjenäkymä
  - Pelaajalle tarjotaan lyhyet ja ytimekkäät ohjeet pelin toiminnasta 
  - Nappi, jota painamalla pelaaja pääsee asetusnäkymään
  
* Laivojen asettelun näkymä
  - Pelaajalle tarjotaan ohjeet ja ruudukko laivojen asettamista varten
    - Laivat asetetaan antamalla näiden alku- ja loppupisteen koordinaatit
    - Mikäli pelaaja yrittää asettaa laivat pelin sallimattomalla tavalla (esimerkiksi liian lähelle toisiaan 
    tai pelilaudan ulkopuolelle), peli herjaa asiasta
   - Kun kaikki laivat on asetettu paikoilleen, siirrytään automaattisesti pelinäkymään
 
* Pelinäkymä
  - Aloittaja arvotaan automaattisesti 
  - Vuorossa oleva pelaaja yrittää upottaa toisen laivat arvaamalla näiden sijainnin ruutu kerrallaan
    - Jos pelaaja osuu laivaan, tämän vuoro jatkuu - jos taas ei, siirtyy vuoro toiselle pelaajalle
    - Pelaaja saa ilmoituksen, mikäli tämä yrittää arvata jo arvattua ruutua
  - Laivojen jäljellä oleva määrä näkyy pelaajalle
  - Pelaaja näkee sekä omansa että toisen meren tilanteen
    - Jo arvatut ruudut on merkitty
  - Pelin voittaja on se, joka ensin upottaa toisen laivat

## Jatkokehitysideoita

Peliä ja sen toimintaa voisi kehittää esimerkiksi seuraavilla ominaisuuksilla: 

* Mahdollisuus avata ohjeet kesken pelin
* Mahdollisuus aloittaa uusi peli
* Mahdollisuus aloittaa uusi peli, vaikka vanha olisi vielä kesken
* Mahdollisuus pelata toista ihmispelaajaa vastaan
* Pelin pisteytys ja näiden tietojen mahdollinen tallennus 
* Graafisen käyttöliittymän hionta
* Äänitehosteiden lisäys peliin 
