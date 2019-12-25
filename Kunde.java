package bedrift;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

public class Kunde {

    private String fornavn, etternavn, email, passord;
    private int alder;
    private Collection<Serietyper> interesser = new ArrayList<>();
    private LocalDateTime registeringsdato;
    private UUID kundeID;


    public Kunde(String fornavn, String etternavn, int alder, String email, String passord){
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.alder = alder;
        this.email = email;
        this.passord = passord;
        this.registeringsdato = LocalDateTime.now();

    }

    public Kunde(String fornavn, String etternavn, int alder, String email, String passord, Serietyper ... serietyper){
        this.interesser = Arrays.asList(serietyper);
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

    public void setKundeID(UUID kundeID){
        this.kundeID = kundeID;
    }

    public UUID getKundeID(){
        return this.kundeID;
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

    public Collection<Serietyper> getSerieInteresser(){
        return this.interesser;
    }


    @Override
    public String toString(){
        return this.fornavn + " " + this.etternavn;
    }
}