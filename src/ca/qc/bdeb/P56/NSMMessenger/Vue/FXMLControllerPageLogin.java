package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Application.JukeBox;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


/**
 * Created by Martin on 2014-10-21.
 */
public class FXMLControllerPageLogin extends Fenetre {

    private final String titre = "Login a NSM Messenger";
    private final String pathFXML = "PageLogin.fxml";
    private Stage primaryStage;
    @FXML
    private ComboBox cmbUtilisateur;
    @FXML
    private PasswordField editMotDePasse;
    @FXML
    private TextField txtIpField;
    @FXML
    private Button btnTester;
    @FXML
    private Button btnConnecter;
    public FXMLControllerPageLogin() {
        cmbUtilisateur = new ComboBox();
        editMotDePasse = new PasswordField();
        txtIpField = new TextField();
        btnTester = new Button();
        btnConnecter = new Button();
        btnConnecter.setOnAction((Event) -> {
            Connection();
        });
        btnTester.setOnAction((Event) -> {
            btnTesterClick();
        });
               
    }

    public void setCmbUtilisateur(ComboBox cmbUtilisateur) {
        this.cmbUtilisateur = cmbUtilisateur;
    }

    public void setTxtMotDePasse(PasswordField txtMotDePasse) {
        this.editMotDePasse = txtMotDePasse;
    }

    public void setTxtIp(TextField txtIp) {
        this.txtIpField = txtIp;
    }

    public ComboBox getCmbUtilisateur() {
        return cmbUtilisateur;
    }

    public PasswordField getMotDePasse() {
        return editMotDePasse;
    }

    public TextField getTxtIp() {
        return txtIpField;
    }

    public Button getBtnTester() {
        return btnTester;
    }

    public Button getBtnConnecter() {
        return btnConnecter;
    }

    public FXMLControllerPageLogin(Stage primaryStage) {
        this.primaryStage = primaryStage;
        txtIpField.setText("localhost");
    }
    public void Connection() {
        InfoLogin io = new InfoLogin();
        String motDePasse = editMotDePasse.getText();
        io.password = new String(motDePasse);
        if (cmbUtilisateur.getValue() != null) {
            io.username = cmbUtilisateur.getValue().toString();
        }

        if (io.password != null && io.username != null)
        {
            gui.aviserObservateurs(NSMMessenger.Observation.ADRESSEIPCHANGEE, txtIpField.getText());
            gui.aviserObservateurs(NSMMessenger.Observation.LOGIN, io);
        }
       
    }
    public void btnTesterClick(){
         gui.aviserObservateurs(NSMMessenger.Observation.ADRESSEIPCHANGEE, txtIpField.getText());
         gui.aviserObservateurs(NSMMessenger.Observation.TESTERCONNECTION,null);
    }

    public void playStopClick() {
        if (JukeBox.isPlaying("NSM"))
            JukeBox.stop("NSM");
        else
            JukeBox.loop("NSM");

        if (JukeBox.isPlaying("BackgroundMusic"))
            JukeBox.stop("BackgroundMusic");
        else
            JukeBox.loop("BackgroundMusic");
    }
    
    public void creationCompteClick() {
        gui.aviserObservateurs(NSMMessenger.Observation.ADRESSEIPCHANGEE, txtIpField.getText());
        gui.afficherCreationCompte();
    }

    public void passwordEnter(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            Connection();
        }
    }

    @Override
    public String getPathFXML() {
        return pathFXML;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    public void jouerSon() {
        JukeBox.loop("NSM");
        JukeBox.loop("BackgroundMusic");
    }
    
    public void changerThemeDark() {
        System.out.println("Change theme Dark");
    }
    
    public Scene getScene() {
        return btnConnecter.getScene();
    }
}
