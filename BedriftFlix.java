package bedrift;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BedriftFlix {

    /*

    Som en del av evaluering for en sommerjobb fikk jeg i oppgave å løse følgende oppgave.
    Jeg har valgt å endre noen småting i oppgaven etter hensyn til bedriften som hadde oppgaven.

    Du skal lage registeret for kunder og produkter i et system for streaming-tjenesten "BedriftFlix".
    Koden du skriver skal brukes av andre utviklere, som skal lage andre deler av systemet
    (f.eks. brukergrensesnittet og selve streamingen).
    Det du lager trenger kun tilbys som Java-metoder, ikke tenk på å lage noe mer avansert grensesnitt.


    BedriftFlix tilbyr mange forskjellige Serier. Seriene er merket med én eller flere serietyper fra følgende liste:
    Drama, Humor, Krim, Animasjon, Dokumentar, Gameshow, Talkshow, Reality.
    Kundene kan velge hvilke serietyper de er interessert i.

    For hver serie skal programmet kunne lagre ID, tittel og en eller flere serietyper.
    En serie skal IKKE kunne ha mindre enn én serietype.
    For hver kunde skal programmet lagre ID, et navn og dato kunden ble registrert.
    Du må også lagre hvilke serietyper kunden er interessert i.
    Det er OK å ha kunder som ikke er interessert i noen serietyper.

    Funksjonalitetskrav 1: Registrering

    Lag en metode for å registrere en ny serie. Ta alt utenom ID som parameter, ID'en bør genereres av systemet.
    Det skal aldri kunne være en serie i systemet som ikke har minst en serietype.
    Hvis metoder likevel kalles på en måte som ville ført til at en serie manglet serietyper,
    må dette håndteres fornuftig.
    Lag passende metoder for å legge til og/eller fjerne serietyper på en serie.
    Lag en metode for å registrere en ny kunde. Ta alt som parameter utenom ID og serietyper kunden er
    interessert i. ID'en bør genereres av systemet, serietypene kunden er interessert i registreres senere.
    Lag passende metoder for å legge til og/eller fjerne serietyper en kunde er interessert i.
    Det er helt ok å ikke være interessert i noen serietyper.

    Funksjonalitetskrav 2: Uthenting

    Lag en metode som returnerer en kunde basert på kundens ID.
    Lag en metode som returnerer alle kunder som er interessert i en gitt serie, basert på seriens ID.
    En kunde er interessert i en serie dersom han er interessert i minst en av de serietypene som serien er merket med.
    Lag en metode som returnerer alle serier som har en gitt serietype
    (uavhengig av hvilke andre serietyper serien har), sortert alfabetisk på tittel.
    Lag en metode som returnerer alle kunder som ble registrert mellom to datoer. (Trenger ikke sorteres.)

    Funksjonalitetskrav 3: Interesserapport

    BedriftFlix ønsker å vite hvilke serier som er mest populære.
    De definerer interessevekten en serie har for en kunde som antall serietyper kunden er interessert i som
    serien også er merket med.
    (Så om en kunde er interessert i Drama, Reality og Krim, og en serie er merket med Drama, Krim og Animasjon,
    så vil serien ha interessevekt 2 for den kunden.)
    Den totale interessevekten for en serie er summen av interessevektene serien har for alle kundene i systemet.

    Lag en metode som tar IDene for en serie og en kunde, og returnerer interessevekten serien har for den kunden.
    Lag en metode som returnerer den serien i systemet som har høyest total interessevekt.
     */

    private List<Serie> serier = new ArrayList<>();
    private List<Kunde> kunder = new ArrayList<>();
    public static final List<String> serieTyper = Stream.of(
            "Drama", "Humor", "Krim", "Animasjon", "Dokumentar", "Gameshow", "Talkshow", "Reality").collect(
            Collectors.toList());

    // tar inn en String og sjekker om det er en gyldig serietype
    public static void sjekkSerieType(String serietype) {
        if (!serieTyper.contains(serietype)) {
            throw new IllegalArgumentException(serietype + " er ikke et gyldig navn på en serietype");
        }
    }

    public void leggTilKunde(Kunde kunde){
        if(!kunder.contains(kunde)) {
            this.kunder.add(kunde);
        }
    }

    public void leggTilSerie(Serie serie){
        if(!serier.contains(serie)) {
            this.serier.add(serie);
        }
    }

    public Kunde getKunde(int kundeID) {
        for (Kunde kunde : kunder) {
            if (kunde.getKundeID() == kundeID) {
                return kunde;
            }
        }
        throw new IllegalArgumentException("Det finnes ingen kunde med følgende kundeID: " + kundeID);
    }

    public Serie getSerie(int serieID) {
        for (Serie serie : serier) {
            if (serie.getSerieID() == serieID) {
                return serie;
            }
        }
        throw new IllegalArgumentException("Det finnes ingen serie med følgende serieID: " + serieID);
    }

    public List<Kunde> getInteresserteKunder(Serie serie) {
        List<Kunde> interesserteKunder = new ArrayList<>();
        for (Kunde kunde : kunder) {
            if (kunde.erInteressert(serie)) {
                interesserteKunder.add(kunde);
            }
        }
        return interesserteKunder;
    }

    public List<Serie> getSerieAvSerietype(String serietype) {
        List<Serie> ønskedeserier = new ArrayList<>();
        for (Serie serie : serier) {
            if (serie.erAvSerietype(serietype)) {
                ønskedeserier.add(serie);
            }
        }
        Collections.sort(ønskedeserier);
        return ønskedeserier;
    }

    public List<Kunde> getKunderFraTilDato(Date fraDato, Date tilDato) {
        List<Kunde> relevanteKunder = new ArrayList<>();
        for (Kunde kunde : kunder) {
            if ((!kunde.getRegisteringsdato().before(fraDato)) && (!kunde.getRegisteringsdato().after(tilDato))) {
                relevanteKunder.add(kunde);
            }
        }
        return relevanteKunder;
    }

    public int getInteresseVekt(int kundeID, int serieID) {
        Kunde kunde = this.getKunde(kundeID);
        Serie serie = this.getSerie(serieID);
        int vekt = 0;
        for (String serietype : kunde.getSerieInteresser()) {
            if (serie.erAvSerietype(serietype)) {
                vekt++;
            }
        }
        return vekt;
    }

    public Serie getMestPopulærSerie() {
        int høyesteScore = 0;
        Serie mestPopulærSerie = null;
        for (Serie serie : serier) {
            int vekt = 0;
            for (Kunde kunde : kunder) {
                vekt += getInteresseVekt(kunde.getKundeID(), serie.getSerieID());
            }
            if (vekt >= høyesteScore) {
                høyesteScore = vekt;
                mestPopulærSerie = serie;
            }
        }
        return mestPopulærSerie;
    }

    public static void main(String[] args) {
        BedriftFlix bedflix= new BedriftFlix();
        Kunde alexander = new Kunde("Alexander", "Fredheim", 25, "alexannf@stud.ntnu.no", "123");
        Kunde sindre = new Kunde("Sindre", "Sivertsen", 23, "a@b.c", "alexerkul");
        Kunde amalie = new Kunde("Amalie", "Henni", 22, "ds", "ada");
        Serie billions = new Serie("Billions", "Drama");
        Serie himym = new Serie("How I Met Your Mother", "Drama", "Humor");
        Serie exonthebeach = new Serie("Ex On The Beach", "Reality");
        Serie planetearth = new Serie("Planet Earth", "Dokumentar");
        Serie rickandmorty = new Serie("Rick & Morty", "Animasjon", "Humor");
        bedflix.leggTilKunde(alexander);
        bedflix.leggTilKunde(sindre);
        bedflix.leggTilKunde(amalie);
        bedflix.leggTilSerie(billions);
        bedflix.leggTilSerie(himym);
        bedflix.leggTilSerie(exonthebeach);
        bedflix.leggTilSerie(planetearth);
        bedflix.leggTilSerie(rickandmorty);

        alexander.leggTilSerieInteresse("Drama");
        System.out.println(bedflix.getInteresseVekt(alexander.getKundeID(),himym.getSerieID()));
        alexander.leggTilSerieInteresse("Humor");
        System.out.println(bedflix.getInteresseVekt(alexander.getKundeID(),himym.getSerieID()));

        amalie.leggTilSerieInteresse("Reality");
        amalie.leggTilSerieInteresse("Dokumentar");
        sindre.leggTilSerieInteresse("Dokumentar");
        sindre.leggTilSerieInteresse("Drama");

        System.out.println(bedflix.getInteresserteKunder(billions));
        System.out.println(bedflix.getInteresserteKunder(planetearth));

        System.out.println(bedflix.getMestPopulærSerie());
        alexander.fjernSerieType("Drama");
        System.out.println(bedflix.getMestPopulærSerie());

        System.out.println(bedflix.getSerieAvSerietype("Humor"));
        System.out.println(bedflix.getSerieAvSerietype("Drama"));

        System.out.println(alexander.getKundeID());
        System.out.println(sindre.getKundeID());
        System.out.println(amalie.getKundeID());









    }
}



