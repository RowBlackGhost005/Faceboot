package com.masa.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that wraps a socket to run in an individual thread.
 * This class controls the I/O streams of the socket and its basic information.
 * 
 * @author Luis Angel Marin
 */
public class ClientSocket implements Runnable{
    
    //Class socket
    Socket clientSocket;
    
    PrintWriter socketSend;
    BufferedReader socketReceived;
    Communication server;
    
    private boolean isOn = true;
    
    /**
     * Creates a new ClientSocket, this socket will run in an individual thread and will handle the I/O streams of the given socket.
     * 
     * @param clientSocket The Socket to hold.
     * @param socketSend The stream to send information.
     * @param socketReceived The stream to receive information.
     * @param server Communication object that work as a server to send the information.
     */
    public ClientSocket(Socket clientSocket, PrintWriter socketSend, BufferedReader socketReceived, Communication server) {
        
        this.clientSocket = clientSocket;
        this.socketSend = socketSend;
        this.socketReceived = socketReceived;
        this.server = server;
    }
    
    /**
     * Sends the given message throught the socket.
     * @param message Message to send.
     */
    public void send(String message){
        socketSend.println(message);
    }
    
    /**
     * Receive the information that was sended at this socket.
     * @return Message received as String.
     * @throws IOException Exception while trying to read.
     */
    public String recieved() throws IOException{
        return socketReceived.readLine();
    }
    
    /**
     * Returns the Communication (Server) where this Client Socket is attached to.
     * @return Communication object where this socket was created.
     */
    public Communication getServer(){
        return this.server;
    }
    
    /**
     * Returns wether this Socket is On or Off in its input stream. 
     * @return True if is on, False otherwise.
     */
    public boolean isOn(){
        return this.isOn;
    }
    
    /**
     * Closes the Socket, the I/O Streams and stops this Thread.
     */
    public void shutdown(){
        try {
            clientSocket.close();
            socketSend.close();
            socketReceived.close();
            this.isOn = false;
            
        } catch (IOException ex) {
            System.out.println("Error while trying to shutdown a Peer");
            Logger.getLogger(ClientSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Starts to listen to the inputs at this ClientSocket.
     */
    @Override
    public void run() {
        while (isOn()) {
            try {
                String clientMessage = recieved();
                System.out.println("Listener: " + clientMessage);
                if (clientMessage != null) {
                    //System.out.println("Peer: " + clientMessage);
                    getServer().handleOperation(clientMessage, this);
                }
                
            } catch (IOException ex) {
                System.out.println("Error Hilo Received");
            } finally{
                shutdown();
            }
        }
    }
}