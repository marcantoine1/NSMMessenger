/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoCreation;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger.Observation;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.input.KeyCode;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import sun.swing.SwingUtilities2;

/**
 *
 * @author Marc-Antoine
 */
class CompteUtilisateur extends javax.swing.JFrame {

    private ChatGUI gui;

    public CompteUtilisateur(ChatGUI gui) {
        this.gui = gui;
        initComponents();
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCreation = new javax.swing.JPanel();
        txtEmail = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        btnCreerCompte = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        txtConfirmation = new javax.swing.JPasswordField();
        btnAnnuler = new javax.swing.JButton();
        lblCourriel = new javax.swing.JLabel();
        lblConfirmation = new javax.swing.JLabel();
        lblMotDePasse = new javax.swing.JLabel();
        lblUtilisateur = new javax.swing.JLabel();
        lblErreur = new javax.swing.JLabel();
        lblNom = new javax.swing.JLabel();
        rdoFemme = new javax.swing.JRadioButton();
        rdoHomme = new javax.swing.JRadioButton();
        txtNom = new javax.swing.JTextField();
        lblPrenom = new javax.swing.JLabel();
        txtPrenom = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblAge = new javax.swing.JLabel();
        txtAge = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlCreation.setMaximumSize(null);

        txtEmail.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EnterPressedHandler(evt);
            }
        });

        txtUsername.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EnterPressedHandler(evt);
            }
        });

        btnCreerCompte.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnCreerCompte.setText("Creer");
        btnCreerCompte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreerCompteActionPerformed(evt);
            }
        });
        btnCreerCompte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EnterPressedHandler(evt);
            }
        });

        txtPassword.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EnterPressedHandler(evt);
            }
        });

        txtConfirmation.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        txtConfirmation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmationActionPerformed(evt);
            }
        });
        txtConfirmation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EnterPressedHandler(evt);
            }
        });

        btnAnnuler.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnAnnuler.setText("Annuler");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        lblCourriel.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblCourriel.setText("Courriel");

        lblConfirmation.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblConfirmation.setText("Confirmation");

        lblMotDePasse.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblMotDePasse.setText("Mot de passe");

        lblUtilisateur.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblUtilisateur.setText("Utilisateur");

        lblErreur.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblErreur.setForeground(new java.awt.Color(255, 0, 0));

        lblNom.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblNom.setText("Nom");

        rdoFemme.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        rdoFemme.setText("Femme");
        rdoFemme.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoFemmeItemStateChanged(evt);
            }
        });

        rdoHomme.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N
        rdoHomme.setText("Homme");
        rdoHomme.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoHommeItemStateChanged(evt);
            }
        });

        txtNom.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        lblPrenom.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblPrenom.setText("Prénom");

        txtPrenom.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        jLabel1.setText("Sexe");

        lblAge.setFont(new java.awt.Font("Gill Sans MT", 1, 14)); // NOI18N
        lblAge.setText("Age");

        txtAge.setFont(new java.awt.Font("Gill Sans MT", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlCreationLayout = new javax.swing.GroupLayout(pnlCreation);
        pnlCreation.setLayout(pnlCreationLayout);
        pnlCreationLayout.setHorizontalGroup(
            pnlCreationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCreationLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlCreationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblConfirmation)
                    .addComponent(lblMotDePasse)
                    .addComponent(lblAge)
                    .addComponent(rdoFemme)
                    .addComponent(rdoHomme)
                    .addComponent(jLabel1)
                    .addComponent(lblPrenom)
                    .addComponent(lblNom)
                    .addComponent(lblCourriel)
                    .addGroup(pnlCreationLayout.createSequentialGroup()
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCreerCompte, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtAge)
                    .addComponent(txtEmail)
                    .addComponent(txtConfirmation)
                    .addComponent(txtPassword)
                    .addComponent(txtPrenom)
                    .addComponent(txtNom)
                    .addComponent(lblUtilisateur)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(lblErreur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        pnlCreationLayout.setVerticalGroup(
            pnlCreationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCreationLayout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(lblErreur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUtilisateur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrenom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMotDePasse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblConfirmation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCourriel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoHomme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoFemme)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAge)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCreationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnnuler)
                    .addComponent(btnCreerCompte)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlCreation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCreation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreerCompteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreerCompteActionPerformed
        char[] motDePasse = txtPassword.getPassword();
        String motDePasseString = new String(motDePasse);
        char[] motDePasseConfirmation = txtConfirmation.getPassword();
        String motDePasseConfirmationString = new String(motDePasseConfirmation);
        Pattern pattern = Pattern.compile("([A-Z0-9._%+-]+@+[A-Z0-9.-]+\\.[A-Z]{2,4})");
        Matcher matcher = pattern.matcher(txtEmail.getText().toUpperCase());

        if ((motDePasseString.equals(motDePasseConfirmationString))) {
            if (!(txtUsername.getText().isEmpty()) && !(motDePasseString.isEmpty())
                    && !(motDePasseConfirmationString.isEmpty()) && 
                    !(txtEmail.getText().isEmpty())
                    && !(txtNom.getText().isEmpty())
                    && !(txtPrenom.getText().isEmpty())
                    && !(txtAge.getText().isEmpty())
                    && (rdoFemme.isSelected() || rdoHomme.isSelected())) {
                if (matcher.matches()) {
                    InfoCreation ic = new InfoCreation();
                    ic.username = txtUsername.getText();
                    ic.password = motDePasseString;
                    ic.email = txtEmail.getText();
                    
                    ic.prenom = txtPrenom.getText();
                    ic.nom = txtNom.getText();
                    if (rdoFemme.isSelected()) {
                        ic.sexe = "Femme";
                        rdoHomme.setEnabled(false);
                    } else {
                        ic.sexe = "Homme";
                        rdoFemme.setEnabled(false);
                    }
                    if(isInteger(txtAge.getText())){
                        ic.age = Integer.parseInt(txtAge.getText());
                        if(ic.age > 1 && ic.age < 100){
                         gui.aviserObservateurs(Observation.CREATION, ic);
                        }
                        else{
                            lblErreur.setText("L'age doit être entre 1 et 100");
                        }
                    }
                    else{
                        lblErreur.setText("Age doit etre un nombre");
                    }
                } else {
                    lblErreur.setText("Courriel non valide");
                }
            } else {
                lblErreur.setText("Tous les champs doivent être remplis");
            }
        } else {
            lblErreur.setText("Vos mots de passes correspondent pas");
        }

    }//GEN-LAST:event_btnCreerCompteActionPerformed

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Voulez vous vraiment annuler votre inscription?", "Annuler votre inscription", JOptionPane.YES_NO_OPTION) == 0) {
            gui.retourLogin();
        }
    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void txtConfirmationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmationActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void EnterPressedHandler(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EnterPressedHandler
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnCreerCompteActionPerformed(null);
        } else if (evt.getKeyCode() == evt.VK_ESCAPE) {
            btnAnnulerActionPerformed(null);
        }
    }//GEN-LAST:event_EnterPressedHandler

    private void rdoHommeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoHommeItemStateChanged
        if (rdoHomme.isSelected()) {
            rdoFemme.setEnabled(false);
        } else {
            rdoFemme.setEnabled(true);
        }
    }//GEN-LAST:event_rdoHommeItemStateChanged

    private void rdoFemmeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoFemmeItemStateChanged
       if (rdoFemme.isSelected()) {
            rdoHomme.setEnabled(false);
        } else {
            rdoHomme.setEnabled(true);
        }
    }//GEN-LAST:event_rdoFemmeItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnCreerCompte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblConfirmation;
    private javax.swing.JLabel lblCourriel;
    private javax.swing.JLabel lblErreur;
    private javax.swing.JLabel lblMotDePasse;
    private javax.swing.JLabel lblNom;
    private javax.swing.JLabel lblPrenom;
    private javax.swing.JLabel lblUtilisateur;
    private javax.swing.JPanel pnlCreation;
    private javax.swing.JRadioButton rdoFemme;
    private javax.swing.JRadioButton rdoHomme;
    private javax.swing.JTextField txtAge;
    private javax.swing.JPasswordField txtConfirmation;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNom;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPrenom;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

}
