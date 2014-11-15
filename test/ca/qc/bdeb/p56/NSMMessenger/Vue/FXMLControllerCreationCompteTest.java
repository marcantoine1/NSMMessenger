/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.p56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Vue.FXMLControllerCreationCompte;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author Ordinatron
 */
public class FXMLControllerCreationCompteTest {
    @Rule
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    TextField txtUtilisateur;
    TextField txtPrenom;
    TextField txtNom;
    TextField txtCourriel;
    TextField txtAge;
    RadioButton radioHomme;
    RadioButton radioFemme;
    PasswordField txtMotDePasse;
    PasswordField txtConfirmation;
    Button btnCreer;
    Button btnAnnuler;
    ToggleGroup groupe;
    Label lblErreur;
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {     
        FXMLControllerCreationCompte creationCompte = new FXMLControllerCreationCompte();
        txtUtilisateur = creationCompte.getTxtUtilisateur();
        txtPrenom = creationCompte.getTxtPrenom();
        txtNom = creationCompte.getTxtNom();
        txtAge = creationCompte.getTxtAge();
        txtCourriel = creationCompte.getTxtCourriel();
        txtConfirmation = creationCompte.getTxtConfirmation();
        txtMotDePasse = creationCompte.getTxtMotDePasse();
        groupe = creationCompte.getRadioGroup();
        lblErreur = creationCompte.getLblErreur(); 
        btnCreer = creationCompte.getBtnCreer();
        radioHomme = creationCompte.getRadioHomme();
        radioFemme = creationCompte.getRadioFemme();
        
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testMessageErreurMotDePasse() {
        txtUtilisateur.setText("Utilisateur");
        txtPrenom.setText("Prenom");
        txtNom.setText("Nom");
        txtCourriel.setText("abc@abc.ca");
        txtAge.setText("17");
        txtMotDePasse.setText("bob");
        txtConfirmation.setText("obo");
        groupe.selectToggle(radioHomme);
        groupe.selectToggle(radioFemme);
        radioFemme.setSelected(true);
        btnCreer.getOnAction().handle(new ActionEvent());
        assertEquals(lblErreur.getText(),"Vos mots de passes ne concordent pas!");
        
    }
    @Test
    public void testMessageErreurUtilisateurVide() {
        txtUtilisateur.setText("");
        txtPrenom.setText("Prenom");
        txtNom.setText("Nom");
        txtCourriel.setText("abc@abc.ca");
        txtAge.setText("17");
        txtMotDePasse.setText("bob");
        txtConfirmation.setText("bob");
        groupe.selectToggle(radioHomme);
        groupe.selectToggle(radioFemme);
        radioFemme.setSelected(true);
        btnCreer.getOnAction().handle(new ActionEvent());
        assertEquals(lblErreur.getText(),"Veuillez remplir tous les champs");
        
    }
        @Test
    public void testMessageErreurPrenomVide() {
        txtUtilisateur.setText("Utilisateur");
        txtPrenom.setText("");
        txtNom.setText("Nom");
        txtCourriel.setText("abc@abc.ca");
        txtAge.setText("17");
        txtMotDePasse.setText("bob");
        txtConfirmation.setText("bob");
        groupe.selectToggle(radioHomme);
        groupe.selectToggle(radioFemme);
        radioFemme.setSelected(true);
        btnCreer.getOnAction().handle(new ActionEvent());
        assertEquals(lblErreur.getText(),"Veuillez remplir tous les champs");
        
    }
        @Test
        public void testMessageErreurNomVide() {
        txtUtilisateur.setText("Utilisateur");
        txtPrenom.setText("Prenom");
        txtNom.setText("");
        txtCourriel.setText("abc@abc.ca");
        txtAge.setText("17");
        txtMotDePasse.setText("bob");
        txtConfirmation.setText("bob");
        groupe.selectToggle(radioHomme);
        groupe.selectToggle(radioFemme);
        radioFemme.setSelected(true);
        btnCreer.getOnAction().handle(new ActionEvent());
        assertEquals(lblErreur.getText(),"Veuillez remplir tous les champs");
        
    }
        @Test
        public void testMessageErreurCourrielNonValide() {
        txtUtilisateur.setText("Utilisateur");
        txtPrenom.setText("Prenom");
        txtNom.setText("Nom");
        txtCourriel.setText("abcabc.ca");
        txtAge.setText("17");
        txtMotDePasse.setText("bob");
        txtConfirmation.setText("bob");
        groupe.selectToggle(radioHomme);
        groupe.selectToggle(radioFemme);
        radioFemme.setSelected(true);
        btnCreer.getOnAction().handle(new ActionEvent());
        assertEquals(lblErreur.getText(),"Le courriel est invalide!");
        
    }
        @Test
        public void testMessageErreurAgeNonValide() {
        txtUtilisateur.setText("Utilisateur");
        txtPrenom.setText("Prenom");
        txtNom.setText("Nom");
        txtCourriel.setText("abc@abc.ca");
        txtAge.setText("170");
        txtMotDePasse.setText("bob");
        txtConfirmation.setText("bob");
        groupe.selectToggle(radioHomme);
        groupe.selectToggle(radioFemme);
        radioFemme.setSelected(true);
        btnCreer.getOnAction().handle(new ActionEvent());
        assertEquals(lblErreur.getText(),"L'age doit être entre 1 et 100");        
    }
        @Test
        public void testMessageErreurAgeNonChiffre() {
        txtUtilisateur.setText("Utilisateur");
        txtPrenom.setText("Prenom");
        txtNom.setText("Nom");
        txtCourriel.setText("abc@abc.ca");
        txtAge.setText("abc");
        txtMotDePasse.setText("bob");
        txtConfirmation.setText("bob");
        groupe.selectToggle(radioHomme);
        groupe.selectToggle(radioFemme);
        radioFemme.setSelected(true);
        btnCreer.getOnAction().handle(new ActionEvent());
        assertEquals(lblErreur.getText(),"L'age doit etre un nombre");       
    }
        @Test
        public void testMessageErreurSexeNonSelectionne() {
        txtUtilisateur.setText("Utilisateur");
        txtPrenom.setText("Prenom");
        txtNom.setText("Nom");
        txtCourriel.setText("abc@abc.ca");
        txtAge.setText("17");
        txtMotDePasse.setText("bob");
        txtConfirmation.setText("bob");
        btnCreer.getOnAction().handle(new ActionEvent());
        assertEquals(lblErreur.getText(),"Veuillez remplir tous les champs");       
    }
}
