package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Application.InfoLogin;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    private PasswordField txtMotDePasse;
    @FXML
    private Label lblCreerCompte;
    @FXML
    private TextField txtIp;
    @FXML
    private Button btnTester;
    @FXML
    private Button btnConnecter;
    public FXMLControllerPageLogin() {
        cmbUtilisateur = new ComboBox();
        lblCreerCompte = new Label();
        txtMotDePasse = new PasswordField();
        txtIp = new TextField();
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
        this.txtMotDePasse = txtMotDePasse;
    }

    public void setTxtIp(TextField txtIp) {
        this.txtIp = txtIp;
    }

    public ComboBox getCmbUtilisateur() {
        return cmbUtilisateur;
    }

    public PasswordField getMotDePasse() {
        return txtMotDePasse;
    }

    public Label getLblCreerCompte() {
        return lblCreerCompte;
    }

    public TextField getTxtIp() {
        return txtIp;
    }

    public Button getBtnTester() {
        return btnTester;
    }

    public Button getBtnConnecter() {
        return btnConnecter;
    }

    public FXMLControllerPageLogin(Stage primaryStage) {
        this.primaryStage = primaryStage;
        txtIp.setText("localhost");
    }
    public void Connection() {
        InfoLogin io = new InfoLogin();
        String motDePasse = txtMotDePasse.getText();
        io.password = new String(motDePasse);
        if (cmbUtilisateur.getValue() != null) {
            io.username = cmbUtilisateur.getValue().toString();
        }

        if (io.password != null && io.username != null)
        {
            gui.aviserObservateurs(NSMMessenger.Observation.ADRESSEIPCHANGEE, txtIp.getText());
            gui.aviserObservateurs(NSMMessenger.Observation.LOGIN, io);
        }
       
    }
    public void btnTesterClick(){
         gui.aviserObservateurs(NSMMessenger.Observation.ADRESSEIPCHANGEE, txtIp.getText());
         gui.aviserObservateurs(NSMMessenger.Observation.TESTERCONNECTION,null);
    }
    public void creationCompteClick() {
        gui.aviserObservateurs(NSMMessenger.Observation.ADRESSEIPCHANGEE, txtIp.getText());
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

}
