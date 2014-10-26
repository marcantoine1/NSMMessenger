/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Controleur.NSMMessenger;
import ca.qc.bdeb.P56.NSMMessengerCommunication.Message;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

/**
 *
 * @author Ordinatron
 */
public class Chat extends Fenetre {

    private final HashMap<String, Lobby> lobbyTabs = new HashMap<>();

    private static final String titre = "NSM Messenger";
    private static final String pathFXML = "chat.fxml";

    private final String cssAntiHighlight = "-fx-focus-color: grey;";
    //Liste des contacts du gui. Contient des données temporaires.
    ObservableList<String> contacts = FXCollections.observableArrayList("CoolGuillaume", "Robert", "Paul", "John");

    @FXML
    private TreeView listeLobbyClient;

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
    private TextField txtChat;
    @FXML
    private Tab tabSalons;
    @FXML
    private Tab tabContacts;
    @FXML
    private Button btnCreerLobby;
    @FXML
    private ListView listeContacts;
    private final Node rootIcon = new ImageView(
            new Image("file:iconeMSN.png", 25, 25, false, false)
    );

    public Chat() {
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
                        }
                    }
                });

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

    public void updateLobbies(String[] lobbies) {

        ArrayList<String> listeLobbies = new ArrayList<>();
        for (String l : lobbies) {
            listeLobbies.add(l);
        }

        TreeItem<String> rootItem = listeLobbyClient.getRoot();
        for (String s : listeLobbies) {
            TreeItem<String> lobbyTreeItem = new TreeItem<>(s);
            if (!rootItem.getChildren().contains(lobbyTreeItem)) {
                TreeItem<String> salon = lobbyTreeItem;
                rootItem.getChildren().add(salon);
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
                                    
                                    TreeItem<String> item = (TreeItem<String>)listeLobbyClient.getSelectionModel().getSelectedItem();
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
                                        if (!panneauTrouve && parent.getValue().equals("Salons")) {
                                            gui.aviserObservateurs(NSMMessenger.Observation.JOINLOBBY, lobbyName);
                                        } else if(parent.getValue().equals("Salons"))
                                        {
                                            tabPanelSalon.getSelectionModel().select(lobbyTabs.get(lobbyName).tab);
                                        }
                                        else if (item.getChildren().isEmpty())
                                            itemUtilisateurDoubleClic(item.getValue());
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

    private Lobby getCurrentLobby() {
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
                listeLobbyClient.getSelectionModel().select(s);
                s.getChildren().addAll(lobby.getTreeUtilisateurs());
                s.setExpanded(true);
            }
        }
    }

    public void btnAjouterLobbyClic() {
        //TODO: Copier le code du chatprimitif
    }

    public void btnAjouterContactClic() {
        //TODO: Gestion de l'ajout des contacts
    }

    private void itemUtilisateurDoubleClic(String username) {
        gui.aviserObservateurs(NSMMessenger.Observation.PROFILEREQUEST, username);
    }

    @FXML
    private void keyPressedTxtChat(KeyEvent t) {
        if (t.getCode().equals(KeyCode.ENTER) && !t.isAltDown()) {

            gui.aviserObservateurs(NSMMessenger.Observation.ENVOIMESSAGE, new Message(getCurrentLobby().nom, txtChat.getText()));
            txtChat.setText("");
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
            if(this.equals(getCurrentLobby()))
                lblChat.setText(chatText);
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
