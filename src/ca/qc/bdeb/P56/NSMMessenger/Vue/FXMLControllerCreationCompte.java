package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Application.InfoCreation;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Guillaume
 */
public class FXMLControllerCreationCompte extends Fenetre {

    private final String cssAntiHighlight = "-fx-focus-color: grey;";
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
    private Label lblErreur;
    @FXML
    private Button btnAnnuler;
    @FXML
    private Button btnCreer;
    @FXML
    private TextField txtUtilisateur;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtNom;
    @FXML
    private PasswordField txtMotDePasse;
    @FXML
    private PasswordField txtConfirmation;
    @FXML
    private TextField txtCourriel;
    @FXML
    private TextField txtAge;
    @FXML
    private RadioButton radioHomme;
    @FXML
    private RadioButton radioFemme;
    @FXML
    private Pane PanelCreerCompte;
    ToggleGroup radioGroup = new ToggleGroup();

    final int AGE_MIN = 1;
    final int AGE_MAX = 100;

    public FXMLControllerCreationCompte() {
        lblErreur = new Label();
        txtUtilisateur = new TextField();
        txtPrenom = new TextField();
        txtNom = new TextField();
        txtCourriel = new TextField();
        txtMotDePasse = new PasswordField();
        txtConfirmation = new PasswordField();
        txtAge = new TextField();
        radioHomme = new RadioButton();
        radioFemme = new RadioButton();
        btnAnnuler = new Button();
        btnCreer = new Button();
        btnCreer.setOnAction((Event) -> {
            btnCreerCompteActionPerformed();
        });
        build();
    }

    public RadioButton getRadioHomme() {
        return radioHomme;
    }

    public RadioButton getRadioFemme() {
        return radioFemme;
    }

    public ToggleGroup getRadioGroup() {
        return radioGroup;
    }

    public void setTxtUtilisateur(TextField txtUtilisateur) {
        this.txtUtilisateur = txtUtilisateur;
    }

    public void setTxtPrenom(TextField txtPrenom) {
        this.txtPrenom = txtPrenom;
    }

    public void setTxtNom(TextField txtNom) {
        this.txtNom = txtNom;
    }

    public void setTxtMotDePasse(PasswordField txtMotDePasse) {
        this.txtMotDePasse = txtMotDePasse;
    }

    public void setTxtConfirmation(PasswordField txtConfirmation) {
        this.txtConfirmation = txtConfirmation;
    }

    public void setTxtCourriel(TextField txtCourriel) {
        this.txtCourriel = txtCourriel;
    }

    public void setTxtAge(TextField txtAge) {
        this.txtAge = txtAge;
    }

    public Label getLblErreur() {
        return lblErreur;
    }

    public Button getBtnAnnuler() {
        return btnAnnuler;
    }

    public Button getBtnCreer() {
        return btnCreer;
    }

    public TextField getTxtUtilisateur() {
        return txtUtilisateur;
    }

    public TextField getTxtPrenom() {
        return txtPrenom;
    }

    public TextField getTxtNom() {
        return txtNom;
    }

    public PasswordField getTxtMotDePasse() {
        return txtMotDePasse;
    }

    public PasswordField getTxtConfirmation() {
        return txtConfirmation;
    }

    public TextField getTxtCourriel() {
        return txtCourriel;
    }

    public TextField getTxtAge() {
        return txtAge;
    }

    public FXMLControllerCreationCompte(Stage primaryStage) {
        this();
        this.primaryStage = primaryStage;
    }

    public void build() {
        radioFemme.setToggleGroup(radioGroup);
        radioHomme.setToggleGroup(radioGroup);
        btnCreer.setDefaultButton(true);
    }

    @Override
    public String getPathFXML() {
        return "CreationCompte.fxml";
    }

    @Override
    public String getTitre() {
        String titre = "Créer un compte";
        return titre;
    }

    public void btnCreerCompteActionPerformed() {

        if (validerAge()
                && validerChampsRemplis()
                && validerCourriel()
                && validerMotDePassesConcordants()) {
            InfoCreation ic = new InfoCreation();
            ic.username = txtUtilisateur.getText();
            ic.password = txtMotDePasse.getText();
            ic.email = txtCourriel.getText();
            ic.age = Integer.parseInt(txtAge.getText());
            ic.prenom = txtPrenom.getText();
            ic.nom = txtNom.getText();
            ic.image = "http://funnyfacebookimages.net/wp-content/uploads/2013/10/Original-Facebook-Geek-Profile-Avatar-1.jpg";
            if (radioFemme.isSelected()) {
                ic.sexe = "Femme";
            } else {
                ic.sexe = "Homme";
            }
            gui.aviserObservateurs(NSMMessenger.Observation.CREATION, ic);
        }
    }

    private boolean validerCourriel() {
        Pattern pattern = Pattern.compile("([A-Z0-9._%+-]+@+[A-Z0-9.-]+\\.[A-Z]{2,4})");
        Matcher matcher = pattern.matcher(txtCourriel.getText().toUpperCase());
        if (matcher.matches()) {
            return true;
        } else {
            lblErreur.setText("Le courriel est invalide!");
            return false;
        }

    }

    private boolean validerAge() {
        if (isInteger(txtAge.getText())) {
            int age = Integer.parseInt(txtAge.getText());
            if (age > AGE_MIN && age < AGE_MAX) {
                return true;
            } else {
                lblErreur.setText("L'age doit être entre " + AGE_MIN + " et " + AGE_MAX);
            }
        } else {
            lblErreur.setText("L'age doit etre un nombre");
        }
        return false;
    }

    private boolean validerMotDePassesConcordants() {
        if (txtMotDePasse.getText().equals(txtConfirmation.getText())) {
            return true;
        } else {
            lblErreur.setText("Vos mots de passes ne concordent pas!");
            return false;
        }

    }

    private boolean validerChampsRemplis() {

        if (!(txtUtilisateur.getText().isEmpty())
                && !(txtMotDePasse.getText().isEmpty())
                && !(txtConfirmation.getText().isEmpty())
                && !(txtCourriel.getText().isEmpty())
                && !(txtNom.getText().isEmpty())
                && !(txtPrenom.getText().isEmpty())
                && !(txtAge.getText().isEmpty())
                && (radioFemme.isSelected() || radioHomme.isSelected())) {
            return true;
        } else {
            lblErreur.setText("Veuillez remplir tous les champs");
            return false;
        }
    }

    public void btnAnnulerActionPerformed() {
        Dialog<ButtonType> d = new Dialog();
        d.setTitle("Annuler votre inscription");
        d.setContentText("Voulez vous vraiment annuler votre inscription?");
        d.initOwner(primaryStage);
        d.initModality(Modality.APPLICATION_MODAL);
        d.setHeaderText(null);
        d.setGraphic(null);
        d.getDialogPane().getButtonTypes().add(ButtonType.YES);
        d.getDialogPane().getButtonTypes().add(ButtonType.NO);
        Optional<ButtonType> reponse = d.showAndWait();
        if (reponse.isPresent() && reponse.get().equals(ButtonType.YES)) {
            gui.afficherPageLogin();
        }

    }

    public void EnterPressedHandler(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == evt.VK_ENTER) {
            btnCreerCompteActionPerformed();
        } else if (evt.getKeyCode() == evt.VK_ESCAPE) {
            btnAnnulerActionPerformed();
        }
    }

    public void rdoHommeItemStateChanged() {
        if (radioHomme.isSelected()) {
            radioFemme.setSelected(false);
        }
    }

    public void rdoFemmeItemStateChanged() {
        if (radioFemme.isSelected()) {
            radioHomme.setSelected(false);
        }
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
