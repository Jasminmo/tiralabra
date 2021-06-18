# Testausdokumentti
Projektin koodi löytyy ```app```-kansiosta.
Koodi muodostuu projektin koodista sekä sen
käyttämästä lichess-alustaa kumminikoivan sovelluksen koodista.
Projektin koodi löytyy ```datastructureproject``` nimisestä Java-paketista
ja projektin käyttämä alustan koodi löytyy ```chess``` ja ```loggin``` nimisistä Java-paketeista.

## Mitä on testattu, miten tämä tehtiin
Tällä hetkellä ```datastructureproject.ai.Heuristics``` luokka ja
paketin ```datastructureproject.chess``` luokat on testattu JUnit-testauskehysellä.

## Minkälaisilla syötteillä testaus tehtiin
Testaus tarkistaa että sovellus voi jäsentää [FEN](https://en.wikipedia.org/wiki/Forsyth–Edwards_Notation)-tyyppistä syötettä ja antaa oikeat arvot pelilaudan tilanteelle.

## Miten testit voidaan toistaa
Testit voidaan toistaa seuraamalla seuraavia ohjeite:
1. kloonaamaa tämä repositio komennolla ```git clone <URL>```, 
   missä ```<URL>``` on tämän reposiotion URL-osoite,
2. siirry kloonaamasi repositiossa olevaan koodikansioon ```cd tiralabra/app```,
2. suorite testausta suorittava gradle komento ```./gradlew test```.