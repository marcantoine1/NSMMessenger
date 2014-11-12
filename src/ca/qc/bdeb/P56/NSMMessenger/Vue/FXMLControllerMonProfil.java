/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.SelfProfileResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.UtilisateurModifier;
import ca.qc.bdeb.P56.NSMMessengerServer.Application.Utilisateur;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume
 */
public class FXMLControllerMonProfil extends Fenetre {

    private final String cssAntiHighlight = "-fx-focus-color: transparent;-fx-background-insets: -1.4, 0, 1, 2;";
    private final String pathFXML = "MonProfil.fxml";
    private final String titre = "Page monProfilController";
    private Stage primaryStage;
    private SelfProfileResponse profil;
    
    final int AGE_MIN = 1;
    final int AGE_MAX = 100;

    @FXML
    private ImageView imgProfil;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblAge;
    @FXML
    private Label nomUtilisateur;
    @FXML
    private Label lblCourriel;
    @FXML
    private Label lblMotDePasse;
    @FXML
    private Label lblConfirmation;
    @FXML
    private Pane panelProfil;
    @FXML
    private Button btnSauvergarder;
    @FXML
    private Button btnChangerImage;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtCourriel;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtMotDePasse;
    @FXML
    private TextField txtConfirmation;
    private boolean estModifie;

    private boolean attenteServeur;

    public void setAttenteServeur(boolean attente) {
        this.attenteServeur = attente;
    }

    public FXMLControllerMonProfil() {
        txtMotDePasse = new TextField();
    }

    public FXMLControllerMonProfil(Stage primaryStage) {
        this();
        this.primaryStage = primaryStage;
    }

    public FXMLControllerMonProfil(SelfProfileResponse profil) {
        this.profil = profil;
        txtMotDePasse = new TextField();
    }

    public void build() {
        construirePage();
    }

    private void construirePage() {
        // TODO : Aller chercher les informations du profilController voulu ainsi que l'image de profilController
        Image image = new Image(getClass().getResourceAsStream("../../ressources/imageParDefaut.png"));
        imgProfil.setImage(image);

        txtNom.setText(profil.getNom());
        txtPrenom.setText(profil.getPrenom());
        txtAge.setText(String.valueOf(profil.getAge()));
        txtCourriel.setText(profil.getCourriel());
        //txtMotDePasse.setText(profil.getMotDePasse());
        nomUtilisateur.setText(profil.getUsername());
        txtConfirmation.setVisible(false);
        lblConfirmation.setVisible(false);
    }

    public void setProfil(SelfProfileResponse pr) {
        profil = pr;
    }

    @Override
    public String getPathFXML() {
        return pathFXML;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @FXML
    public void motDePasseKeyType() {
        lblConfirmation.setVisible(true);
        txtConfirmation.setVisible(true);
    }

    private void afficherMessageErreur(String message) {
        Dialog d = new Dialog();
        d.setTitle("Impossible de faire les changements");
        d.setContentText(message);
        d.initOwner(primaryStage);
        d.initModality(Modality.APPLICATION_MODAL);
        d.setHeaderText(null);
        d.setGraphic(null);
        d.getDialogPane().getButtonTypes().add(ButtonType.OK);
        Optional<ButtonType> reponse = d.showAndWait();
    }

    private boolean validerCourriel() {
        Pattern pattern = Pattern.compile("([A-Z0-9._%+-]+@+[A-Z0-9.-]+\\.[A-Z]{2,4})");
        Matcher matcher = pattern.matcher(txtCourriel.getText().toUpperCase());
        if (matcher.matches()) {
            return true;
        } else {
            afficherMessageErreur("Le courriel est invalide!");
            return false;
        }

    }

    private boolean validerAge() {
        if (isInteger(txtAge.getText())) {
            int age = Integer.parseInt(txtAge.getText());
            if (age > AGE_MIN && age < AGE_MAX) {
                return true;
            } else {
                afficherMessageErreur("L'age doit être entre " + AGE_MIN + " et " + AGE_MAX);
            }
        } else {
            afficherMessageErreur("L'age doit etre un nombre");
        }
        return false;
    }
    
    private boolean validerChampsRemplis() {

        if (!(txtCourriel.getText().isEmpty())
                && !(txtNom.getText().isEmpty())
                && !(txtPrenom.getText().isEmpty())
                && !(txtAge.getText().isEmpty())) {
            return true;
        } else {
            afficherMessageErreur("Veuillez remplir tous les champs");
            return false;
        }
    }
        private boolean validerMotDePassesConcordants() {
        if (txtMotDePasse.getText().equals(txtConfirmation.getText())) {
            return true;
        } else {
            afficherMessageErreur("Vos mots de passes ne concordent pas!");
            return false;
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
    private void verifierInformationModifier(TextField champ,String valeurPrecedente){
        if((!(champ.getText().equals(valeurPrecedente)))){
            estModifie = true;
            System.out.println("true");
        }
    }

    @FXML
    public void sauvergarderChangements() {
        if (validerAge() && validerChampsRemplis() && validerCourriel() && validerMotDePassesConcordants() ) {
            
            if (!(txtMotDePasse.getText().equals(""))) {
                estModifie = true;
            }
            verifierInformationModifier(txtPrenom,profil.getPrenom());
            verifierInformationModifier(txtNom,profil.getNom());
            verifierInformationModifier(txtCourriel,profil.getCourriel());
            verifierInformationModifier(txtAge,String.valueOf(profil.getAge()));

            if (estModifie) {
                String[] util = new String[7];
                util[0]=profil.getUsername();
                util[1]=txtMotDePasse.getText();
                util[2]=txtCourriel.getText();
                util[3]=txtAge.getText();
                util[4]=txtNom.getText();
                util[5]= txtPrenom.getText();
                util[6]=profil.getSexe();
               String[] util2 = new String[7];
               util2[0]=profil.getUsername();
               util2[1]= profil.getMotDePasse();
               util2[2]=profil.getCourriel();
               util2[3]=String.valueOf(profil.getAge());
               util2[4]=profil.getNom();
               util2[5]=profil.getPrenom();
               util2[6]=profil.getSexe();
               
                UtilisateurModifier utilModif = new UtilisateurModifier(util2, util);
                gui.aviserObservateurs(NSMMessenger.Observation.UTILISATEURMODIFIER, utilModif); 
            }
        }
    }
}
