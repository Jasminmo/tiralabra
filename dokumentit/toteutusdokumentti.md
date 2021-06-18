# Toteutusdokumentti

## Ohjelman yleisrakenne

Projekti on toteutettu Java-ohjelmointikielellä ja käyttää xboard ja
lichess alustoja hyödyntävää java-projektipohjaa [chess](https://github.com/TiraLabra/chess).

Projektin koodi löytyy ```datastructureproject``` nimisestä Java-paketista
ja projektin käyttämä alustan koodi löytyy ```chess``` ja ```loggin``` nimisistä Java-paketeista.

Luokka ```chess.App``` on ohjelman pääluokka ja se käyttää ```chess.botChessBot```-rajapintaa toteuttavaa
luokkaa shakin-tekoälynä. Paketin ```datastructureproject.ai``` ```GreedyBot``` ja ```AlphaBetaBot``` luokat
toteuttavat tämän rajapinnan. Molemmat luokat käyttävät luokkaa ```Heuristics``` pelitilanteen arvionnissa.
Luokka ```GreedyBot``` pelaa ahneesti, eli se valitsee parhaimman arvoisen shakki-siirron kaikista
mahdollisista siirtoista. Luokka ```AlphaBetaBot``` taas rakentaa pelipuun joka on korkeudeltaan kolme,
eli se arvioi mikä on paras siirto, kun katsotaan kolmen pelisiirron jälkeistä tilannetta, ja valitsee
näistä siirroista parhaimman. Luokka ```Heuristics``` käyttää paketin ```datastructureproject.chess```
luokkia pelitilanteen arvionissa. Pelitilannetta arviodaan siten että katsotaan mikä on molemien pelaajien
jäljellä olevat pelinappulat ja näitä sitten lasketaan standardin perusarvion perusteella (katso [WikiPedia](https://en.wikipedia.org/wiki/Chess_piece_relative_value#Standard_valuations)) ja summataan.
Pelinappulien arvot ovat:
 * Sotilas on yhden pisteen arvoinen.
 * Lähetti ja ratsu ovat molemmat kolmen pisteen arvoisia.
 * Torni on viiden pisteen arvoinen.
 * Kuningatar on yhdeksän pisteen arvoinen.


## Saavutetut aika- ja tilavaativuudet
Algoritmin aikavaativuus on parhaassa tapauksessa ```O(b^(d/2))``` ja pahimmassa tapauksessa ```O(b^d)```,
missä muuttaja ```d``` on hakupuun korkeus ja ```b``` on mahdollisten siirtojen lukumäärä.
Algoritmin tilavaativuus on  ```O(b*d)```.

## Työn mahdolliset puutteet ja parannusehdotukset
* Tekoäly tällähetkellä pelaa hyvin, mutta valitettavasti sillä on ongelmia shakkitilanteiden kanssa.
* Peliin voisi lisätä graafinen käyttöliittymä.

## Lähteet
* [Shakin pelisäännöt](https://en.wikipedia.org/wiki/Rules_of_chess)
* [Alpha–beta pruning (wikipedia)](https://en.wikipedia.org/wiki/Alpha–beta_pruning)
* [Introduction to AI](https://materiaalit.github.io/intro-to-ai/)-kurssi
