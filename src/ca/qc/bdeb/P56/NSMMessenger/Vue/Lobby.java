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
public class Lobby {

    JScrollPane panel = new JScrollPane();
    JTextArea text;
    private DefaultListModel lstModelUtilisateurs = new DefaultListModel();

    public DefaultListModel getLstModelUtilisateurs() {
        return lstModelUtilisateurs;
    }

    public void setLstModelUtilisateurs(DefaultListModel lstModelUtilisateurs) {
        this.lstModelUtilisateurs = lstModelUtilisateurs;
    }

    public Lobby(String NomLobby) {     
        text = new JTextArea(164, 94);
        text.setEditable(false);
        text.setRows(5);
        text.setColumns(20);
        text.setTabSize(8); 
        panel.setName(NomLobby);
        panel.setPreferredSize(new Dimension(166, 96));
        panel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.getViewport().add(text, null);
    }

    public JScrollPane getPanel()
    {
        return panel;
    }
    
    public void ajouterMessage(String message){
        this.text.setText(text.getText() + message);
    }
}
