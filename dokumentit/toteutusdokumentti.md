# Toteutusdokumentti

## Ohjelman yleisrakenne

Projekti on toteutettu Java-ohjelmointikielellä ja käyttää xboard ja
lichess alustoja hyödyntävää java-projektipohjaa [chess](https://github.com/TiraLabra/chess).

Projektin koodi löytyy ```datastructureproject``` nimisestä Java-paketista
ja projektin käyttämä alustan koodi löytyy ```chess``` ja ```loggin``` nimisistä Java-paketeista.

Luokka ```chess.App``` on ohjelman pääluokka ja se käyttää ```chess.bot.ChessBot```-rajapintaa toteuttavaa
luokkaa shakin-pelaamisessa. Paketista ```datastructureproject.chess``` löytyvä ```TreeSearchBot``` luokka
toteuttaa tämän rajapinnan, mikä käyttää luokkaa ```Heuristics``` pelilaudan tilanteen arvionnissa.
Luokka ```TreeSearchBot``` voi pelata ahneesti (**greedy** eli valitsee parhaimman arvoisen tämän hetkisistä) tai sitten arvio tulevat mahdolliset shakki-siirrot ja valitsee niistä sen mikä parhaimman arvoisen mahdollisista siirtoista. Luokalla on kahta konstruktoria. Yksi niistä ottaa argumenttina rajapintaa ```ChessMovePicker``` toteuttavan luokan ja toinen ```GameNodeEvaluator``` rajapintaa toteuttavan luokan. Rajapinta ```ChessMovePicker``` toteuttavat luokat jotka saavat tiettyä pelitilannetta kuvaavaa luokan ```BoardState``` oliota ja valitsevat sitten seuraavan siirron. Tällä hetkellä rajapinta ```ChessMovePicker``` toteuttavat vain kaksi luokkaa ```GreedyMovePicker``` ja ```EvaluatingPicker```. Luokka ```GreedyMovePicker``` voidaan käyttää saamaan luokka ```TreeSearchBot``` toimimaan ahneasti shakkisiirtojen valinnassa, kun taas luokkaa ```EvaluatingPicker``` voidaan käyttää järkevämmän tekoälyn toiminnassa. Nimittäin, luokka ottaa argumentina rajapintaa ```GameNodeEvaluator``` toteuttavan luokan joita on tällä hetkellä kaksi ```MinMax``` ja ```AlphaBeta```.

Lyhyesti luokasta ```TreeSearchBot``` saadaan
ahneasti pelaava versio seuraavalla kutsulla
    
```
    TreeSearchBot greedyBot = new TreeSearchBot(new GreedyMovePicker());
```
```MinMax```-algoritmilla pelaava kutsulla
    
```
    TreeSearchBot minMaxBot = new TreeSearchBot(new MinMax(2));
```
```alpha-beta```-algoritmilla pelaava kutsulla
    
```
    TreeSearchBot alphaBetaBot = new TreeSearchBot(new AlphaBeta(2));
```

Luokka ```Heuristics``` käyttää paketin ```datastructureproject.chess```
luokkia pelitilanteen arvionissa. Pelitilannetta arviodaan siten että katsotaan mikä on molemien pelaajien
jäljellä olevat pelinappulat ja näitä sitten lasketaan pelitilanteen arvio (katso [WikiPedia](https://en.wikipedia.org/wiki/Chess_piece_relative_value#Standard_valuations)).

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
