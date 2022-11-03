package com.masa.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerSocketHandler implements Runnable{

    private ServerSocket serverSocket;
    private Communication communication;
    
    public ServerSocketHandler(ServerSocket server, Communication communication){
        this.serverSocket = server;
        this.communication = communication;
    }

    @Override
    public void run() {
        while (true) {

            Socket clientSocket = null;

            try {
                //Waits till a connection is requested
                clientSocket = serverSocket.accept();
                
                

                communication.handlePeer(clientSocket);

            } catch (IOException e) {
                System.out.println("Error while trying to accept a new Peer");
            }
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
    
    

}
