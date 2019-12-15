package bedrift;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BedriftFlixTest {

    List<Serie> serier;
    List<Kunde> kunder;
    Kunde alex = new Kunde("Alexander", "Fredheim", 25, "alexannf@stud.ntnu.no",
            "123", Serietyper.Dokumentar, Serietyper.Krim,
            Serietyper.Humor, Serietyper.Drama, Serietyper.Talkshow);

    Kunde sindre = new Kunde("Sindre", "Sivertsen", 23, "sindre@mail.com",
            "111", Serietyper.Animasjon, Serietyper.Humor);

    Kunde simon = new Kunde("Simon", "Egaas", 25, "simon@mail.com", "222",
            Serietyper.Krim, Serietyper.Dokumentar);

    // TODO: legge til serietyper for resten
    Kunde amalie = new Kunde("Amalie", "Henni", 22, "amalie@mail.com", "333");
    Kunde even = new Kunde(
            "Even", "Skjellaug", 24, "even@mail.com", "444");
    Kunde marius = new Kunde(
            "Marius", "Fredheim", 20, "marius@mail.com", "555");

    Serie juliblodfjell = new Serie("Jul i Blodfjell", Serietyper.Krim, Serietyper.Humor);
    Serie exit = new Serie("Exit", Serietyper.Dokumentar, Serietyper.Drama, Serietyper.Humor);
    Serie planetearth = new Serie("Planet Earth", Serietyper.Dokumentar);
    Serie brille = new Serie("Brille", Serietyper.Talkshow, Serietyper.Humor);
    Serie whiplash = new Serie("Whiplash", Serietyper.Gameshow, Serietyper.Humor);
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

    }

    // Oppgave: Lag en metode som returnerer alle serier som har en gitt serietype, sortert alfabetisk på tittel.
    @Test
    void getSerieAvSerietype() {
    }

    // Oppgave: Lag en metode som returnerer alle kunder som ble registrert mellom to datoer. (Trenger ikke sorteres.)
    @Test
    void getKunderFraTilDato() {
    }

    // Oppgave: Lag en metode som tar IDene for en serie og en kunde,
    // og returnerer interessevekten serien har for den kunden.
    @Test
    void getInteresseVekt() {
    }

    // Oppgave: Lag en metode som returnerer den serien i systemet som har høyest total interessevekt.
    @Test
    void getMestPopulærSerie() {
    }
}