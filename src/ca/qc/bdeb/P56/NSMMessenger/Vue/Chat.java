/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import java.awt.PageAttributes;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.ConditionalFeature.FXML;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
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
import javax.swing.ImageIcon;

/**
 *
 * @author Ordinatron
 */
public class Chat{

    private Stage primaryStage;
    private final String cssAntiHighlight = "-fx-focus-color: transparent;-fx-background-insets: -1.4, 0, 1, 2;";
    //Liste des contacts du gui. Contient des données temporaires.
    ObservableList<String> contacts =FXCollections.observableArrayList ("CoolGuillaume", "Robert", "Paul", "John");
    private String pathFXML = "chat.fxml";
    @FXML
    private TreeView listeLobbyClient;
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
    private final Node rootIcon = new ImageView(
        new Image(getClass().getResourceAsStream("../../ressources/iconeMSN.png"))
    );
    public Chat(){
        
    }
    public Chat(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
    public void build(){
        retirerGlow();
        construireListeSalons();
        construireListeContacts();
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
    private void construireListeSalons(){
        //TODO: Remplacer par le code qui va chercher les vrais salons.
        TreeItem<String> rootItem = new TreeItem<String> ("Salons");
        rootItem.setExpanded(true);
        for (int i = 0; i < 5; i++) {
            TreeItem<String> salon = new TreeItem<String> ("Salon " + i);
            for (int j = 0; j < 10; j++) {
                TreeItem<String> utilisateur = new TreeItem<String> ("Utilisateur " + j);
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
}
