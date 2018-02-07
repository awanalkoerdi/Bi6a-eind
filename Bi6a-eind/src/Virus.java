package bi6a.eind;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Virus {

    /**initialiseerd en declareerd variabalen.
     * 
     */
    private int VirusID;
    private String VirusNaam;
    private String classificatie;
    private int HostID;
    private String HostNaam;
    private HashSet<Integer> virushostList = new HashSet<>();
    
    /**Constructor waarin de rest van de variabelen wordt gedeclareerd.
     * 
     * @param VirusID
     * @param VirusNaam
     * @param classificatie
     * @param HostID
     * @param HostNaam
     * @param virushostList 
     */
    
    Virus(int VirusID, String VirusNaam, String classificatie, int HostID, String HostNaam, HashSet<Integer> virushostList){
        this.VirusID = VirusID;
        this.VirusNaam = VirusNaam;
        this.classificatie = classificatie;
        this.HostID = HostID;
        this.HostNaam = HostNaam;
        this.virushostList = virushostList;
    }


    

    //hulp Rivka
    public HashSet<Integer> getVirushostList() {
        return virushostList;
    }

    public void addVirushostList(Integer host) {
        this.virushostList.add(host);
    }

    

    public void setVirusID(int VirusID) {
        this.VirusID = VirusID;
    }

    public void setVirusNaam(String VirusNaam) {
        this.VirusNaam = VirusNaam;
    }

    public void setClassificatie(String classificatie) {
        this.classificatie = classificatie;
    }

    public void setHostID(int HostID) {
        this.HostID = HostID;
    }

    public void setHostNaam(String HostNaam) {
        this.HostNaam = HostNaam;
    }

    public int getVirusID() {
        return VirusID;
    }

    public String getVirusNaam() {
        return VirusNaam;
    }

    public String getClassificatie() {
        return classificatie;
    }

    public int getHostID() {
        return HostID;
    }

    public String getHostNaam() {
        return HostNaam;
    }

    
    
    
    
}
