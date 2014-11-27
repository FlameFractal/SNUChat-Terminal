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
import java.util.Scanner;

public class msgSend extends Thread{
    Socket sock;
    public msgSend(Socket socket){
        sock = socket;
    }
    
    @Override
    public void run(){
        try{
            Scanner inp = new Scanner(System.in);
            String msg = "hi";
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());
            while(!"bye".equals(msg)){
                System.out.print("User : ");
                msg = inp.nextLine();
                output.writeUTF(msg);
            }
            
        }catch(IOException e){}
    }
}
