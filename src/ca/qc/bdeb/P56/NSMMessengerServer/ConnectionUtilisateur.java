package ca.qc.bdeb.P56.NSMMessengerServer;

import com.esotericsoftware.kryonet.Connection;

/**
 *
 * @author 1150580
 */
public class ConnectionUtilisateur {
    public final Connection connection;
    public final String username;
    public ConnectionUtilisateur(Connection c, String u){
        connection = c;
        username = u;
    }
}
