/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger.Observation;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerServer.LobbyDTO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Marc-Antoine
 */
public class ChatPrimitif extends javax.swing.JFrame {

    ChatGUI gui;
    private HashMap<Integer, Lobby> listePanneauLobby = new HashMap<>();
    private HashMap<String, Integer> lobbyID = new HashMap<>();
    /**
     * Creates new form ChatPrimitif
     */
    String imgPath = "../../ressources/iconeMSN.png";
    ImageIcon img = new ImageIcon(imgPath);

    public ChatPrimitif(ChatGUI gui) {
        this.gui = gui;
        initComponents();
        TabPanelSalons.removeTabAt(0);
        TabPanelSalons.updateUI();
        try {
            //this.setIconImage(ImageIO.read(getClass().getResourceAsStream(imgPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajouterEventTxtBox();
        ajouterEventTabPanel();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabPnlInfo = new javax.swing.JTabbedPane();
        PnlSalon = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstLobby = new javax.swing.JList();
        jButton3 = new javax.swing.JButton();
        btnVoirProfile = new javax.swing.JButton();
        PnlContacts = new javax.swing.JPanel();
        PnlUtilisateur = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstUtilisateurs = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        btnEnvoyer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextPane();
        TabPanelSalons = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        lblChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NSM Messenger");
        setMinimumSize(new java.awt.Dimension(800, 450));

        jPanel1.setBackground(new java.awt.Color(0, 83, 186));

        jButton1.setText("Créer un lobby");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Joindre");
        jButton2.setToolTipText("Sélectionner un lobby afin de le joindre");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lstLobby.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstLobby.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstLobbyValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(lstLobby);

        jButton3.setText("Quitter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnVoirProfile.setText("Voir mon profile");
        btnVoirProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoirProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlSalonLayout = new javax.swing.GroupLayout(PnlSalon);
        PnlSalon.setLayout(PnlSalonLayout);
        PnlSalonLayout.setHorizontalGroup(
            PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSalonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnlSalonLayout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnVoirProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        PnlSalonLayout.setVerticalGroup(
            PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSalonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVoirProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPnlInfo.addTab("Salon", PnlSalon);

        javax.swing.GroupLayout PnlContactsLayout = new javax.swing.GroupLayout(PnlContacts);
        PnlContacts.setLayout(PnlContactsLayout);
        PnlContactsLayout.setHorizontalGroup(
            PnlContactsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );
        PnlContactsLayout.setVerticalGroup(
            PnlContactsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );

        tabPnlInfo.addTab("Contacts", PnlContacts);

        lstUtilisateurs.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lstUtilisateurs);

        javax.swing.GroupLayout PnlUtilisateurLayout = new javax.swing.GroupLayout(PnlUtilisateur);
        PnlUtilisateur.setLayout(PnlUtilisateurLayout);
        PnlUtilisateurLayout.setHorizontalGroup(
            PnlUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlUtilisateurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );
        PnlUtilisateurLayout.setVerticalGroup(
            PnlUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlUtilisateurLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabPnlInfo.addTab("Utilisateur", PnlUtilisateur);

        jPanel3.setBackground(new java.awt.Color(0, 83, 186));

        btnEnvoyer.setText("Envoyer");
        btnEnvoyer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnvoyerMouseClicked(evt);
            }
        });

        jScrollPane1.setViewportView(txtChat);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(btnEnvoyer, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
        );

        jScrollPane2.setBackground(new java.awt.Color(51, 153, 255));

        lblChat.setEditable(false);
        lblChat.setColumns(20);
        lblChat.setRows(5);
        lblChat.setLineWrap(true);
        lblChat.setWrapStyleWord(true);
        jScrollPane2.setViewportView(lblChat);

        TabPanelSalons.addTab("Salon 1", jScrollPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TabPanelSalons, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPnlInfo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TabPanelSalons)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabPnlInfo.getAccessibleContext().setAccessibleName("Contact");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnEnvoyerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnvoyerMouseClicked
        //todo: lobby
        gui.aviserObservateurs(Observation.ENVOIMESSAGE, new Message(getCurrentLobby(), txtChat.getText(), null));
        txtChat.setText("");
    }//GEN-LAST:event_btnEnvoyerMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CreerLobby cl = new CreerLobby(this, true);
        cl.setVisible(true);
        if (cl.getFlagCreer()) {
            gui.aviserObservateurs(Observation.CREERLOBBY, cl.getNomLobby());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void lstLobbyValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstLobbyValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_lstLobbyValueChanged

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

    }//GEN-LAST:event_jButton2MouseClicked

    private void btnVoirProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoirProfileActionPerformed
        Object ob;
        String nom;
        ob = lstUtilisateurs.getModel().getElementAt(lstUtilisateurs.getModel().getSize() - 1);
        nom = ob.toString();
        PageProfile pp = new PageProfile(nom);
        pp.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        pp.pack();
        pp.setVisible(true);

    }//GEN-LAST:event_btnVoirProfileActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String lobbyName = lstLobby.getSelectedValue().toString();
        int id = lobbyID.get(lobbyName);
        if (!listePanneauLobby.containsKey(id)) {
            gui.aviserObservateurs(Observation.JOINLOBBY, id);
        }
     }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (lstLobby.getSelectedValue() != null) {
            String lobbyName = lstLobby.getSelectedValue().toString();
            int id = lobbyID.get(lobbyName);
            if (listePanneauLobby.containsKey(id)) {
                quitterSalon(listePanneauLobby.get(id).numeroLobby, listePanneauLobby.get(id).getName());
                gui.aviserObservateurs(Observation.LEAVELOBBY, id);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlContacts;
    private javax.swing.JPanel PnlSalon;
    private javax.swing.JPanel PnlUtilisateur;
    private javax.swing.JTabbedPane TabPanelSalons;
    private javax.swing.JButton btnEnvoyer;
    private javax.swing.JButton btnVoirProfile;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea lblChat;
    private javax.swing.JList lstLobby;
    private javax.swing.JList lstUtilisateurs;
    private javax.swing.JTabbedPane tabPnlInfo;
    private javax.swing.JTextPane txtChat;
    // End of variables declaration//GEN-END:variables

    public void ajouterMessage(int lobby, String user, String s) {
        listePanneauLobby.get(lobby).ajouterMessage("\n" + user + " : " + s);
        //lblChat.setText(lblChat.getText() + "\n" + user + " : " + s);
    }

    public void notifierConnectionClient(int id, String nom) {
        if (listePanneauLobby.get(id) != null) {
            listePanneauLobby.get(id).ajouterMessage("\n" + nom + " s'est connecté au lobby.");
            listePanneauLobby.get(id).getLstModelUtilisateurs().addElement(nom);
            if (getCurrentLobby() == id) {
                lstUtilisateurs.setModel(listePanneauLobby.get(id).getLstModelUtilisateurs());
            }
        }

    }

    public void notifierDeconnectionClient(int id, String nom) {
        if (listePanneauLobby.get(id) != null) {
            listePanneauLobby.get(id).ajouterMessage("\n" + nom + " s'est déconnecté du lobby.");
            listePanneauLobby.get(id).getLstModelUtilisateurs().removeElement(nom);
            if (getCurrentLobby() == id) {
                lstUtilisateurs.setModel(listePanneauLobby.get(id).getLstModelUtilisateurs());
            }
        }
    }

    public void ajouterEventTxtBox() {
        txtChat.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    evt.consume();
                    btnEnvoyerMouseClicked(null);
                }
            }
        });
    }

    public void ajouterSalon(int numLobby, String nomSalon) {
        Lobby nouveauLobby = new Lobby(numLobby, nomSalon);
        TabPanelSalons.add(nouveauLobby);
        listePanneauLobby.put(numLobby, nouveauLobby);
    }

    public void quitterSalon(int numLobby, String nomSalon) {
        for (int i = 0; i < listePanneauLobby.size(); i++) {
            TabPanelSalons.remove(listePanneauLobby.get(numLobby));
            listePanneauLobby.remove(numLobby);
        }
    }

    public void updateLobbies(LobbyDTO[] lobbies) {
        DefaultListModel lm = new DefaultListModel();

        for (int i = 0; i < lobbies.length; i++) {
            lm.addElement(lobbies[i].name);
            lobbyID.put(lobbies[i].name, lobbies[i].id);
        }
        lstLobby.setModel(lm);

    }

    public int getCurrentLobby() {
        return ((Lobby) TabPanelSalons.getSelectedComponent()).numeroLobby;
    }

    public void lobbyJoined(ArrayList<String> liste, String nomLobby) {
        ajouterSalon(lobbyID.get(nomLobby), nomLobby);

        DefaultListModel lm = listePanneauLobby.get(lobbyID.get(nomLobby)).getLstModelUtilisateurs();
        for (String s : liste) {
            lm.addElement(s);
        }
        lstUtilisateurs.setModel(lm);

    }

    public void nouvelUtilisateurLobby(int id, String nom) {

    }

    public JButton getButton() {
        return this.btnEnvoyer;
    }

    public JTextPane getChat() {
        return this.txtChat;
    }

    private void ajouterEventTabPanel() {
        TabPanelSalons.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                lstUtilisateurs.setModel(((Lobby) TabPanelSalons.getSelectedComponent()).getLstModelUtilisateurs());
            }
        });
    }
}
