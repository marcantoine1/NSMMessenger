package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessengerCommunication.ProfileResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Francis
 */
public class FXMLControllerProfil extends Fenetre {

    private final String cssAntiHighlight = "-fx-focus-color: transparent;-fx-background-insets: -1.4, 0, 1, 2;";
    private ProfileResponse profil;

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

    public void setAttenteServeur(boolean attente) {
        boolean attenteServeur = attente;
    }

    public FXMLControllerProfil() {
        imgProfil = new ImageView();
        lblNomComplet = new Label();
        lblAge = new Label();
        btnAddRemove = new Button();
        btnChat = new Button();
        btnAchievements = new Button();
        btnReport = new Button();
        nomUtilisateur = new Label();
        lblSexe = new Label();
        btnAddRemove.setOnAction((Event) -> {
            try {
                btnAddRemoveClicked();
            } catch (InterruptedException ex) {
                Logger.getLogger(FXMLControllerProfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public ImageView getImgProfil() {
        return imgProfil;
    }

    public Label getLblNomComplet() {
        return lblNomComplet;
    }

    public Label getLblAge() {
        return lblAge;
    }

    public Button getBtnAddRemove() {
        return btnAddRemove;
    }

    public Label getNomUtilisateur() {
        return nomUtilisateur;
    }

    public Label getLblSexe() {
        return lblSexe;
    }

    public FXMLControllerProfil(ProfileResponse profil) {
        this.profil = profil;
    }

    public void build() {
        construirePage();
    }

    private void construirePage() {
        Image imageProfil = new Image(profil.getImage());
        imgProfil.setImage(imageProfil);
        imageBoutonAddRemoveContact();
        if(!profil.connecte){
           btnChat.setDisable(true);
        }
        else{
            btnChat.setDisable(false);
        }
        Image image = new Image(getClass().getResourceAsStream("../../Ressources/Profil/chat.jpg"));
        
        btnChat.setGraphic(new ImageView(image));
        image = new Image(getClass().getResourceAsStream("../../Ressources/Profil/trophy.jpg"));
        btnAchievements.setGraphic(new ImageView(image));
        image = new Image(getClass().getResourceAsStream("../../Ressources/Profil/report.jpg"));
        btnReport.setGraphic(new ImageView(image));

        lblNomComplet.setText(profil.getPrenom() + " " + profil.getNom());
        lblAge.setText("" + profil.getAge());
        nomUtilisateur.setText(profil.getUsername());
        lblSexe.setText(profil.getSexe());
    }

    void imageBoutonAddRemoveContact() {
        Platform.runLater(() ->{
            if (profil.isContact()) {
                Image image = new Image(getClass().getResourceAsStream("../." +
                        "./Ressources/Profil/remove.jpg"));
                btnAddRemove.setGraphic(new ImageView(image));
            } else {
                Image image = new Image(getClass().getResourceAsStream("../../Ressources/Profil/add.jpg"));
                btnAddRemove.setGraphic(new ImageView(image));
            }
        });

    }

    @FXML
    private void btnAddRemoveClicked() throws InterruptedException {
        if (profil.isContact) {
            gui.aviserObservateurs(NSMMessenger.Observation.CONTACTEFFACERREQUEST, profil.getUsername());
            gui.aviserObservateurs(NSMMessenger.Observation.PROFILEREQUEST, profil.getUsername());
        } else {
            gui.aviserObservateurs(NSMMessenger.Observation.CONTACTREQUEST, profil.getUsername());
            gui.aviserObservateurs(NSMMessenger.Observation.PROFILEREQUEST, profil.getUsername());
        }
        
        
    }

    @FXML
    private void btnChatClicked() {
       gui.ajouterAuLobby(profil.getUsername());
       
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

    public void setProfil(ProfileResponse pr) {
        profil = pr;
    }

    @Override
    public String getPathFXML() {
        return "Profil.fxml";
    }

    @Override
    public String getTitre() {
        return "Page profilController";
    }
}
