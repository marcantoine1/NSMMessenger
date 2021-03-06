package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Application.JukeBox;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger.Observation;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author Ordinatron
 */
public class FXMLControllerChat extends Fenetre {

    private final HashMap<String, Lobby> lobbyTabs = new HashMap<>();

    private static final String titre = "NSM Messenger";
    private static final String pathFXML = "chat.fxml";

    private final String cssAntiHighlight = "-fx-focus-color: grey;";

    @FXML
    private TreeView listeLobbyClient;
    private Stage primaryStage;

    public HashMap<String, Lobby> getLobbyTabs() {
        return lobbyTabs;
    }

    public TreeView getListeLobbyClient() {
        return listeLobbyClient;
    }

    @FXML
    private Pane panelChat;
    @FXML
    private TabPane tabPanelSalon;
    @FXML
    private TextArea lblChat;
    @FXML
    private TextArea txtChat;
    @FXML
    private Tab tabSalons;
    @FXML
    private Tab tabContacts;
    @FXML
    private Button btnCreerLobby;
    @FXML
    private ListView listeContactsConnectes;
    @FXML
    private ListView listeContactsDeconnectes;

    public Pane getPanelChat() {
        return panelChat;
    }

    public TabPane getTabPanelSalon() {
        return tabPanelSalon;
    }

    public TextArea getLblChat() {
        return lblChat;
    }

    public TextArea getTxtChat() {
        return txtChat;
    }

    public Tab getTabSalons() {
        return tabSalons;
    }

    public Tab getTabContacts() {
        return tabContacts;
    }

    public ListView getListeContactsConnectes() {
        return listeContactsConnectes;
    }

    public ListView getListeContactsDeconnectes() {
        return listeContactsDeconnectes;
    }

    private final Node rootIcon = new ImageView(
            new Image("file:iconeMSN.png", 25, 25, false, false)
    );

    public FXMLControllerChat() {
        tabPanelSalon = new TabPane();
        lblChat = new TextArea();
        listeLobbyClient = new TreeView();
    }

