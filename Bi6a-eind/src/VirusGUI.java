/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bi6a.eind;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


/**GUI waarmee de applicatie wordt gemaakt.
 * 
 * @author Gebruiker
 */
public class VirusGUI extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel url, classif, hostid, sorteer, vl1, vl2, overeenkomstl;
    private JTextField urlfield;
    private JButton search, open, vergelijk;
    private JComboBox<String> classifbox, host1, host2, sorteerbox;
    private JTextArea lijst1, lijst2, overeenkomst;
    private List<Virus> virusList = new java.util.ArrayList<>();
    private ArrayList<String> classificatielijst;

    JFileChooser fileChooser;
    File selectedFile;

    /**Methode waarin de frame wordt vastgesteld en zichtbaar wordt gemaakt.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        VirusGUI frame = new VirusGUI();
        frame.setSize(570, 450);
        frame.setTitle("Virus Applicatie");
        frame.createGUI();
        frame.show();
        frame.setVisible(true);
    }

    /**Methode waarbij de gui wordt gecreeerd.
     * 
     */
    public void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        panel = new JPanel();
        window.add(panel);

        url = new JLabel("File or URL:");
        urlfield = new JTextField();
        urlfield.setPreferredSize(new Dimension(300, 20));
        search = new JButton("Browse");
        open = new JButton("Open");
        search.addActionListener(this);
        open.addActionListener(this);

        window.add(url);
        window.add(urlfield);
        window.add(search);
        window.add(open);

        classif = new JLabel("                  Viral Classification:     ");
        classifbox = new JComboBox();
        classifbox.setVisible(rootPaneCheckingEnabled);
        classifbox.setPreferredSize(new Dimension(350, 20));
        classifbox.addActionListener(this);

        window.add(classif);
        window.add(classifbox);

        hostid = new JLabel("      Host ID:   ");
        host1 = new JComboBox();
        host1.setVisible(true);
        host1.setPreferredSize(new Dimension(225, 20));
        host2 = new JComboBox();
        host2.setVisible(true);
        host2.setPreferredSize(new Dimension(225, 20));

        window.add(hostid);
        window.add(host1);
        window.add(host2);

        sorteer = new JLabel("     Sorteer:    ");
        sorteerbox = new JComboBox();
        sorteerbox.setVisible(true);
        sorteerbox.setPreferredSize(new Dimension(450, 20));
        window.add(sorteer);
        window.add(sorteerbox);

        vl1 = new JLabel("                        Virus 1                             ");
        lijst1 = new JTextArea();
        lijst1.setPreferredSize(new Dimension(200, 100));
        vl2 = new JLabel("                        Virus 2                              ");
        lijst2 = new JTextArea();
        lijst2.setPreferredSize(new Dimension(200, 100));
        vergelijk = new JButton("Vergelijk");
        vergelijk.setPreferredSize(new Dimension(400, 20));

        window.add(vl1);
        window.add(vl2);
        window.add(lijst1);

        window.add(lijst2);
        window.add(vergelijk);

        overeenkomstl = new JLabel("                 Overeenkomsten virus 1 en virus 2                 ");
        overeenkomst = new JTextArea();
        overeenkomst.setPreferredSize(new Dimension(300, 100));

        window.add(overeenkomstl);
        window.add(overeenkomst);

    }

    /**Methode waarbij er actie wordt gevoerd nadat een bepaald iets wordt geselecteerd in de combobox.
     * 
     */
    public void selectBox() {

        int select = classifbox.getSelectedIndex();
        if(select == 0){
            VirusLogica.boxVullen("ssDNA");
            
        } 

    }
    
    /**Buttons en Boxen etc. worden gekoppeld aan de actionlistener.
     * Hierbij kunnen functies worden gegeven aan deze variabelen.
     * 
     * @param evt 
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent evt) {

        if (evt.getSource() == search) {
            fileChooser = new JFileChooser();
            int reply = fileChooser.showOpenDialog(this);
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                urlfield.setText(selectedFile.getAbsolutePath());
            }
        }

        if (evt.getSource() == open) {
            selectedFile = new File(urlfield.getText());
            virusList = VirusLogica.readFile(selectedFile);

            classificatielijst = new ArrayList<>();
            classificatielijst.add("none");
            classificatielijst.add("ssDNA");
            classificatielijst.add("dsDNA");
            classificatielijst.add("ssRNA");
            classificatielijst.add("dsRNA");
            classificatielijst.add("Retrovirus");
            classificatielijst.add("Viroid");
            classifbox.setModel(new DefaultComboBoxModel(classificatielijst.toArray()));
            classificatielijst.clear();
        }
        

        if (evt.getSource() == classifbox) {
            String box1 = classifbox.getSelectedItem().toString();
            String box2 = classifbox.getSelectedItem().toString();
            
            

            host1.setModel(new DefaultComboBoxModel(VirusLogica.hostset.toArray()));
            host2.setModel(new DefaultComboBoxModel(VirusLogica.hostset.toArray()));
        }
    }
}
