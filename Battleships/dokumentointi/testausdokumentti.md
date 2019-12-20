# Testausdokumentti
Ohjelmaa on testattu sekä automatisoiduilla yksikkö- ja intergraatiotesteillä että manuaalisesti järjestelmätasolla.

## Yksikkö- ja integraatiotestaus
### Sovelluslogiikka
Sovelluslogiikan testit kohdistuvat pakkauksen battleships.domain sisällä oleviin luokkiin. Monet näistä testeistä testaavat myös integraatiota useiden luokkien välillä, sillä monet esimerkiksi luokan Game ominaisuuksista vaativat vuorovaikutusta useamman luokan välillä. Tapauksissa, joissa käyttöliittymän toiminta on välttämätöntä, on hyödynnetty [Mockitoa](https://site.mockito.org/).

Etenkin pienemmillä sovellusloogisilla luokilla on käytössään yksikkötestausta, jotta erilaiset tapaukset tulevat varmasti käytyä läpi koodin runsaan haarautuvuuden vuoksi. 

### DAO-luokka
Luokan ScoreDao testaus on toteutettu hyödyntämällä [keinotekoista MongoDB-tietokantaa](https://github.com/bwaldvogel/mongo-java-server).

### Testikattavuus
Pakkaus battleships.ui, joka sisältää FXMLControllerit ja käyttöliittymäluokat, on jätetty testeistä pois. Näitä lukuun ottamatta testien rivikattavuus on nykyisellään 72% ja haarautumakattavuus 71%. 
  
![testikattavuus_jacoco_raportti](https://github.com/laaksoma/ot-harjoitustyo/blob/master/Battleships/dokumentointi/kaaviot/testikattavuusraportti.png)
  
Testaamatta jäivät metodit, jotka pyörittävät isompia kokonaisuuksia luokan Game-integratiivisesta toiminnallisuudesta, kuten esimerkiksi metodit _turn()_ ja _playGame()_. 

## Järjestelmätestaus
Tämän sovelluksen kohdalla järjestelmätestaus on suoritettu manuaalisesti. 

### Asennus ja konfigurointi 
Sovellusta on testattu käyttöohjeen mukaisesti siten, että käytetty tietokone on ollut onlineyhteydessä. 

## Sovellukseen jääneet heikkoudet
Tällä hetkellä graafisen käyttöliittymän kanssa virheilmoitukset tulostuvat vain terminaaliin, eivät graafisen käyttöliittymän ikkunaan. 
