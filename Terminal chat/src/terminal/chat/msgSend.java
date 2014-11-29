/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.chat;

/**
 *
 * @author Saketh
 */
import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static terminal.chat.TerminalChat.Table;
import static terminal.chat.TerminalChat.statement;

public class msgSend extends Thread{
    Socket sock;
    String user;
    public msgSend(Socket socket, String u){
        sock = socket;
        user=u;
    }
    
    @Override
    public void run(){
        try{
            Scanner inp = new Scanner(System.in);
            String msg = "hi";
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());
            while(!"bye".equals(msg)){
                msg = inp.nextLine();
                output.writeUTF(user+" :" +msg);
            }
            try {
                statement.executeUpdate("UPDATE "+Table+" SET status = 'dead' WHERE Name = '"+user+"'");
            } catch (SQLException ex) {
                Logger.getLogger(msgSend.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch(IOException e){}
    }
}
