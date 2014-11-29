/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package terminal.chat;

import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 *
 * @author Saketh
 */
public class msgRecv extends Thread{
    Socket sock;
    public msgRecv(Socket socket){
        sock = socket;
    }
    
    @Override
    public void run(){
        try{
            Scanner inp = new Scanner(System.in);
            String msg = "hi";
            DataInputStream input = new DataInputStream(sock.getInputStream());
            while(!"bye".equals(msg)){
                //System.out.print("User : ");
                msg = input.readUTF();
                System.out.println("Remote : "+msg);
                msg = "";
            }
            
        }catch(IOException e){}
    }
}
