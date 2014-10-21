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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Ordinatron
 */
public class Chat{

    private final HashMap<String, Lobby> lobbyTabs = new HashMap<>();
    private Stage primaryStage;
    private final String cssAntiHighlight = "-fx-focus-color: grey;";
    //Liste des contacts du gui. Contient des données temporaires.
    ObservableList<String> contacts =FXCollections.observableArrayList ("CoolGuillaume", "Robert", "Paul", "John");
    private final String pathFXML = "chat.fxml";
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
    private Tab tabModelSalon;
    @FXML
    private Tab tabSalons;
    @FXML
    private Tab tabContacts;
    @FXML
    private Button btnCreerLobby;
    @FXML
    private ListView listeContacts;
    private ChatGUI gui;
    private final Node rootIcon = new ImageView(
        new Image("file:iconeMSN.png", 25, 25, false, false)
    );
    public Chat(){
        
    }
    public Chat(Stage primaryStage){
        this.primaryStage = primaryStage;
        TreeItem<String> rootItem = new TreeItem<> ("Salons");
        rootItem.setExpanded(true);
        listeLobbyClient.setRoot(rootItem);
    }
    public void build(){
        retirerGlow();
        construireListeSalons();
        construireListeContacts();
        listeLobbyClient.setShowRoot(false);
    }
    private void retirerGlow(){
        listeLobbyClient.setStyle(cssAntiHighlight);
        panelChat.setStyle(cssAntiHighlight);
        tabPanelSalon.setStyle(cssAntiHighlight);
        lblChat.setStyle(cssAntiHighlight);
        txtChat.setStyle(cssAntiHighlight);
        tabModelSalon.setStyle(cssAntiHighlight);
        tabSalons.setStyle(cssAntiHighlight);
        tabContacts.setStyle(cssAntiHighlight);
        btnCreerLobby.setStyle(cssAntiHighlight);       
    }
    public void setGUI(ChatGUI gui){
        this.gui = gui;
    }
    private void construireListeSalons(){
        //TODO: Remplacer par le code qui va chercher les vrais salons.
        TreeItem<String> rootItem = new TreeItem<String> ("Salons");
        rootItem.setExpanded(true);
        for (int i = 0; i < 5; i++) {
            TreeItem<String> salon = new TreeItem<String> ("Salon " + i);
            for (int j = 0; j < 10; j++) {
                TreeItem<String> utilisateur = new TreeItem<String> ("Utilisateur " + j, rootIcon);
                salon.getChildren().add(utilisateur);
            }
            rootItem.getChildren().add(salon);
        }
        listeLobbyClient.setRoot(rootItem);
    }
    private void construireListeContacts(){
        //TODO: Logique d'obtention des contacts coté serveur
        listeContacts.setItems(contacts);
    }
    
    
    
    public void ajouterMessage(String lobby, String user, String s){
        lobbyTabs.get(lobby).ajouterMessage(user + ": " + s);
    }
    public void notifierConnectionClient(String lobby, String nom){
        lobbyTabs.get(lobby).ajouterMessage(nom + " s'est connecté au lobby.");
    }
    public void notifierDeconnectionClient(String lobby, String nom){
        lobbyTabs.get(lobby).ajouterMessage(nom + " s'est déconnecté du lobby.");
    }
    public void updateLobbies(String[] lobbies){
        
        ArrayList<String> listeLobbies = new ArrayList<>();
        for(String l : lobbies)
            listeLobbies.add(l);
        
        TreeItem<String> rootItem = listeLobbyClient.getRoot();
        for (String s : listeLobbies) {
            TreeItem<String> lobbyTreeItem = new TreeItem<>(s);
            if(!rootItem.getChildren().contains(lobbyTreeItem))
            {
                TreeItem<String> salon = lobbyTreeItem;
                rootItem.getChildren().add(salon);
            }
        }
        
        for(TreeItem<String> s : rootItem.getChildren())
        {
            if(!listeLobbies.contains(s.getValue()) && !lobbyTabs.containsKey(s.getValue()))
                rootItem.getChildren().remove(s);
        }
        
        listeLobbyClient.setRoot(rootItem);
        
    }
    
    private void beforeTabChanged()
    {
        getCurrentLobby().saveState();
    }
    
    private void afterTabChanged()
    {
        getCurrentLobby().loadState();
        chargerListeUtilisateurs();
    }
    
    public void lobbyJoined(ArrayList<String> liste, String nomLobby){
        Lobby lobby = new Lobby(nomLobby, liste);
        lobbyTabs.put(nomLobby, lobby);
        tabPanelSalon.getTabs().add(lobby.tab);
    }
    
    private Lobby getCurrentLobby()
    {
        return lobbyTabs.get(tabPanelSalon.getSelectionModel().getSelectedItem().getText());
    }
    
    private void chargerListeUtilisateurs()
    {
        Lobby currentLobby = getCurrentLobby();
        TreeItem<String> rootItem = listeLobbyClient.getRoot();
        for(TreeItem<String> s : rootItem.getChildren())
        {
            s.getChildren().clear();
            if(s.getValue().equals(currentLobby.nom))
                s.getChildren().addAll(currentLobby.getTreeUtilisateurs());      
        }
    }
    private void btnAjouterLobbyClic(){
        //TODO: Copier le code du chatprimitif
    }
    private void btnAjouterContactClic(){
        //TODO: Gestion de l'ajout des contacts
    }
    private void itemUtilisateurDoubleClic(){
        gui.aviserObservateurs(NSMMessenger.Observation.PROFILEREQUEST, gui);
    }
    private void changementTabSelecte(){
        
    }
    private void enterPressedTxtChat(){
        gui.aviserObservateurs(NSMMessenger.Observation.ENVOIMESSAGE, new Message(getCurrentLobby().nom, txtChat.getText(), null));
        txtChat.setText("");
    } 
    
    public class Lobby
    {
        private Tab tab;
        private String nom;
        private String chatText;
        private String chatBoxText;
        private ArrayList<String> utilisateurs;
        
        public Lobby(String nom, ArrayList<String> utilisateurs)
        {
            tab = new Tab(nom);
            this.nom = nom;
            chatText = "";
            chatBoxText = "";
            this.utilisateurs = utilisateurs;
        }

        private void ajouterMessage(String message) {
            this.chatText+= '\n' + message;
        }
        
        public void saveState()
        {
            chatText = lblChat.getText();
            chatBoxText = txtChat.getText();
        } 
        
        public void loadState()
        {
            lblChat.setText(chatText);
            txtChat.setText(chatBoxText);
        }
        
        public ArrayList<TreeItem<String>> getTreeUtilisateurs()
        {
            ArrayList<TreeItem<String>> treeUtilisateurs = new ArrayList<>();
            for(String s : utilisateurs)
                treeUtilisateurs.add(new TreeItem<>(s));
            return treeUtilisateurs;
        }
    }
}
