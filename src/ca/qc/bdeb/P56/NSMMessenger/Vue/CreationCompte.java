/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author Guillaume
 */
public class CreationCompte {
    private final String cssAntiHighlight = "-fx-focus-color: grey;";
    private final String pathFXML = "CreationCompte.fxml";
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
    private TextArea txtMotDePasse;
    @FXML
    private TextArea txtConfirmation;
    @FXML
    private TextArea txtCourriel;
    @FXML
    private TextArea txtAge;
    @FXML
    private RadioButton radioHomme;
    @FXML
    private RadioButton RadioFemme;
    @FXML
    private Pane PanelCreerCompte;
    
    public CreationCompte(){
        
    }
    public CreationCompte(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    public void build(){
        retirerGlow();
        construirePage();
    }
    private void retirerGlow(){
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
        RadioFemme.setStyle(cssAntiHighlight);
        radioHomme.setStyle(cssAntiHighlight);
    }
    private void construirePage() {
    }
}
