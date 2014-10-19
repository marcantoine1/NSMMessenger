/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author Francis
 */
public class Profil {
    private Stage primaryStage;
    private final String cssAntiHighlight = "-fx-focus-color: transparent;-fx-background-insets: -1.4, 0, 1, 2;";
    private String pathFXML = "Profil.fxml";
    
    @FXML
    private ImageView imgProfil;
    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblAge;
    @FXML
    private Button btnFermer;
    
    public Profil() {
    
    }
    
    public Profil (Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    
    public void build() {
        retirerGlow();
        construirePage();
    }
    
    private void retirerGlow() {
        imgProfil.setStyle(cssAntiHighlight);
        lblPrenom.setStyle(cssAntiHighlight);
        lblNom.setStyle(cssAntiHighlight);
        lblAge.setStyle(cssAntiHighlight);
        btnFermer.setStyle(cssAntiHighlight);
    }
    
    private void construirePage() {
        // TODO : Aller chercher les informations du profil voulu ainsi que l'image de profil
        lblPrenom.setText("Prénom");
        lblNom.setText("Nom");
        lblAge.setText("110");
    }   
}
