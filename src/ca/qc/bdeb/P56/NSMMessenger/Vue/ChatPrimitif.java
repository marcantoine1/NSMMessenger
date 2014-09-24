/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger.Observation;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author Marc-Antoine
 */
public class ChatPrimitif extends javax.swing.JFrame{

    
    ChatGUI gui;
    /**
     * Creates new form ChatPrimitif
     */    
    String imgPath = "../../ressources/imageLogin.jpg";
    ImageIcon img = new ImageIcon(imgPath);
    
    public ChatPrimitif(ChatGUI gui) {
        this.gui = gui;
        initComponents();
        try {
            this.setIconImage(ImageIO.read(getClass().getResourceAsStream(imgPath)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        ajouterEventTxtBox();
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
        PnlContacts = new javax.swing.JPanel();
        PnlUtilisateur = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnEnvoyer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextPane();
        TabPanelSalons = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        lblChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NSM Messenger");

        jPanel1.setBackground(new java.awt.Color(0, 83, 186));

        javax.swing.GroupLayout PnlSalonLayout = new javax.swing.GroupLayout(PnlSalon);
        PnlSalon.setLayout(PnlSalonLayout);
        PnlSalonLayout.setHorizontalGroup(
            PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );
        PnlSalonLayout.setVerticalGroup(
            PnlSalonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        tabPnlInfo.addTab("Salon", PnlSalon);

        javax.swing.GroupLayout PnlContactsLayout = new javax.swing.GroupLayout(PnlContacts);
        PnlContacts.setLayout(PnlContactsLayout);
        PnlContactsLayout.setHorizontalGroup(
            PnlContactsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );
        PnlContactsLayout.setVerticalGroup(
            PnlContactsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );

        tabPnlInfo.addTab("Contacts", PnlContacts);

        javax.swing.GroupLayout PnlUtilisateurLayout = new javax.swing.GroupLayout(PnlUtilisateur);
        PnlUtilisateur.setLayout(PnlUtilisateurLayout);
        PnlUtilisateurLayout.setHorizontalGroup(
            PnlUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
        );
        PnlUtilisateurLayout.setVerticalGroup(
            PnlUtilisateurLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
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
                .addComponent(tabPnlInfo)
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void btnEnvoyerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnvoyerMouseClicked
        gui.aviserObservateurs(Observation.ENVOIMESSAGE, txtChat.getText());
        txtChat.setText("");
    }//GEN-LAST:event_btnEnvoyerMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlContacts;
    private javax.swing.JPanel PnlSalon;
    private javax.swing.JPanel PnlUtilisateur;
    private javax.swing.JTabbedPane TabPanelSalons;
    private javax.swing.JButton btnEnvoyer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea lblChat;
    private javax.swing.JTabbedPane tabPnlInfo;
    private javax.swing.JTextPane txtChat;
    // End of variables declaration//GEN-END:variables

    
    //todo: ameliorer l'affichage de messages
    public void ajouterMessage(int lobby, String user, String s)
    {
        //todo: lobby
        lblChat.setText(lblChat.getText() + "\n" + user + " : " +s);
    }
    public void ajouterEventTxtBox(){
        txtChat.addKeyListener(new KeyAdapter() 
    {
        public void keyPressed(KeyEvent evt)
        {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            {
                gui.aviserObservateurs(Observation.ENVOIMESSAGE, txtChat.getText());
                txtChat.setText("");
            }
        }
    });
    }
    public JButton getButton(){
        return this.btnEnvoyer;
    }
    public JTextPane getChat(){
        return this.txtChat;
    }
    public JTextArea getLblChat(){
        return this.lblChat;
    }
}
