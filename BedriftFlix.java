package bedrift;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class BedriftFlix {

    /*

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

    private Map<UUID,Serie> serier = new HashMap<>();
    private Map<UUID,Kunde> kunder = new HashMap<>();


    // Oppgave: Lag en metode for å registrere en ny kunde
    public void leggTilKunde(Kunde kunde){
        UUID kundeID = UUID.randomUUID();
        kunde.setKundeID(kundeID);
        kunder.put(kundeID, kunde);
    }

    public void leggTilKunder(Kunde ... kunder){
        for(Kunde kunde : kunder){
            leggTilKunde(kunde);
        }
    }

    // Oppgave: Lag en metode for å registrere en ny kunde
    public void leggTilKunde(String fornavn, String etternavn, int alder, String email, String passord){
        UUID kundeID = UUID.randomUUID();
        Kunde kunde = new Kunde(fornavn, etternavn, alder, email, passord);
        kunde.setKundeID(kundeID);
        kunder.put(kundeID, kunde);
    }

    // Oppgave: Lag en metode for å registrere en ny serie
    public void leggTilSerie(Serie serie){
        UUID serieID = UUID.randomUUID();
        serie.setSerieID(serieID);
        serier.put(serieID, serie);
    }

    public void leggTilSerier(Serie ... serier){
        for(Serie serie : serier){
            leggTilSerie(serie);
        }
    }

    // Oppgave: Lag en metode for å registrere en ny serie
    public void leggTilSerie(String tittel, Serietyper... serietyper){
        UUID serieID = UUID.randomUUID();
        Serie serie = new Serie(tittel, serietyper);
        serie.setSerieID(serieID);
        serier.put(serieID, serie);
    }

    // Oppgave: Lag en metode som returnerer en kunde basert på kundens ID.
    public Kunde getKunde(UUID kundeID) {
        if(!kunder.containsKey(kundeID)){
            throw new IllegalArgumentException(kundeID  + "er ikke en gyldig kundeID");
        }
        return kunder.get(kundeID);
    }

    public Serie getSerie(UUID serieID) {
        if(!serier.containsKey(serieID)){
            throw new IllegalArgumentException(serieID  + "er ikke en gyldig serieID");
        }
        return serier.get(serieID);
    }

    // Oppgave: Lag en metode som returnerer alle kunder som er interessert i en gitt serie, basert på seriens ID.
    public Collection<Kunde> getInteresserteKunder(UUID serieID) {
        Serie serie = this.serier.get(serieID);
        Collection<Serietyper> serietyper = serie.getSerieTyper();
        Collection<Kunde> allekunder = kunder.values();

        // går gjennom alle kunder og filtrerer ut de som ikke har noen serieinteresser som matcher seriens serietyper
        return allekunder.stream().sorted(Comparator.comparing(Kunde::toString)).filter(
                k -> !Collections.disjoint(serietyper, k.getSerieInteresser())).collect(Collectors.toList());
    }

    // Oppgave: Lag en metode som returnerer alle serier som har en gitt serietype, sortert alfabetisk på tittel.
    public Collection<Serie> getSerierAvSerietype(Serietyper serietype) {
        Collection<Serie> alleserier = serier.values();
        return alleserier.stream().sorted(Comparator.comparing(Serie::getTittel)).
                filter(s -> s.getSerieTyper().contains(serietype)).collect(Collectors.toList());

    }

    // Oppgave: Lag en metode som returnerer alle kunder som ble registrert mellom to datoer. (Trenger ikke sorteres.)
    public List<Kunde> getKunderFraTilDato(LocalDate fraDato, LocalDate tilDato) {
        Collection<Kunde> allekunder = kunder.values();
        return allekunder.stream().filter(k -> k.getRegisteringsdato().isAfter(ChronoLocalDateTime.from(fraDato))).
                filter(k -> k.getRegisteringsdato().isBefore(ChronoLocalDateTime.from(tilDato))).
                collect(Collectors.toList());
    }

    // Oppgave: Lag en metode som tar IDene for en serie og en kunde,
    // og returnerer interessevekten serien har for den kunden.
    public int getInteresseVekt(UUID kundeID, UUID serieID) {
        Collection<Serietyper> kundeInteresser = kunder.get(kundeID).getSerieInteresser();
        Collection<Serietyper> serietyper = serier.get(serieID).getSerieTyper();
        return (int) kundeInteresser.stream().filter(kundeint -> serietyper.contains(kundeint)).count();

    }

    // Oppgave: Lag en metode som returnerer den serien i systemet som har høyest total interessevekt.
    public Serie getMestPopulærSerie() {
        Serie mestPopSerie = null;
        int hoyesteScore = 0;
        for(UUID serieID: serier.keySet()){
            int score = 0;
            score += this.getInteresserteKunder(serieID).
                    stream().mapToInt(k-> getInteresseVekt(k.getKundeID(), serieID)).sum();
            if(score > hoyesteScore){
                hoyesteScore = score;
                mestPopSerie = serier.get(serieID);
            }
        }
        return mestPopSerie;
    }
}



