# Käyttöohje
Sovelluksen on tarkoituksena toimia shakkia pelaavana tekoälynä, joka 
pystyy pelaamaan sekä ihmistä että itseään vastaan.
Sovellus on toteutettu Java-ohjelmointikielellä ja käyttää xboard ja
lichess alustoja hyödyntävää java-projektipohjaa [chess](https://github.com/TiraLabra/chess).

## Ohjelman suoritus
Ohjelma käyttää [lichess](lichess.org)-alustan API-liittymää shakkin pelaamiseen.
Ideana on että käyttäjä luo uuden käyttäjä tilin [lichess](lichess.org)-alustassa ja
luoda ```API-token```-avaimen jota sitten käytetään shakin pelaamiseen alustalla.
Seuraa näitä ohjeita:
1. Kloonaa tämä github repositio komennolla ```git clone https://github.com/Jasminmo/tiralabra```
2. Luo uusi ```API-token``` [lichess.org](https://lichess.org/account/oauth/token/create) sivustossa ja hyväksy kaikki asetukset.
3. Hae luomaasi ```API-token```:iin tarvittavat oikeudet seuraavalla komennolla, missä ```user``` on käyttäjä nimesi ja ```token``` on luomasi ```API-token```:

    ```curl -d '' https://lichess.org/api/bot/<user>/upgrade -H "Authorization: Bearer <token>"```

4. Aloita [lichess]():issä uusi shakkipeli ja
5. Käynnistä shakkiohjelma komennolla:
    ```./gradlew run --args="--lichess --token=<token-avain>"```
  Näin ohjelma alkaa pelata shakkia.
6. Kun haluat sulkea ohjelman paina ```CTRL+C```.
7. Jos tarvitset lisätietoa lue seuraavassa linkistä löytyvät [ohjeet](https://github.com/TiraLabra/chess).