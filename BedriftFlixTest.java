package bedrift;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class BedriftFlixTest {

    private final BedriftFlix bedflixtest = new BedriftFlix();

    Kunde alex = new Kunde("Alexander", "Fredheim", 25, "alexannf@stud.ntnu.no",
            "123", Serietyper.Dokumentar, Serietyper.Krim,
            Serietyper.Humor, Serietyper.Drama, Serietyper.Talkshow);

    Kunde sindre = new Kunde("Sindre", "Sivertsen", 23, "sindre@mail.com",
            "111", Serietyper.Dokumentar, Serietyper.Humor);

    Kunde simon = new Kunde("Simon", "Egaas", 25, "simon@mail.com", "222",
            Serietyper.Krim, Serietyper.Dokumentar);

    Kunde amalie = new Kunde("Amalie", "Henni", 22, "amalie@mail.com", "333",
            Serietyper.Humor, Serietyper.Animasjon, Serietyper.Gameshow);

    Kunde even = new Kunde(
            "Even", "Skjellaug", 24, "even@mail.com", "444",
            Serietyper.Dokumentar, Serietyper.Reality);

    Kunde marius = new Kunde(
            "Marius", "Fredheim", 20, "marius@mail.com", "555",
            Serietyper.Animasjon, Serietyper.Reality, Serietyper.Gameshow);

    Serie juliblodfjell = new Serie("Jul i Blodfjell", Serietyper.Krim, Serietyper.Humor);
    Serie exit = new Serie("Exit", Serietyper.Dokumentar, Serietyper.Drama, Serietyper.Humor);
    Serie planetearth = new Serie("Planet Earth", Serietyper.Dokumentar);
    Serie brille = new Serie("Brille", Serietyper.Talkshow, Serietyper.Humor);
    Serie wipeout = new Serie("Wipeout", Serietyper.Gameshow, Serietyper.Humor);
    Serie rickandmorty = new Serie("Rick & Morty", Serietyper.Animasjon, Serietyper.Humor);
    Serie paradisehotel = new Serie("Paradise Hotel", Serietyper.Reality);
    Serie dexter  = new Serie("Dexter", Serietyper.Krim, Serietyper.Drama);
    Serie nyttpanytt = new Serie("Nytt på nytt", Serietyper.Talkshow, Serietyper.Humor);

    @Before
    public void init(){

    }

    @After
    public void after(){

    }

    // Oppgave: Lag en metode som returnerer alle kunder som er interessert i en gitt serie, basert på seriens ID.
    @Test
    void getInteresserteKunder() {
        bedflixtest.leggTilKunder(alex, amalie, sindre, even);

        // planet earth er kun dokumentar
        bedflixtest.leggTilSerie(planetearth);
        Collection<Kunde> likerPE = Arrays.asList(alex, even, sindre);
        Collection<Kunde> intrKunderPE = bedflixtest.getInteresserteKunder(planetearth.getSerieID());
        assertEquals(likerPE, intrKunderPE);

        // rick and morty er både humor og animasjon
        bedflixtest.leggTilSerie(rickandmorty);
        Collection<Kunde> likerRickAndMorty = Arrays.asList(alex, amalie, sindre);
        Collection<Kunde> intrKunderRM = bedflixtest.getInteresserteKunder(rickandmorty.getSerieID());
        assertEquals(likerRickAndMorty, intrKunderRM);
    }

    // Oppgave: Lag en metode som returnerer alle serier som har en gitt serietype, sortert alfabetisk på tittel.
    @Test
    void getSerierAvSerietype() {
        bedflixtest.leggTilSerier(planetearth, dexter, exit, nyttpanytt);
        Collection<Serie> erDokumentar = Arrays.asList(exit, planetearth);
        Collection<Serie> doku = bedflixtest.getSerierAvSerietype(Serietyper.Dokumentar);
        assertEquals(erDokumentar, doku);

        Collection<Serie> erHumor = Arrays.asList(exit, nyttpanytt);
        Collection<Serie> hum = bedflixtest.getSerierAvSerietype(Serietyper.Humor);
        assertEquals(erHumor, hum);
    }

    // Oppgave: Lag en metode som returnerer alle kunder som ble registrert mellom to datoer. (Trenger ikke sorteres.)
    @Test
    void getKunderFraTilDato() {
    }

    // Oppgave: Lag en metode som tar IDene for en serie og en kunde,
    // og returnerer interessevekten serien har for den kunden.
    @Test
    void getInteresseVekt() {
        bedflixtest.leggTilKunder(alex, amalie, sindre, even);

        //exit har både humor, dokumentar og drama
        bedflixtest.leggTilSerie(exit);

        int amalieScore = bedflixtest.getInteresseVekt(amalie.getKundeID(), exit.getSerieID());
        assertEquals(1, amalieScore);

        int alexScore = bedflixtest.getInteresseVekt(alex.getKundeID(), exit.getSerieID());
        assertEquals(3, alexScore);

        int sindreScore = bedflixtest.getInteresseVekt(sindre.getKundeID(), exit.getSerieID());
        assertEquals(2, sindreScore);

    }

    // Oppgave: Lag en metode som returnerer den serien i systemet som har høyest total interessevekt.
    @Test
    void getMestPopulærSerie() {
        bedflixtest.leggTilKunder(alex, amalie);
        bedflixtest.leggTilSerier(planetearth, wipeout);
        // wipeout score = 3, planetearth score = 1

        Serie serie1 = bedflixtest.getMestPopulærSerie();
        assertEquals(wipeout, serie1);

        bedflixtest.leggTilSerie(exit);
        // exit score = 4
        assertEquals(exit, bedflixtest.getMestPopulærSerie());
    }
}