package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by Martin on 2014-10-21.
 */
public class PageLogin {

    private Stage primaryStage;
    @FXML
    private ComboBox cmbUtilisateur;
    @FXML
    private PasswordField editMotDePasse;
    @FXML
    private Label lblCreerCompte;
    private FxGUI gui;
    public PageLogin() {
         
    }

    public PageLogin(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void build(){
        
    }
    public void Connection(ActionEvent e ){
        InfoLogin io = new InfoLogin();
        String motDePasse = editMotDePasse.getText();
        io.password = new String(motDePasse);
        if(cmbUtilisateur.getValue() !=null)
        io.username = cmbUtilisateur.getValue().toString();
        
        if (io.password != null && io.username != null) {
           gui.aviserObservateurs(NSMMessenger.Observation.LOGIN, io);
        }
        gui.afficherChat();
    }
    public void setGui(FxGUI gui){
        this.gui = gui;
    }
    public void creationCompteClick(MouseEvent e){
        gui.afficherCreationCompte();
    }
    public void passwordEnter(KeyEvent e){
        if (e.getCode() == KeyCode.ENTER) {
                    Connection(null);
                }
    }
   
}
