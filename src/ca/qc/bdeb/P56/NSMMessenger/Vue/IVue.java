/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessenger.Vue;

import ca.qc.bdeb.mvc.Observable;

/**
 * @author 1150580
 */
public interface IVue extends Observable {
    public void ajouterMessage(String message);

    public void lancerChat();

    public void afficherCreationCompte();

    public void showAccountCreated();

    public void showUsernameError();


    public void showLoginError();
}
