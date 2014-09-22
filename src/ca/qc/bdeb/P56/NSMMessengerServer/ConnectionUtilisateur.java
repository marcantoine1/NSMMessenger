/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerServer;

import com.esotericsoftware.kryonet.Connection;

/**
 *
 * @author 1150580
 */
public class ConnectionUtilisateur {
    public Connection connection;
    public String username;
    public ConnectionUtilisateur(Connection c, String u)
    {
        connection = c;
        username = u;
    }
}
