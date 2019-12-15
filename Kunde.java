package bedrift;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Kunde {

    private String fornavn, etternavn, email, passord;
    private int alder;
    private List<Serietyper> interesser = new ArrayList<>();
    private LocalDateTime registeringsdato;


    public Kunde(String fornavn, String etternavn, int alder, String email, String passord){
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.alder = alder;
        this.email = email;
        this.passord = passord;
        this.registeringsdato = LocalDateTime.now();

    }

    public Kunde(String fornavn, String etternavn, int alder, String email, String passord, Serietyper ... serietyper){
        this.interesser = List.of(serietyper);
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.alder = alder;
        this.email = email;
        this.passord = passord;
        this.registeringsdato = LocalDateTime.now();

    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getEmail() {
        return email;
    }

    public int getAlder() {
        return alder;
    }

    public LocalDateTime getRegisteringsdato(){
        return this.registeringsdato;
    }

    // utløser IllegalArgumentException om serie interessen allerede finnes for denne kunden
    public void leggTilSerieInteresse(Serietyper serietype){
        if(interesser.contains(serietype)){
            throw new IllegalArgumentException(serietype + " er allerede en serieInteresse hos " + this);
        }
        this.interesser.add(serietype);
    }

    public void leggTilSerieInteresser(Serietyper ... serietyper){
        for(Serietyper serietype : serietyper){
            if(interesser.contains(serietype)){
                throw new IllegalArgumentException(serietype + " er allerede en serieInteresse hos " + this);
            }
            this.interesser.add(serietype);
        }
    }

    // utløser IllegalArgumentException om serie-interessen ikke finnes for denne kunden
    public void fjernSerieInteresse(Serietyper serietype){
        if(!interesser.contains(serietype)) {
            throw new IllegalArgumentException(serietype + " er ikke en serieInteresse hos " + this);
        }
        interesser.remove(serietype);
    }

    public List<Serietyper> getSerieInteresser(){
        return this.interesser;
    }


    @Override
    public String toString(){
        return this.fornavn + " " + this.etternavn;
    }

    public static void main(String[] args) {
        Kunde alex = new Kunde(
                "Alexander", "Fredheim", 25, "alexannf@stud.ntnu.no",
                "123", Serietyper.Humor, Serietyper.Dokumentar, Serietyper.Talkshow);

    System.out.println(alex.getSerieInteresser());

    }

}
