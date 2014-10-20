/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javax.swing.ImageIcon;

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
    @FXML
    private Pane panelProfil;
    @FXML
    private Label nomUtilisateur;
    @FXML
    private Label lblSexe;
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
       // btnFermer.setStyle(cssAntiHighlight);
        nomUtilisateur.setStyle(cssAntiHighlight);
        panelProfil.setStyle(cssAntiHighlight);
        lblSexe.setStyle(cssAntiHighlight);
    }
    
    private void construirePage() {
        // TODO : Aller chercher les informations du profil voulu ainsi que l'image de profil
        //Image imageTemporaire=new Image("placeholder.png");        
        Image image= new Image(getClass().getResourceAsStream("../../ressources/placeHolder.png"));
        lblPrenom.setText(lblPrenom.getText()+"Monsieur");
        lblNom.setText(lblNom.getText()+"Tartampion");
        lblAge.setText(lblAge.getText()+"126");
        nomUtilisateur.setText("CoolGuillaume123");
        //imgProfil.setImage(imageTemporaire);
        imgProfil.setImage(image);
        lblSexe.setText(lblSexe.getText()+"Svp Oui Merci");
    }   
}
