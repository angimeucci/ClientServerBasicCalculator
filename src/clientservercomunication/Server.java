/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientservercomunication;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author informatica
 */
public class Server {
    ServerSocket socket_server=null;
    Socket socket_client=null;
    
    String messaggio_client=null;
    String risposta_server=null;
    
    BufferedReader dati_dal_client;
    DataOutputStream dati_al_client;
    
    public Socket attendi(){
        try {
            System.out.println("Server in esecuzione.");
            socket_server=new ServerSocket(6500);
            System.out.println("Server in attesa del client.");
            socket_client=socket_server.accept();
            System.out.println("Client connesso.");
            dati_dal_client=new BufferedReader(new InputStreamReader(socket_client.getInputStream()));
            dati_al_client=new DataOutputStream(socket_client.getOutputStream());
        }
        catch (Exception e) {
            System.out.println("Errore nell'istanziamento del server.");
        }
        return socket_client;
    }
    
    
    public void comunica(){
        String[] s;
        int risultato;
        try {
            System.out.println("In attesa dei messaggi da parte del client.");
            messaggio_client=dati_dal_client.readLine();
            s = messaggio_client.split(" ");
            System.out.println("Messaggio ricevuto.");
            System.out.println("Formulazione della risposta in corso..");
            switch(s[1]){
                case "*":
                    risultato = Integer.valueOf(s[0])*Integer.valueOf(s[2]);
                break;
                case ":":
                    risultato = Integer.valueOf(s[0])/Integer.valueOf(s[2]);
                break;
                case "+":
                    risultato = Integer.valueOf(s[0])+Integer.valueOf(s[2]);
                break;
                case "-":
                    risultato = Integer.valueOf(s[0])-Integer.valueOf(s[2]);
                break;
                default:
                    throw new Exception();
            }
            risposta_server = String.valueOf(risultato);
            System.out.println("Invio della risposta al client.");
            dati_al_client.writeBytes(risposta_server+'\n');
            System.out.println("Elaborazione completata.");
            socket_client.close();
        }
        catch (Exception e) {
            System.out.println("Errore del server durante la comunicazione.");
        }
    }
}
