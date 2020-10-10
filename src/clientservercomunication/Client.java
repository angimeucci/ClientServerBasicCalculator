/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientservercomunication;
import java.io.*;
import java.net.*;

/**
 *
 * @author informatica
 */
public class Client {
    BufferedReader input_tastiera;
    Socket socket;    
    String nome_server = "127.0.0.1";
    int num_porta = 6500;
    
    String operazione;
    String risposta;
    
    DataOutputStream dati_al_server;
    BufferedReader dati_dal_server;
    
    
    public Socket connetti(){
        input_tastiera=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Client in esecuzione.");
        try {
            socket=new Socket(nome_server,num_porta);
            dati_al_server=new DataOutputStream(socket.getOutputStream());
            dati_dal_server=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(UnknownHostException e){
            System.err.println("Host non riconosciuto.");
        }
        catch (Exception e) {
            System.out.println("Errore durante la connessione.");
        }
        return socket;
    }
    
    
    public void comunica(){
        try {
            System.out.println("Inserire l'operazione completa:");
            operazione = input_tastiera.readLine();
            System.out.println("Invio del messaggio al server.");
            dati_al_server.writeBytes(operazione+'\n');
            risposta=dati_dal_server.readLine();
            System.out.println("Risposta del server: "+risposta);
            socket.close();
        }
        catch (Exception e) {
            System.out.println("Errore durante la comunicazione col server.");
        }
    }
}