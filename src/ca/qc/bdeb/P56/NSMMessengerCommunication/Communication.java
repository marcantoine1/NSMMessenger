/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.P56.NSMMessengerCommunication;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;


/**
 *
 * @author 1150580
 */
public class Communication {
    public static final int PORT = 31456;
    
    public static void initialiserKryo(Kryo kryo)
    {
        kryo.register(LoginRequest.class);
        kryo.register(Message.class);
        kryo.register(LoginResponse.class);
    }
}
