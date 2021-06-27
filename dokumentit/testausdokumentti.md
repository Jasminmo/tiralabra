# Testausdokumentti
Projektin koodi löytyy ```app```-kansiosta.
Koodi muodostuu projektin koodista sekä sen
käyttämästä lichess-alustaa kumminikoivan sovelluksen koodista.
Projektin koodi löytyy ```datastructureproject``` nimisestä Java-paketista
ja projektin käyttämä alustan koodi löytyy ```chess``` ja ```loggin``` nimisistä Java-paketeista.

## Miten testit voidaan toistaa
Testit voidaan toistaa seuraamalla ajamalla seuraavaa komentoa sovelluksen ```app```-kansiossa ```./gradlew test```.

## Mitä on testattu, miten tämä tehtiin
Tällä hetkellä ```datastructureproject.ai.Heuristics``` luokka ja
paketin ```datastructureproject.chess``` luokat on testattu JUnit-testauskehysellä.

## Minkälaisilla syötteillä testaus tehtiin
Testaus tarkistaa että sovellus voi jäsentää [FEN](https://en.wikipedia.org/wiki/Forsyth–Edwards_Notation)-tyyppistä syötettä ja antaa oikeat arvot pelilaudan tilanteelle.
