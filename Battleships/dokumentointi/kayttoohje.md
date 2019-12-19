# Käyttöohje
Lataa tiedosto [battleships.jar](https://github.com/laaksoma/ot-harjoitustyo/releases/tag/Loppupalautus).

### Ohjelman käynnistäminen ja konfigurointi
Ohjelman voi suorittaa joko tekstikäyttöliittymällä 

<pre>java -jar Battleships-1.0-SNAPSHOT.jar --text</pre>

tai graafisella käyttöliittymällä korvaamalla parametrin jollakin muulla tai jättämällä sen pois

<pre>java -jar Battleships-1.0-SNAPSHOT.jar</pre>

### Pelin aloittaminen
![aloitusnäkymä](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/aloitusnakyma.png)

Sovellus aukeaa aloitusnäkymään, jossa pelaaja saa valita haluamansa pelitavan (yksin/yhdessä), ja napin painalluksella tämän vahvistettuaan antaa pelaajan/pelaajien nimet. Mikäli pelaaja ei anna pelitapaa tai pelaajan nimeä, ei peli päästä tätä eteenpäin.

### Pelilaudan luominen pelaajalle
![laudan luominen](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/laudanluonti.png)

Pelaajalle tarjotaan ohjeet laivojen asettamiseen, ruudukko, johon laivat tulee asettaa, ja tekstikentät, joihin tulee syöttää aloituspisteen koordinaatit ja suunta. Pelaaja näkee myös asettamansa laivan pituuden tekstinä. Mikäli koordinaattien syöte on väärä, ei laivaa aseteta. Mikäli koordinaatit ovat sallittuja, laiva asetetaan ja ruudukko päivittyy. 

Kun kaikki laivat on asetettu, siirrytään kahden pelaajan pelissä automaattisesti seuraavan pelaajan laivojen asettamiseen. Muutoin tämä vaihe jätetään välistä ja siirrytään automaattisesti peliin.

### Pelaaminen
![pelinakyma](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/pelinakyma.png)

Peli arpoo aloittajan. Pelissä pelaajat voivat vuorotellen arvata toistensa ruudukoista alueita, joissa arvelevat laivojen olevan. Mikäli pelaaja arvaa oikein, saa tämä jatkaa vuoroaan. Vuoro siirtyy eteenpäin, kun pelaaja arvaa väärin. Kun toiselta pelaajalta on paljastettu viimeinenkin laiva, peli päättyy, ja pelaajalle tarjotaan mahdollisuus aloittaa uusi peli.
