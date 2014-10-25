/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoCreation;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Guillaume
 */
public class CreationCompte extends Fenetre {

    private final String pathFXML = "CreationCompte.fxml";
    private final String titre = "Créer un compte";
    private final String cssAntiHighlight = "-fx-focus-color: grey;";
    private Stage primaryStage;
    @FXML
    private Label lblUtilisateur;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblMotDePasse;
    @FXML
    private Label lblConfirmation;
    @FXML
    private Label lblCourriel;
    @FXML
    private Label lblAge;
    @FXML
    private Label lblSexe;
    @FXML
    private Label lblErreur;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnCreer;
    @FXML
    private TextArea txtUtilisateur;
    @FXML
    private TextArea txtPrenom;
    @FXML
    private TextArea txtNom;
    @FXML
    private PasswordField txtMotDePasse;
    @FXML
    private PasswordField txtConfirmation;
    @FXML
    private TextArea txtCourriel;
    @FXML
    private TextArea txtAge;
    @FXML
    private RadioButton radioHomme;
    @FXML
    private RadioButton radioFemme;
    @FXML
    private Pane PanelCreerCompte;
    ToggleGroup radioGroup = new ToggleGroup();
    
    final int AGE_MIN = 1;
    final int AGE_MAX = 100;

    public CreationCompte() {
    }

    public CreationCompte(Stage primaryStage) {        
        this();
        this.primaryStage = primaryStage;
    }

    public void build() {
        radioFemme.setToggleGroup(radioGroup);
        radioHomme.setToggleGroup(radioGroup);
        btnCreer.setDefaultButton(true);
        retirerGlow();
        construirePage();
    }

    private void retirerGlow() {
        lblErreur.setStyle(cssAntiHighlight);
        lblUtilisateur.setStyle(cssAntiHighlight);
        lblPrenom.setStyle(cssAntiHighlight);
        lblNom.setStyle(cssAntiHighlight);
        lblCourriel.setStyle(cssAntiHighlight);
        lblMotDePasse.setStyle(cssAntiHighlight);
        lblConfirmation.setStyle(cssAntiHighlight);
        lblAge.setStyle(cssAntiHighlight);
        lblSexe.setStyle(cssAntiHighlight);
        PanelCreerCompte.setStyle(cssAntiHighlight);
        btnAnnuler.setStyle(cssAntiHighlight);
        btnCreer.setStyle(cssAntiHighlight);
        txtUtilisateur.setStyle(cssAntiHighlight);
        txtPrenom.setStyle(cssAntiHighlight);
        txtNom.setStyle(cssAntiHighlight);
        txtCourriel.setStyle(cssAntiHighlight);
        txtMotDePasse.setStyle(cssAntiHighlight);
        txtConfirmation.setStyle(cssAntiHighlight);
        txtAge.setStyle(cssAntiHighlight);
        radioFemme.setStyle(cssAntiHighlight);
        radioHomme.setStyle(cssAntiHighlight);
    }

    private void construirePage() {
    }

    @Override
    public String getPathFXML() {
        return pathFXML;
    }

    @Override
    public String getTitre() {
        return titre;
    }
    
     public void btnCreerCompteActionPerformed() {                                               
        char[] motDePasse = txtMotDePasse.getText().toCharArray();
        String motDePasseString = new String(motDePasse);
        char[] motDePasseConfirmation = txtConfirmation.getText().toCharArray();
        String motDePasseConfirmationString = new String(motDePasseConfirmation);
        Pattern pattern = Pattern.compile("([A-Z0-9._%+-]+@+[A-Z0-9.-]+\\.[A-Z]{2,4})");
        Matcher matcher = pattern.matcher(txtCourriel.getText().toUpperCase());

        if ((motDePasseString.equals(motDePasseConfirmationString))) {
            if (!(txtUtilisateur.getText().isEmpty()) && !(motDePasseString.isEmpty())
                    && !(motDePasseConfirmationString.isEmpty()) && 
                    !(txtCourriel.getText().isEmpty())
                    && !(txtNom.getText().isEmpty())
                    && !(txtPrenom.getText().isEmpty())
                    && !(txtAge.getText().isEmpty())
                    && (radioFemme.isSelected() || radioHomme.isSelected())) {
                if (matcher.matches()) {
                    InfoCreation ic = new InfoCreation();
                    ic.username = txtUtilisateur.getText();
                    ic.password = motDePasseString;
                    ic.email = txtCourriel.getText();
                    
                    ic.prenom = txtPrenom.getText();
                    ic.nom = txtNom.getText();
                    if (radioFemme.isSelected()) {
                        ic.sexe = "Femme";
                    } else {
                        ic.sexe = "Homme";
                    }
                    if(isInteger(txtAge.getText())){
                        ic.age = Integer.parseInt(txtAge.getText());
                        if(ic.age > AGE_MIN && ic.age < AGE_MAX){
                         gui.aviserObservateurs(NSMMessenger.Observation.CREATION, ic);
                        }
                        else{
                            lblErreur.setText("L'age doit être entre " +  AGE_MIN + " et " + AGE_MAX);
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

    }                                              

    public void btnAnnulerActionPerformed() {                                           
        if (JOptionPane.showConfirmDialog(null, "Voulez vous vraiment annuler votre inscription?", "Annuler votre inscription", JOptionPane.YES_NO_OPTION) == 0) {
            gui.afficherPageLogin();
        }
    }                                          

    public void EnterPressedHandler(java.awt.event.KeyEvent evt) {                                     
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnCreerCompteActionPerformed();
        } else if (evt.getKeyCode() == evt.VK_ESCAPE) {
            btnAnnulerActionPerformed();
        }
    }                                    

    public void rdoHommeItemStateChanged() {                                          
        if (radioHomme.isSelected()) {
            radioFemme.setSelected(false);
        }
    }                                         

    public void rdoFemmeItemStateChanged() {                                          
       if (radioFemme.isSelected()) {
            radioHomme.setSelected(false);
        }
    }      
    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
