# Battleships

Sovelluksen avulla käyttäjän on mahdollista pelata Battleships-peliä joko yksin yksinkertaista tekoälyä vastaan, tai yhdessä kaverin kanssa.

## Dokumentaatio
[Arkkitehtuurikuvaus](https://github.com/laaksoma/ot-harjoitustyo/blob/refactoring/Battleships/dokumentointi/arkkitehtuuri.md)

[Kayttoohje](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kayttoohje.md)

Testaus

[Tuntikirjanpito](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/tuntikirjanpito.md)

[Vaatimusmaarittely](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/vaatimuusmaarittely.md)

## Releaset

[Viikon 5 release](https://github.com/laaksoma/ot-harjoitustyo/releases/tag/viikko5)

[Viikon 6 release](https://github.com/laaksoma/ot-harjoitustyo/releases/tag/Viikko6)

## Komentorivitoiminnot
### Suorittaminen komentoriviltä 
Ohjelman suorittaminen komentoriviltä tapahtuu seuraavalla komennolla (mikäli compile on jo tehty, sanan 'compile' voi jättää välistä): 

> mvn compile exec:java -Dexec.mainClass=battleships.ui.BattleshipsMain

### Testaus
Testit suoritetaan komennolla 

> mvn test

Testikattavuusraportti luodaan komennolla 

> mvn test jacoco:report

ja sitä voi tarkastella avaamalla selaimella tiedoston _target/site/jacoco/index.html_.

### Suoritettavan jarin generointi
Jar-tiedosto generoidaan hakemistoon _target_ komennolla 

> mvn package

### JavaDoc
JavaDoc generoidaan hakemistoon _target/site/apidocs/_ komennolla

> mvn javadoc:javadoc

### Checkstyle 
Tiedostoon [checkstyle.xml](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla 

> mvn jxr:jxr checkstyle:checkstyle

Checkstyle-tarkistusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/checkstyle.html_.

