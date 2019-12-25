package bedrift;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class Serie{

    private Collection<Serietyper> serietyper = new ArrayList<>();
    private String tittel;
    private UUID serieID;

    // utløser IllegalArgumentException for initsialisering uten serietype(r)
    public Serie(String tittel, Serietyper... serietyper){
        if(serietyper.length == 0) {
            throw new IllegalArgumentException(tittel + " må ha minst en serietype");
        }
        for(Serietyper serietype:serietyper){
            this.serietyper.add(serietype);
        }
        this.tittel = tittel;
    }

    // Oppgave: Lag passende metoder for å legge til og/eller fjerne serietyper på en serie.

    // utløser IllegalArgumentException om serietypen vi legger til allerede er registrert på objektet
    public void leggTilSerieType(Serietyper serietype){
        if(serietyper.contains(serietype)){
            throw new IllegalArgumentException(serietype + " er allerede lagt til for " + tittel);
        }
        this.serietyper.add(serietype);
    }

    // utløser IllegalArgumentException om serietypen vi prøver å slette ikke finnes eller hvis vi kun har én serietype
    public void fjernSerieType(Serietyper serietype){
        if(!serietyper.contains(serietype)){
            throw new IllegalArgumentException(serietype + " er ikke en serietype for " + tittel);
        }
        else{
            if(serietyper.size()==1){
                throw new IllegalStateException("Du kan ikke fjerne den eneste serietypen");
            }
            serietyper.remove(serietype);
        }
    }

    public String getTittel(){
        return this.tittel;
    }

    public Collection<Serietyper> getSerieTyper(){
        return this.serietyper;
    }

    public void setSerieID(UUID serieID){
        this.serieID = serieID;
    }

    public UUID getSerieID(){
        return this.serieID;
    }

    @Override
    public String toString(){
        return this.tittel;
    }

}
