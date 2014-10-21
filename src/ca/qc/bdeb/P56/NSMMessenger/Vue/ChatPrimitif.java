/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger.Observation;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Marc-Antoine
 */
public class ChatPrimitif extends javax.swing.JFrame {

    private ChatGUI gui;
    private HashMap<String, Lobby> listePanneauLobby = new HashMap<>();
    /*
     * Creates new form ChatPrimitif
     */
    private String imgPath = "../../ressources/iconeMSN.png";
    ImageIcon img = new ImageIcon(imgPath);

    public ChatPrimitif(ChatGUI gui) {
        this.gui = gui;
        initComponents();
        this.setResizable(false);
        TabPanelSalons.removeTabAt(0);
        TabPanelSalons.updateUI();
        try {
            //this.setIconImage(ImageIO.read(getClass().getResourceAsStream(imgPath)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ajouterEventTxtBox();
        ajouterEventTabPanel();
        ajouterEventLstUtilisateur();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFondChat = new javax.swing.JPanel();
        tabPnlInfo = new javax.swing.JTabbedPane();
        PnlSalon = new javax.swing.JPanel();
        btnCreerLobby = new javax.swing.JButton();
        btnJoindreLobby = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstLobby = new javax.swing.JList();
        btnQuitter = new javax.swing.JButton();
        PnlContacts = new javax.swing.JPanel();
        PnlUtilisateur = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstUtilisateurs = new javax.swing.JList();
        pnlMessage = new javax.swing.JPanel();
        btnEnvoyer = new javax.swing.JButton();
        scrollPaneMessage = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextPane();
        TabPanelSalons = new javax.swing.JTabbedPane();
        scrollPaneLobby = new javax.swing.JScrollPane();
        lblChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NSM Messenger");
        setMinimumSize(new java.awt.Dimension(800, 450));

        pnlFondChat.setBackground(new java.awt.Color(0, 83, 186));

        btnCreerLobby.setText("Créer un lobby");
        btnCreerLobby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreerLobbyActionPerformed(evt);
            }
        });

        btnJoindreLobby.setText("Joindre");
        btnJoindreLobby.setToolTipText("Sélectionner un lobby afin de le joindre");
        btnJoindreLobby.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoindreLobbyActionPerformed(evt);
            }
        });

        lstLobby.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(lstLobby);

        btnQuitter.setText("Quitter");
        btnQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PnlSalonLayout = new javax.swing.GroupLayout(PnlSalon);
        PnlSalon.setLayout(PnlSalonLayout);
        PnlSalonLayout.setHorizontalGroup(
            PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSalonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PnlSalonLayout.createSequentialGroup()
                        .addComponent(btnJoindreLobby, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnCreerLobby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        PnlSalonLayout.setVerticalGroup(
            PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSalonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCreerLobby, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnJoindreLobby, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuitter, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        pnlMessage.setBackground(new java.awt.Color(0, 83, 186));

        btnEnvoyer.setText("Envoyer");
        btnEnvoyer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnvoyerMouseClicked(evt);
            }
        });

        scrollPaneMessage.setViewportView(txtChat);

        javax.swing.GroupLayout pnlMessageLayout = new javax.swing.GroupLayout(pnlMessage);
        pnlMessage.setLayout(pnlMessageLayout);
        pnlMessageLayout.setHorizontalGroup(
            pnlMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMessageLayout.createSequentialGroup()
                .addComponent(scrollPaneMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEnvoyer, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMessageLayout.setVerticalGroup(
            pnlMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMessageLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMessage)
                    .addComponent(btnEnvoyer, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)))
        );

        scrollPaneLobby.setBackground(new java.awt.Color(51, 153, 255));

        lblChat.setEditable(false);
        lblChat.setColumns(20);
        lblChat.setRows(5);
        lblChat.setLineWrap(true);
        lblChat.setWrapStyleWord(true);
        scrollPaneLobby.setViewportView(lblChat);

        TabPanelSalons.addTab("Salon 1", scrollPaneLobby);

        javax.swing.GroupLayout pnlFondChatLayout = new javax.swing.GroupLayout(pnlFondChat);
        pnlFondChat.setLayout(pnlFondChatLayout);
        pnlFondChatLayout.setHorizontalGroup(
            pnlFondChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFondChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TabPanelSalons, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabPnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFondChatLayout.setVerticalGroup(
            pnlFondChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFondChatLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFondChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabPnlInfo)
                    .addGroup(pnlFondChatLayout.createSequentialGroup()
                        .addComponent(TabPanelSalons)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tabPnlInfo.getAccessibleContext().setAccessibleName("Contact");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondChat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFondChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnEnvoyerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnvoyerMouseClicked
        gui.aviserObservateurs(Observation.ENVOIMESSAGE, new Message(getCurrentLobby(), txtChat.getText(), null));
        txtChat.setText("");
    }//GEN-LAST:event_btnEnvoyerMouseClicked

    private void btnCreerLobbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreerLobbyActionPerformed
        CreerLobby cl = new CreerLobby(this, true);
        cl.setVisible(true);
        if (cl.getFlagCreer()) {
            gui.aviserObservateurs(Observation.CREERLOBBY, cl.getNomLobby());
        }
    }//GEN-LAST:event_btnCreerLobbyActionPerformed

    private void btnJoindreLobbyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoindreLobbyActionPerformed
        if (!lstLobby.isSelectionEmpty()) {
            String lobbyName = lstLobby.getSelectedValue().toString();
            if (!listePanneauLobby.containsKey(lobbyName)) {
                gui.aviserObservateurs(Observation.JOINLOBBY, lobbyName);
            }
        }
     }//GEN-LAST:event_btnJoindreLobbyActionPerformed

    private void btnQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitterActionPerformed

        if (!lstLobby.isSelectionEmpty()) {
            String lobbyName = lstLobby.getSelectedValue().toString();
            if (listePanneauLobby.containsKey(lobbyName)) {

                quitterSalon(lobbyName);
                gui.aviserObservateurs(Observation.LEAVELOBBY, lobbyName);

            }
        }
    }//GEN-LAST:event_btnQuitterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlContacts;
    private javax.swing.JPanel PnlSalon;
    private javax.swing.JPanel PnlUtilisateur;
    private javax.swing.JTabbedPane TabPanelSalons;
    private javax.swing.JButton btnCreerLobby;
    private javax.swing.JButton btnEnvoyer;
    private javax.swing.JButton btnJoindreLobby;
    private javax.swing.JButton btnQuitter;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea lblChat;
    private javax.swing.JList lstLobby;
    private javax.swing.JList lstUtilisateurs;
    private javax.swing.JPanel pnlFondChat;
    private javax.swing.JPanel pnlMessage;
    private javax.swing.JScrollPane scrollPaneLobby;
    private javax.swing.JScrollPane scrollPaneMessage;
    private javax.swing.JTabbedPane tabPnlInfo;
    private javax.swing.JTextPane txtChat;
    // End of variables declaration//GEN-END:variables

    public void ajouterMessage(String lobby, String user, String s) {
        listePanneauLobby.get(lobby).ajouterMessage("\n" + user + " : " + s);
        //lblChat.setText(lblChat.getText() + "\n" + user + " : " + s);
    }

    public void notifierConnectionClient(String lobby, String nom) {
        if (listePanneauLobby.get(lobby) != null) {
            listePanneauLobby.get(lobby).ajouterMessage("\n" + nom + " s'est connecté au lobby.");
            listePanneauLobby.get(lobby).getLstModelUtilisateurs().addElement(nom);
            mettreAJourListeUtilisateurs(lobby);
        }

    }

    private void mettreAJourListeUtilisateurs(String lobby) {
        if (getCurrentLobby() == lobby) {
            lstUtilisateurs.setModel(listePanneauLobby.get(lobby).getLstModelUtilisateurs());
        }
    }

    public void notifierDeconnectionClient(String lobby, String nom) {
        if (listePanneauLobby.get(lobby) != null) {
            listePanneauLobby.get(lobby).ajouterMessage("\n" + nom + " s'est déconnecté du lobby.");
            listePanneauLobby.get(lobby).getLstModelUtilisateurs().removeElement(nom);
            mettreAJourListeUtilisateurs(lobby);
        }
    }

    void ajouterEventTxtBox() {
        txtChat.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    evt.consume();
                    btnEnvoyerMouseClicked(null);
                }
            }
        });
    }

    void ajouterSalon(String nomSalon) {
        Lobby nouveauLobby = new Lobby(nomSalon);
        listePanneauLobby.put(nomSalon, nouveauLobby);
        TabPanelSalons.add(nouveauLobby.getPanel());

    }

    void quitterSalon(String nomSalon) {
        for (int i = 0; i < listePanneauLobby.size(); i++) {
            TabPanelSalons.remove(listePanneauLobby.get(nomSalon).getPanel());
            listePanneauLobby.remove(nomSalon);
        }
    }

    public void updateLobbies(String[] lobbies) {
        DefaultListModel lm = new DefaultListModel();

        for (int i = 0; i < lobbies.length; i++) {
            lm.addElement(lobbies[i]);
        }
        lstLobby.setModel(lm);

    }

    String getCurrentLobby() {
        if (TabPanelSalons.getSelectedIndex() >= 0) {
            return TabPanelSalons.getTitleAt(TabPanelSalons.getSelectedIndex());
        } else {
            return "";
        }
    }

    public void lobbyJoined(ArrayList<String> liste, String nomLobby) {
        ajouterSalon(nomLobby);

        DefaultListModel lm = listePanneauLobby.get(nomLobby).getLstModelUtilisateurs();
        for (String s : liste) {
            lm.addElement(s);
        }
        lstUtilisateurs.setModel(lm);

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
                if (TabPanelSalons.getSelectedIndex() >= 0) {
                    lstUtilisateurs.setModel(listePanneauLobby.get(getCurrentLobby()).getLstModelUtilisateurs());
                }
            }
        });
    }

    private void ajouterEventLstUtilisateur() {
        lstUtilisateurs.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    int index = list.locationToIndex(evt.getPoint());
                    gui.aviserObservateurs(Observation.PROFILEREQUEST, lstUtilisateurs.getModel().getElementAt(index).toString());
                }
            }
        });
    }

    void afficherProfil(ProfileResponse profileResponse) {
        PageProfil pp = new PageProfil(profileResponse);
        pp.setVisible(true);
    }
}
