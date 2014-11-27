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
public class TerminalChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Socket sock = null;
        String ans;
        String ip;
        Scanner inp = new Scanner(System.in);
        
        do{
        System.out.println("1.Start Server");
        System.out.println("2.Connect\nans: ");
        ans = inp.nextLine();
        if( null != ans)
            switch (ans) {
                case "1":
                    sock = server();
                    break;
                case "2":
                    System.out.print("Please enter the remote IP : ");
                    ip = inp.nextLine();
                    sock = connect(ip);
                    break;
            }
        }while(!("1".equals(ans) || "2".equals(ans)));
        msgSend msgU = new msgSend(sock);
        msgRecv msgR = new msgRecv(sock);
        
        msgU.start();
        msgR.start();
    }
    
    public static Socket connect(String ip){
        try{
            Socket sock = new Socket(ip, 6789);
            return sock;
        }
        catch(IOException e){
            System.out.println(e);
            return null;
        }
    }
    
    public static Socket server(){
        try{
            ServerSocket server = new ServerSocket(6789);
            Socket sock = server.accept();
            return sock;
        }catch(IOException e){
            System.out.println(e);
            return null;
        }
    }
}
