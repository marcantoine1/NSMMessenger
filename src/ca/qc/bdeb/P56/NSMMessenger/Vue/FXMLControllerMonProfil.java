/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import ca.qc.bdeb.P56.NSMMessengerCommunication.SelfProfileResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Guillaume
 */
public class FXMLControllerMonProfil extends Fenetre {
    
    private final String cssAntiHighlight = "-fx-focus-color: transparent;-fx-background-insets: -1.4, 0, 1, 2;";
    private final String pathFXML = "MonProfil.fxml";
    private final String titre = "Page monProfilController";
    private SelfProfileResponse profil;

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


    private boolean attenteServeur;

    public void setAttenteServeur(boolean attente) {
        this.attenteServeur = attente;
    }

    public FXMLControllerMonProfil() {

    }

    public FXMLControllerMonProfil(SelfProfileResponse profil) {
        this.profil = profil;
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
        txtMotDePasse.setText(profil.getMotDePasse());
        txtConfirmation.setText(profil.getCourriel());
        nomUtilisateur.setText(profil.getUsername());
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
}
