/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientservercomunication;

/**
 *
 * @author informatica
 */
public class ServerApplication{
    public static void main(String argv[]) {
        // TODO code application logic here
            Server server = new Server();
            server.attendi();
            server.comunica();    
    }
}
