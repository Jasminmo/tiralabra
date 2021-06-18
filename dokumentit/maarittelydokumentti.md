# Määrittelydokumentti

## Yleiskuvaus

* Opinto-ohjelma: tietojenkäsittelytieteen kandidaatti (TKT)
* Projektin ohjelmointikieli: Java
* Projektin aihe: Tekoäly shakkipelille
* Projektin algoritmi: Alpha-beta pruning
* Projektin dokumentaatiokieli: suomen kieli
* Projektin kommenteissa ja koodauksessa käytetty kieli: englannin kieli


Projektin tarkoituksena on toteuttaa shakkia pelaavan tekoälyn [Alpha–beta pruning](https://en.wikipedia.org/wiki/Alpha–beta_pruning)-algoritmilla.
Algoritmin aikavaativuus on parhaassa tapauksessa ```O(b^(d/2))``` ja pahimmassa tapauksessa ```O(b^d)```,
missä muuttaja ```d``` on hakupuun korkeus ja ```b``` on mahdollisten siirtojen lukumäärä.
Algoritmin tilavaativuus on  ```O(b*d)```.

Projekti on toteutettu Java-ohjelmointikielellä ja käyttää xboard ja
lichess alustoja hyödyntävää java-projektipohjaa [chess](https://github.com/TiraLabra/chess).
Projekti on testattu [lichess](https://lichess.org) shakki-pelialustassa ja todettu toimivan.
Projektia voidaan käyttää samalla tavalla kuin *chess* java-projektipohjaa.
Lisä tietoa saa lukemalla projektipohjan [ohjeita](../app/documentation/Beginners_guide.md).


### Lähteet
* [Alpha–beta pruning (wikipedia)](https://en.wikipedia.org/wiki/Alpha–beta_pruning)
* [Introduction to AI](https://materiaalit.github.io/intro-to-ai/)-kurssi
* [Shakin pelisäännöt](https://en.wikipedia.org/wiki/Rules_of_chess)
* [Shakkinappuloiden arvot](https://en.wikipedia.org/wiki/Chess_piece_relative_value)
