/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;

/**
 *
 * @author Guillaume
 */
public class Lobby extends JScrollPane {

    int numeroLobby;
    JScrollPane panneau;
    JTextArea text;

    public Lobby(int numLobby, String NomLobby) {
        this.numeroLobby = numLobby;        
        text = new JTextArea(164, 94);
        text.setEditable(false);
        text.setRows(5);
        text.setColumns(20);
        text.setTabSize(8); 
        panneau = new JScrollPane();
        panneau.setName(NomLobby);
        panneau.setPreferredSize(new Dimension(166, 96));
        panneau.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panneau.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panneau.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panneau.getViewport().add(text, null);
    }

    public JScrollPane getPanneau() {
        return panneau;
    }
    public Lobby getLobby(){
        return this;
    }
    public int getNumLobby(){
        return this.numeroLobby;
    }
    public void ajouterMessage(String message){
        this.text.setText(text.getText() + message);
    }
}