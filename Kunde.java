package bedrift;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Kunde {

    private String fornavn, etternavn, email, passord;
    private int alder, kid;
    private List<String> interesser = new ArrayList<>();
    private static int kidCounter = 0;
    private Date registeringsdato;


    public Kunde(String fornavn, String etternavn, int alder, String email, String passord){
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.alder = alder;
        this.email = email;
        this.passord = passord;
        this.kid = kidCounter;
        kidCounter++;
        this.registeringsdato = new Date();

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

    public int getKundeID(){
        return this.kid;
    }

    public Date getRegisteringsdato(){
        return this.registeringsdato;
    }

    public void leggTilSerieInteresse(String type){
        BedriftFlix.sjekkSerieType(type);
        if(!interesser.contains(type)){
            this.interesser.add(type);
        }
        // kan utløse IllegalArgumentException men vil ikke krasje programmet, ingen skade gjort
    }

    public void fjernSerieType(String type){
        if(interesser.contains(type)) {
            interesser.remove(type);
        }
        // kan utløse IllegalArgumentException men vil ikke krasje programmet, ingen skade gjort
    }

    public boolean erInteressert(Serie serie){
        for(String serietype:serie.getSerieTyper()){
            if((this.getSerieInteresser().contains(serietype))){
                return true;
            }
        }
        return false;
    }

    public List<String> getSerieInteresser(){
        return this.interesser;
    }


    @Override
    public String toString(){
        return this.fornavn + " " + this.etternavn;
    }
}
