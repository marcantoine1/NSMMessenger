package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Application.JukeBox;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by Martin on 2014-10-21.
 */
public class FXMLControllerPageLogin extends Fenetre {

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
     Stage primaryStage;
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
        return "PageLogin.fxml";
    }

    @Override
    public String getTitre() {
        String titre = "Login a NSM Messenger";
        return titre;
    }

    public void jouerSon() {
        JukeBox.loop("NSM");
        JukeBox.loop("BackgroundMusic");
    }
    
    public void changerThemeDark() {
        String css = getClass().getResource("../../Ressources/CSS/DarkTheme.css").toExternalForm();
        getScene().getStylesheets().clear();
        getScene().getStylesheets().add(css);
        gui.appliquerDarkTheme();
    }
    
    public void changerThemeBlue() {
        String css = getClass().getResource("../../Ressources/CSS/BlueTheme.css").toExternalForm();
        getScene().getStylesheets().clear();
        getScene().getStylesheets().add(css);
        gui.appliquerBlueTheme();
    }
    public void motDePassePerdu(){
        TextInputDialog lobbyDialog = new TextInputDialog();
        lobbyDialog.setContentText("Entrez votre nom d'utilisateur");
        lobbyDialog.setTitle("Retrouver votre mot de passe");
        lobbyDialog.initOwner(primaryStage);
        lobbyDialog.initModality(Modality.APPLICATION_MODAL);
        lobbyDialog.setHeaderText(null);
        lobbyDialog.setGraphic(null);
        Optional<String> response = lobbyDialog.showAndWait();
        if (response.isPresent()) {
            gui.aviserObservateurs(NSMMessenger.Observation.RETRIEVEPASSWORDREQUEST, response.get());
        }      
    }
    public Scene getScene() {
        return btnConnecter.getScene();
    }
}
