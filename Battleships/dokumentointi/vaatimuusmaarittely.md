# Vaatimuusmäärittely

## Sovelluksen tarkoitus
Sovellus tarjoaa käyttäjälle mahdollisuuden pelata _Battleships_-peliä joko tietokonetta tai toista pelaajaa vastaan lokaalisti.

## Käyttöliittymäluonnos

![Hahmotelma](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/kayttoliittymahahmotelma.png)

## Perusversion tarjoama toiminnallisuus

* Ohjenäkymä
  - Pelaaja toivotetaan tervetulleeksi peliin ja kysytään, miten halutaan pelata
  - Tarjotaan vaihtoehdot pelata joko yksin tietokonetta vastaan tai kaverin kanssa
  - Pelaajan/pelaajien nimille kentät
  - Nappi, jota painamalla hyväksytään valinnat
  
* Laivojen asettelun näkymä
  - Pelaajalle tarjotaan ohjeet ja ruudukko laivojen asettamista varten 
    - Laivat asetetaan antamalla näiden alku- ja loppupisteen koordinaatit, ja suunta laivalle
    - Mikäli pelaaja yrittää asettaa laivat pelin sallimattomalla tavalla (esimerkiksi liian lähelle toisiaan 
    tai pelilaudan ulkopuolelle), peli herjaa asiasta 
   - Kun kaikki laivat on asetettu paikoilleen, siirrytään automaattisesti pelinäkymään 
       - Mikäli pelataan kaverin kanssa, siirrytään ensin automaattisesti asettamaan toisenkin pelaajan laivat, ja vasta sitten pelinäkymään

 
* Pelinäkymä
  - Aloittaja arvotaan automaattisesti 
  - Vuorossa oleva pelaaja yrittää upottaa toisen laivat arvaamalla näiden sijainnin ruutu kerrallaan
    - Jos pelaaja osuu laivaan, tämän vuoro jatkuu - jos taas ei, siirtyy vuoro toiselle pelaajalle 
    - Pelaaja saa ilmoituksen, mikäli tämä yrittää arvata jo arvattua ruutua
  - Pelaaja näkee sekä omansa että toisen meren tilanteen 
    - Jo arvatut ruudut on merkitty 
  - Pelin voittaja on se, joka ensin upottaa toisen laivat 

## Jatkokehitysideoita

Peliä ja sen toimintaa voisi kehittää esimerkiksi seuraavilla ominaisuuksilla: 

* Mahdollisuus avata ohjeet kesken pelin
* Mahdollisuus aloittaa uusi peli
* Mahdollisuus aloittaa uusi peli, vaikka vanha olisi vielä kesken
* Pelin pisteytys ja näiden tietojen mahdollinen tallennus KESKEN
* Graafisen käyttöliittymän hionta
* Äänitehosteiden lisäys peliin 
