package bedrift;

import java.util.ArrayList;
import java.util.List;

public class Serie implements Comparable<Serie> {

    static int idCounter = 0;
    private int id;
    private List<String> serietyper = new ArrayList<>();
    private String tittel;

    public Serie(String tittel, String... serietyper){
        if(serietyper.length == 0) {
            throw new IllegalArgumentException("Serie må ha minst en serietype");
        }
        for(String serietype:serietyper){
            BedriftFlix.sjekkSerieType(serietype);
            this.serietyper.add(serietype);
        }
        this.tittel = tittel;
        this.id = idCounter;
        idCounter++;
    }

    public void leggTilSerieType(String type){
        BedriftFlix.sjekkSerieType(type);
        if(!serietyper.contains(type)){
            this.serietyper.add(type);
        }
        // kan utløse IllegalArgumentException men vil ikke krasje programmet, ingen skade gjort
    }

    public void fjernSerieType(String type){
        if(serietyper.contains(type)){
            if(serietyper.size()==1){
                throw new IllegalStateException("Du kan ikke fjerne den eneste serietypen");
            }
            else{
                serietyper.remove(type);
            }
        }
        // kan utløse IllegalArgumentException men vil ikke krasje programmet, ingen skade gjort
    }

    public String getTittel(){
        return this.tittel;
    }

    public int getSerieID(){
        return this.id;
    }

    public boolean erAvSerietype(String serietype){
        BedriftFlix.sjekkSerieType(serietype);
        if(serietyper.contains(serietype)){
            return true;
        }
        return false;
    }

    public List<String> getSerieTyper(){
        return this.serietyper;
    }

    @Override
    public String toString(){
        return this.tittel;
    }

    @Override
    public int compareTo(Serie o) {
        return this.getTittel().compareTo(o.getTittel());
    }
}
