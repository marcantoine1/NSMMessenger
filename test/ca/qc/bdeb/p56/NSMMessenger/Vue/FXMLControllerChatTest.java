/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.p56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Vue.FXMLControllerChat;
import ca.qc.bdeb.P56.NSMMessenger.Vue.FXMLControllerChat.Lobby;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author patrick
 */
public class FXMLControllerChatTest {

    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    FXMLControllerChat chat;
    TreeView listeLobbyClient;
    Pane panelChat;
    TabPane tabPanelSalon;
    TextArea lblChat;
    TextArea txtChat;
    Tab tabSalons;
    Tab tabContacts;
    ListView listeContactsConnectes;
    ListView listeContactsDeconnectes;
    HashMap<String, FXMLControllerChat.Lobby> lobbyTabs;
    String[] listeLobbies = {"Lobby"};
    ArrayList<String> listeUtilisateurs;
    Lobby lobby;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        chat = new FXMLControllerChat();
        listeLobbyClient = chat.getListeLobbyClient();
        panelChat = chat.getPanelChat();
        tabPanelSalon = chat.getTabPanelSalon();
        lblChat = chat.getLblChat();
        txtChat = chat.getTxtChat();
        tabSalons = chat.getTabSalons();
        tabContacts = chat.getTabContacts();
        listeContactsConnectes = chat.getListeContactsConnectes();
        listeContactsDeconnectes = chat.getListeContactsDeconnectes();
        lobbyTabs = chat.getLobbyTabs();

        listeUtilisateurs = new ArrayList<>();
        listeUtilisateurs.add("Testeur");
        listeUtilisateurs.add("Testeur2");
        lobby = chat.new Lobby("Lobby", listeUtilisateurs);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAffichageDuTexteEnvoye() {
        chat.lobbyJoined(listeUtilisateurs, listeLobbies[0]);
        chat.ajouterMessage("Lobby", "Testeur", "Allo");
        assertEquals("\nTesteur: Allo", chat.getLblChat().getText());
    }
    
    @Test
    public void testAffichageDeuxUtilisateursEnvoyerTexte() {
        chat.lobbyJoined(listeUtilisateurs, listeLobbies[0]);
        chat.ajouterMessage("Lobby", "Testeur", "Salut");
        chat.ajouterMessage("Lobby", "Testeur2", "Bye je t'aime pas");
        assertEquals("\nTesteur: Salut\nTesteur2: Bye je t'aime pas", chat.getLblChat().getText());
    }

    @Test
    public void testNotifierConnectionUtilisateurDansLobby() {
        chat.build();
        chat.lobbyJoined(listeUtilisateurs, listeLobbies[0]);
        chat.updateLobbies(listeLobbies);
        chat.notifierConnectionClient("Lobby", "Testeur");
        assertEquals("\nTesteur s'est connecté au lobby.", lblChat.getText());
    }
    @Test
    public void testNotifierDeconnectionUtilisateurDansLobby() {
        chat.build();
        chat.lobbyJoined(listeUtilisateurs, listeLobbies[0]);
        chat.notifierConnectionClient("Lobby", "Testeur");
        chat.notifierDeconnectionClient("Lobby", "Testeur");
        assertEquals("\nTesteur s'est connecté au lobby.\nTesteur s'est déconnecté du lobby.", lblChat.getText());
    }
}
