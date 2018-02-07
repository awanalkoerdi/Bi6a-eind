package bi6a.eind;

import java.io.*;
import java.util.Map;
import java.util.*;
import javax.swing.*;

public class VirusLogica {
    
    
   /** Initialiseren en declareren van variabelen
    *
    */
    static Set<String> classiflist = new HashSet<>();
    static Set<String> hosts = new HashSet<>();
    static HashMap<String, HashSet<String>> hostmap = new HashMap<>();
    static HashSet<Integer> hostlijst = new HashSet<>();
    static List<Virus> virusList = new ArrayList<>();
    static ArrayList<Virus> HostVirus = new ArrayList<>();
    static Set<Integer> hostset = new HashSet<>();

    /**Methode waarin bestand wordt gelezen. Het estand wordt in een string gezet en vervolgens wordt het gelezen.
     * Verder worden indexen uit het bestand in een string of een lijst gezet.
     * @param selectedFile
     * @return 
     */
    public static List<Virus> readFile(File selectedFile) {

        try {
            String line;
            BufferedReader inFile;
            inFile = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()));
            String[] array;
            inFile.readLine();

            while ((inFile.readLine()) != null) {
                line = inFile.readLine();
                array = line.split("\\t", -1);

                if (!array[7].isEmpty()) {

                    int virusID = Integer.parseInt(array[0]);
                    String virusNaam = array[1];
                    String classificatie = array[2];
                    String[] larray = classificatie.split(";");
                    int hostID = Integer.parseInt(array[7]);

                    //System.out.println(hostID);
                    String hostNaam = array[8];
                    String HostIDList = (array[7] + '(' + array[8] + ')');

                    hostlijst.add(hostID);
                    classiflist.add(larray[1]);
                    hosts.add(HostIDList);
                    hostmap.get(hosts);

                    //hulp Lotte
                    if (!hostmap.containsKey(classiflist)) {
                        hostmap.put(array[2], (HashSet<String>) hosts);
                    } else {
                        hostmap.get(array[2]).add(HostIDList);
                    }
                    
                    
                    virusList.add(new Virus(virusID, virusNaam, classificatie, 0, hostNaam, hostlijst));
                }
            }
            inFile.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File Error: " + e.toString());
        }
        return virusList;
    }

    /**Methode die steeds iets toevoegd aan een lijst wanneer aangeroepen.
     * 
     * @param v
     * @return 
     */
    //hulp Rivka Vrolijk
    public HashSet<Integer> getHostVirus(Virus v) {
        return v.getVirushostList();
    }
    
    /**Methode die comboboxen vult wanneer aangeroepen in de gui.
     * Kijkt naar virus in de lijst met alle variabelen.
     * 
     * @param classification
     * @return 
     */
    public static Set<Integer> boxVullen(String classification) {
        for (Virus v : virusList) {
            if (v.getClassificatie().contains(classification)) {
                for (int i = 0; i < HostVirus.size(); i++) {
                    hostset.add(v.getHostID());
                }
            }
        }
        return hostset; 
    }
    
    
    
}