    public FXMLControllerChat(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void build() {
        TreeItem<String> rootItem = new TreeItem<>("Salons");
        rootItem.setExpanded(true);
        listeLobbyClient.setRoot(rootItem);
        listeLobbyClient.setShowRoot(false);

        tabPanelSalon.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {

                        if (t != null) {
                            lobbyTabs.get(t.getText()).saveState();
                        }
                        if (t1 != null) {
                            lobbyTabs.get(t1.getText()).loadState();
                            chargerListeUtilisateurs(lobbyTabs.get(t1.getText()));
                            verifierDernierLobby();
                        }
                    }
                });
        tabPanelSalon.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPanelSalon.getTabs().addListener(new ListChangeListener<Tab>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Tab> change) {

                while (change.next()) {
                    for (Tab t : change.getRemoved()) {
                        lobbyTabs.remove(t.getText());
                        gui.aviserObservateurs(Observation.LEAVELOBBY, t.getText());
                        verifierDernierLobby();
                    }
                }

            }

        });

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
    public void logoutClick(){
        gui.aviserObservateurs(Observation.LOGOUTREQUEST, "parametre");
    }
    private void verifierDernierLobby() {
        if (lobbyTabs.size() < 2) {
            tabPanelSalon.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        } else {
            tabPanelSalon.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        }
    }

    public void ajouterMessage(String lobby, String user, String s) {
        lobbyTabs.get(lobby).ajouterMessage(user + ": " + s);
    }

    public void notifierConnectionClient(String lobby, String nom) {
        lobbyTabs.get(lobby).ajouterUtilisateur(nom);
    }

    public void notifierDeconnectionClient(String lobby, String nom) {
        lobbyTabs.get(lobby).enleverUtilisateur(nom);
    }

    public void updateContacts(ArrayList<String> utilisateurs, ArrayList<String> connectes) {

        Platform.runLater(() -> {
            listeContactsConnectes.getItems().clear();
            listeContactsDeconnectes.getItems().clear();
            for (String utilisateur : utilisateurs) {
                if (connectes.contains(utilisateur)) {
                    listeContactsConnectes.getItems().add(utilisateur);

                } else {
                    listeContactsDeconnectes.getItems().add(utilisateur);
                }
            }
        });
    }

    public void updateLobbies(String[] lobbies) {

        ArrayList<String> listeLobbies = new ArrayList<>();
        for (String l : lobbies) {
            listeLobbies.add(l);
        }

        TreeItem<String> rootItem = listeLobbyClient.getRoot();
        for (String string : listeLobbies) {
            boolean lobbyTrouve = false;
            for (TreeItem<String> treeString : rootItem.getChildren()) {
                if (treeString.getValue().equals(string)) {
                    lobbyTrouve = true;
                }
            }
            if (!lobbyTrouve) {
                rootItem.getChildren().add(new TreeItem<>(string));
            }
        }

        for (TreeItem<String> s : rootItem.getChildren()) {
            if (!listeLobbies.contains(s.getValue()) && !lobbyTabs.containsKey(s.getValue())) {
                rootItem.getChildren().remove(s);
            }
        }
        listeLobbyClient.setRoot(rootItem);
        listeLobbyClient.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> paramP) {
                return new TreeCell<String>() {
                    @Override
                    protected void updateItem(String paramT, boolean paramBoolean) {
                        super.updateItem(paramT, paramBoolean);
                        if (!isEmpty()) {
                            setGraphic(new Label(paramT));
                            this.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {

                                    TreeItem<String> item = (TreeItem<String>) listeLobbyClient.getSelectionModel().getSelectedItem();
                                    item.setExpanded(true);

                                    if (event.getClickCount() == 2) {
                                        String lobbyName = item.getValue();
                                        boolean panneauTrouve = false;
                                        for (Tab t : tabPanelSalon.getTabs()) {
                                            if (t.getText().equals(lobbyName)) {
                                                panneauTrouve = true;
                                            }
                                        }
                                        TreeItem<String> parent = item.getParent();
                                        if (!panneauTrouve && parent.equals(rootItem)) {
                                            gui.aviserObservateurs(NSMMessenger.Observation.JOINLOBBY, lobbyName);
                                        } else if (parent.equals(rootItem)) {
                                            tabPanelSalon.getSelectionModel().select(lobbyTabs.get(lobbyName).tab);
                                        } else {
                                            itemUtilisateurDoubleClic(item.getValue());
                                        }
                                    }
                                }
                            });
                        }
                    }
                };
            }
        });
    }

    public void lobbyJoined(ArrayList<String> liste, String nomLobby) {
        Lobby lobby = new Lobby(nomLobby, liste);
        lobbyTabs.put(nomLobby, lobby);
        tabPanelSalon.getTabs().add(lobby.tab);
        tabPanelSalon.getSelectionModel().select(lobby.tab);
    }

    public Lobby getCurrentLobby() {
        String salonRecherche = tabPanelSalon.getSelectionModel().getSelectedItem().getText();
        return lobbyTabs.get(salonRecherche);
    }

    private void chargerListeUtilisateurs() {
        chargerListeUtilisateurs(getCurrentLobby());
    }

    private void chargerListeUtilisateurs(Lobby lobby) {
        TreeItem<String> rootItem = listeLobbyClient.getRoot();
        for (TreeItem<String> s : rootItem.getChildren()) {
            s.getChildren().clear();
            if (s.getValue().equals(lobby.nom)) {
                s.getChildren().addAll(lobby.getTreeUtilisateurs());
                s.setExpanded(true);
            }
        }
    }

    public void changerThemeDark() {
        String css = getClass().getResource("../../Ressources/CSS/DarkTheme.css").toExternalForm();
        panelChat.getScene().getStylesheets().clear();
        panelChat.getScene().getStylesheets().add(css);
        gui.appliquerDarkTheme();
    }

    public void changerThemeBlue() {
        String css = getClass().getResource("../../Ressources/CSS/BlueTheme.css").toExternalForm();
        panelChat.getScene().getStylesheets().clear();
        panelChat.getScene().getStylesheets().add(css);
        gui.appliquerBlueTheme();
    }
    

    @FXML
    private void contactDoubleClickConnecte(MouseEvent e) {
        Object item;
        if (e.getClickCount() == 2) {
            if ((item = listeContactsConnectes.getSelectionModel().getSelectedItem()) != null) {
                itemUtilisateurDoubleClic((String) item);
            }
        }
    }

    @FXML
    private void contactDoubleClickDeconnecte(MouseEvent e) {
        Object item;
        if (e.getClickCount() == 2) {
            if ((item = listeContactsDeconnectes.getSelectionModel().getSelectedItem()) != null) {
                itemUtilisateurDoubleClic((String) item);
            }
        }
    }
    @FXML
    private void btnAjouterLobbyClic() {

        TextInputDialog lobbyDialog = new TextInputDialog();
        lobbyDialog.setContentText("Entrez le nom du lobby:");
        lobbyDialog.setTitle("Créer un lobby");
        lobbyDialog.initOwner(primaryStage);
        lobbyDialog.initModality(Modality.APPLICATION_MODAL);
        lobbyDialog.setHeaderText(null);
        lobbyDialog.setGraphic(null);
        Optional<String> response = lobbyDialog.showAndWait();

        if (response.isPresent()) {
            gui.aviserObservateurs(Observation.CREERLOBBY, response.get());
        }

    }

    public void btnAjouterContactClic() {
        TextInputDialog lobbyDialog = new TextInputDialog();
        lobbyDialog.setContentText("Entrez le nom du contact:");
        lobbyDialog.setTitle("Ajouter un contact");
        lobbyDialog.initOwner(primaryStage);
        lobbyDialog.initModality(Modality.APPLICATION_MODAL);
        lobbyDialog.setHeaderText(null);
        lobbyDialog.setGraphic(null);
        Optional<String> response = lobbyDialog.showAndWait();
        if (response.isPresent()) {
            gui.aviserObservateurs(Observation.CONTACTREQUEST, response.get());
        }
    }

    private void itemUtilisateurDoubleClic(String username) {
        gui.aviserObservateurs(Observation.PROFILEREQUEST, username);
    }

    @FXML
    private void keyPressedTxtChat(KeyEvent t) {
        if (t.getCode().equals(KeyCode.ENTER)) {
            if (!t.isShiftDown()) {

                gui.aviserObservateurs(Observation.ENVOIMESSAGE, new Message(getCurrentLobby().nom, txtChat.getText()));
                txtChat.setText("");
                t.consume();
            } else {
                txtChat.appendText("\n");
            }
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

    public class Lobby {

        private Tab tab;
        private String nom;
        private String chatText;
        private String chatBoxText;
        private ArrayList<String> utilisateurs;

        public String getNom() {
            return nom;
        }

        public String getChatText() {
            return chatText;
        }

        public String getChatBoxText() {
            return chatBoxText;
        }

        public ArrayList<String> getUtilisateurs() {
            return utilisateurs;
        }

        public Lobby(String nom, ArrayList<String> utilisateurs) {
            tab = new Tab(nom);
            this.nom = nom;
            chatText = "";
            chatBoxText = "";
            this.utilisateurs = utilisateurs;
        }

        private void ajouterUtilisateur(String nom) {
            ajouterMessage(nom + " s'est connecté au lobby.");
            utilisateurs.add(nom);
            if (this.equals(getCurrentLobby())) {
                chargerListeUtilisateurs();
            }
        }

        private void enleverUtilisateur(String nom) {
            ajouterMessage(nom + " s'est déconnecté du lobby.");
            utilisateurs.remove(nom);
            if (this.equals(getCurrentLobby())) {
                chargerListeUtilisateurs();
            }
        }

        private void ajouterMessage(String message) {
            this.chatText += '\n' + message;
            if (this.equals(getCurrentLobby())) {
                lblChat.setText(chatText);
            }
        }

        public void saveState() {
            chatText = lblChat.getText();
            chatBoxText = txtChat.getText();
        }

        public void loadState() {
            lblChat.setText(chatText);
            txtChat.setText(chatBoxText);
        }

        public ArrayList<TreeItem<String>> getTreeUtilisateurs() {
            ArrayList<TreeItem<String>> treeUtilisateurs = new ArrayList<>();

            for (String s : utilisateurs) {
                treeUtilisateurs.add(new TreeItem<>(s));
            }
            return treeUtilisateurs;
        }
    }
}
