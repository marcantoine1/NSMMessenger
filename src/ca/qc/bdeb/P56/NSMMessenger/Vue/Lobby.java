/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Guillaume
 */
public class Lobby extends JScrollPane {

    int numeroLobby;
    JTextArea text;
    private DefaultListModel lstModelUtilisateurs = new DefaultListModel();

    public DefaultListModel getLstModelUtilisateurs() {
        return lstModelUtilisateurs;
    }

    public void setLstModelUtilisateurs(DefaultListModel lstModelUtilisateurs) {
        this.lstModelUtilisateurs = lstModelUtilisateurs;
    }

    public Lobby(int numLobby, String NomLobby) {
        this.numeroLobby = numLobby;        
        text = new JTextArea(164, 94);
        text.setEditable(false);
        text.setRows(5);
        text.setColumns(20);
        text.setTabSize(8); 
        setName(NomLobby);
        setPreferredSize(new Dimension(166, 96));
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        getViewport().add(text, null);
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
