# Määrittelydokumentti

## Yleiskuvaus

* Opinto-ohjelma: Tietojenkäsittelytiede
* Projektin aihe: Verkot ja polunetsintä
* Projektin ohjelmointikieli: Java
* projektin aihe: Tekoäly shakkipelille

Projektin tarkoituksena on toteuttaa tekoälyn shakkipelille käyttäen esim. A*-algoritmia alussa ja jos mahdollista ja aikaa riittää niin
tekoälyn voisi toteuttaa [MCTS](https://en.wikipedia.org/wiki/Monte_Carlo_tree_search)-algoritmilla.
Kuitenkin yritän alussa tutkia miten löydetään tehokkaasti nopein/lyhin reitti labyrintistä ulos. Labyrintti voi olla tehty esimerksi ascii-merkeistä tai piirretty kuva. 

## Algoritmin suunnittelu ja analyysi
Miten löydetään tehokkaasti nopein/lyhin reitti verkossa kahden pisteen välillä. Verkon pisteet voivat olla esimerkiksi koordinaatteja.

Alkutiedot ratkaisumenetelmistä on otettu [Maze-solving algorithm](https://en.wikipedia.org/wiki/Maze-solving_algorithm)

Ajatuksena on luoda useita ratkaisualgoritmeja, joiden monimutkaisuus vaihtelee olennaisesti välillä O (|V| + |E|), missä V on verkon solmujoukko ja E on kaarijoukko.

