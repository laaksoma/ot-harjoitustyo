# Battleships

Sovelluksen avulla käyttäjän on mahdollista pelata Battleships-peliä joko yksin yksinkertaista tekoälyä vastaan, tai yhdessä kaverin kanssa.

## Dokumentaatio
[Arkkitehtuurikuvaus](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/arkkitehtuuri.md)

[Kayttoohje](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kayttoohje.md)

[Testausdokumentti](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/testausdokumentti.md)

[Tuntikirjanpito](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/tuntikirjanpito.md)

[Vaatimusmaarittely](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/vaatimuusmaarittely.md)

## Releaset

[Viikon 5 release](https://github.com/laaksoma/ot-harjoitustyo/releases/tag/viikko5)

[Viikon 6 release](https://github.com/laaksoma/ot-harjoitustyo/releases/tag/Viikko6)

[Loppurelease](https://github.com/laaksoma/ot-harjoitustyo/releases/tag/Loppupalautus)

## Komentorivitoiminnot
### Suorittaminen komentoriviltä 
Ohjelman suorittaminen komentoriviltä tapahtuu seuraavalla komennolla (mikäli compile on jo tehty, sanan 'compile' voi jättää välistä): 

<pre>mvn compile exec:java -Dexec.mainClass=battleships.ui.BattleshipsMain</pre>

### Testaus
Testit suoritetaan komennolla 

<pre>mvn test</pre>

Testikattavuusraportti luodaan komennolla 

<pre>mvn test jacoco:report</pre>

ja sitä voi tarkastella avaamalla selaimella tiedoston _target/site/jacoco/index.html_.

### Suoritettavan jarin generointi
Jar-tiedosto generoidaan hakemistoon _target_ komennolla 

<pre>mvn package</pre>

### JavaDoc
JavaDoc generoidaan hakemistoon _target/site/apidocs/_ komennolla

<pre>mvn javadoc:javadoc</pre>

### Checkstyle 
Tiedostoon [checkstyle.xml](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla 

<pre>mvn jxr:jxr checkstyle:checkstyle</pre>

Checkstyle-tarkistusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/checkstyle.html_.

