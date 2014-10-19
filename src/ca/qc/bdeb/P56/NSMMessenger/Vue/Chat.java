/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.P56.NSMMessenger.Vue;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ordinatron
 */
public class Chat {

    private String pathFXML = "../../ressources/chat.fxml";
    public Chat() {

    }

    public Chat(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(pathFXML));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Hello");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {

        }
    }
}
