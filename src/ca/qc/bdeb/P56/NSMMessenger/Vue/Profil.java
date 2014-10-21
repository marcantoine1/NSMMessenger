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

/**
 *
 * @author Francis
 */
public class Profil {

    private Stage primaryStage;
    private final String cssAntiHighlight = "-fx-focus-color: transparent;-fx-background-insets: -1.4, 0, 1, 2;";
    private final String pathFXML = "Profil.fxml";

    @FXML
    private ImageView imgProfil;
    @FXML
    private Label lblNomComplet;
    @FXML
    private Label lblAge;
    @FXML
    private Button btnAddRemove;
    @FXML
    private Button btnChat;
    @FXML
    private Button btnAchievements;
    @FXML
    private Button btnReport;
    @FXML
    private Pane panelProfil;
    @FXML
    private Label nomUtilisateur;
    @FXML
    private Label lblSexe;

    public Profil() {

    }

    public Profil(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void build() {
        retirerGlow();
        construirePage();
    }

    private void retirerGlow() {
        imgProfil.setStyle(cssAntiHighlight);
        lblNomComplet.setStyle(cssAntiHighlight);
        lblAge.setStyle(cssAntiHighlight);
        nomUtilisateur.setStyle(cssAntiHighlight);
        panelProfil.setStyle(cssAntiHighlight);
        lblSexe.setStyle(cssAntiHighlight);
        btnAddRemove.setStyle(cssAntiHighlight);
        btnChat.setStyle(cssAntiHighlight);
        btnAchievements.setStyle(cssAntiHighlight);
        btnReport.setStyle(cssAntiHighlight);
    }

    private void construirePage() {
        // TODO : Aller chercher les informations du profil voulu ainsi que l'image de profil    
        Image image = new Image(getClass().getResourceAsStream("../../ressources/placeHolder.png"));
        imgProfil.setImage(image);
        image = new Image(getClass().getResourceAsStream("../../Ressources/Profil/add.jpg"));
        btnAddRemove.setGraphic(new ImageView(image));
        image = new Image(getClass().getResourceAsStream("../../Ressources/Profil/chat.jpg"));
        btnChat.setGraphic(new ImageView(image));
        image = new Image(getClass().getResourceAsStream("../../Ressources/Profil/trophy.jpg"));
        btnAchievements.setGraphic(new ImageView(image));
        image = new Image(getClass().getResourceAsStream("../../Ressources/Profil/report.jpg"));
        btnReport.setGraphic(new ImageView(image));

        lblNomComplet.setText("Monsieur Tartampion");
        lblAge.setText("126 ans");
        nomUtilisateur.setText("CoolGuillaume123");
        lblSexe.setText("Homme");
    }
    
    @FXML
    private void btnAddRemoveClicked() {
        // TODO : Implémenter l'action d'ajouter ou enlever le contact
        System.out.println("Click sur le bouton Add/Remove");
    }
    
    @FXML
    private void btnChatClicked() {
        // TODO : Implémenter l'action d'ajouter le contact dans un chat privé
        System.out.println("Click sur le bouton Chat");
    }
    
    @FXML
    private void btnAchievementsClicked() {
        // TODO : Implémenter l'action d'afficher les achievements du contact
        System.out.println("Click sur le bouton Achievements");
    }
    
    @FXML
    private void btnReportClicked() {
        // TODO : Implémenter l'action de report le contact
        System.out.println("Click sur le bouton Report");
    }
    
}
