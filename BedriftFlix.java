package bedrift;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    Tilbakemelding:
    - Bruk en enum for å representere serietyper.
    - Bra bruk av exception for feilhåndtering.
    - Hvis metoder ikke gjør noe pga en sjekk, husk å rapportere til omkringliggende kode at noe gikk galt -
    gjerne vha exceptions. Ikke bare "feil stille", slik. feks leggTilKunde kan gjøre.
    - Comparable-interfacet skal brukes til å definere den naturlige ordningen av objektene av en klasse.
    Det er vanskelig å argumentere for at tittel er den "naturlige" ordningen til en serie,
    det er mange andre kandidater for sortering også. Derfor burde det heller vært brukt comparators for sortering her.
    Ta gjerne en titt på den nye enkle syntaksen for sortering vha comparators, med Comparator.comparing og ::-syntaks.
    - Vurder HashMap som alternativ til lister som datastruktur i BedriftFlix-klassen.
    - Jeg anbefaler at du setter deg inn i programmering med streams og lambdaer.
    - Jeg anbefaler at du setter deg inn i enhetstesting vha JUnit. Greit nok med main-testing i denne oppgaven,
    men faktisk enhetstesting er noe du vil få bruk for.
    - java.util.Date er utdatert i dag, ta en titt på klassene i java.time-pakken.
    - Ta en titt på UUID (både konseptet og java-klassen ved samme navn) for ID-generering.
     */


    private Map<UUID,Serie> serier = new HashMap<>();
    private Map<UUID,Kunde> kunder = new HashMap<>();


    // Oppgave: Lag en metode for å registrere en ny kunde
    public void leggTilKunde(Kunde kunde){
        UUID kundeID = UUID.randomUUID();
        kunder.put(kundeID, kunde);
    }

    // Oppgave: Lag en metode for å registrere en ny kunde
    public void leggTilKunde(String fornavn, String etternavn, int alder, String email, String passord){
        UUID kundeID = UUID.randomUUID();
        Kunde kunde = new Kunde(fornavn, etternavn, alder, email, passord);
        kunder.put(kundeID, kunde);
    }


    // Oppgave: Lag en metode for å registrere en ny serie
    public void leggTilSerie(Serie serie){
        UUID serieID = UUID.randomUUID();
        serier.put(serieID, serie);
    }

    // Oppgave: Lag en metode for å registrere en ny serie
    public void leggTilSerie(String tittel, Serietyper... serietyper){
        UUID serieID = UUID.randomUUID();
        Serie serie = new Serie(tittel, serietyper);
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
    /*

    // Oppgave: Lag en metode som returnerer alle kunder som er interessert i en gitt serie, basert på seriens ID.
    // TODO: skrive test, lage med streams
    public List<Kunde> getInteresserteKunder(Serie serie) {

    }

    // Oppgave: Lag en metode som returnerer alle serier som har en gitt serietype, sortert alfabetisk på tittel.
    // TODO: skrive test, lage med streams
    public List<Serie> getSerieAvSerietype(String serietype) {

    }

    // Oppgave: Lag en metode som returnerer alle kunder som ble registrert mellom to datoer. (Trenger ikke sorteres.)
    // TODO: skrive test, lage med streams
    public List<Kunde> getKunderFraTilDato(Date fraDato, Date tilDato) {

    }

    // Oppgave: Lag en metode som tar IDene for en serie og en kunde,
    // og returnerer interessevekten serien har for den kunden.
    // TODO: skrive test, lage med streams
    public int getInteresseVekt(UUID kundeID, UUID serieID) {

    }

    // Oppgave: Lag en metode som returnerer den serien i systemet som har høyest total interessevekt.
    // TODO: skrive test, lage med streams
    public Serie getMestPopulærSerie() {

    }


     */
    public static void main(String[] args) {










    }
}



