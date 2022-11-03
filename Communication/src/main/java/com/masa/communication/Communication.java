package com.masa.communication;

import com.masa.businesslogic.IBusinessLogic;
import domain.Peer;
import domain.Request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that controls the inputs and outputs of the sockets and the connections
 * that come into this Peer.
 *
 * @author Luis Angel Marin
 */
public class Communication {

    private boolean isOn;

    //Must be the same in the Peer subsystem.
    //It represents the port where this communication is listening.
    private int serverPort;
    
    private int directoryPeerPort = 4444;

    private ServerSocketHandler serverSocket;
    
//    private ServerSocket serverSocket;
    private Socket clientSocket;
    
    private String name;

    private Peer peer;
    
    //Holds all current connections.
    private static ArrayList<ClientSocket> peers = new ArrayList<>();

    private Serializer serializer;

    private ICommHandler commHandler;
    
    private ArrayList<Peer> activePeers = new ArrayList<>();

    /**
     * Creates a Communication object with the given name and starts the
     * sockets. The port at which this component will listen its 4444 by
     * default.
     *
     * @param name Name of this communication component.
     */
    public Communication(String name, IBusinessLogic businessLogic) {


        Socket socket = null;
        ClientSocket firstConnection = null;

        this.isOn = false;

        //Connects into Directory Peer to register.
        try {
//            socket = new Socket("localhost", directoryPeerPort);
//
//            PrintWriter socketSend = new PrintWriter(socket.getOutputStream(), true);
//            BufferedReader socketReceived = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//
//            firstConnection = new ClientSocket(socket, socketSend, socketReceived, this);
//            
            firstConnection = createConnectionSocket(directoryPeerPort);
            
            Thread newSocketThread = new Thread(firstConnection, name);

            newSocketThread.start();

            Request request = new Request("netregister", "Peersito");

            this.serializer = new Serializer();
            this.commHandler = new CommHandler(this, businessLogic);

            firstConnection.send(serializer.Serialize(request));
            
            try {
                newSocketThread.join();

                
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        } catch (IOException ex) {
            System.out.println("Error trying to connect to directory peer");
        } finally {
            firstConnection.shutdown();
        }

        System.out.println("Ya tengo puerto! : " + serverPort);

        //TODO connection into Redudancy Peer to fetch all new information in the BDs.
        //END TODO.
        //Starts the server socket and starts to listen in the given port.
        
        startListen();

    }

    /**
     * Starts to listen with the server socket in the selected port, all new
     * connections will be handled by this object only serverSocket.
     */
    public void startListen() {

        ServerSocketHandler serverHandler;
        
        System.out.println("Start listening");
        
        try {
            serverHandler = new ServerSocketHandler(new ServerSocket(serverPort), this);

            Thread serverHandlerThread = new Thread(serverHandler);
            
            serverHandlerThread.start();
            
            System.out.println("Estoy listo!");
            
            this.isOn = true;
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the connection of the new socket given as parameter. The socket
     * will be wrapped in a ClientSocket object that handles the I/O streams and
     * runs in a individual thread to keep the connection alive.
     *
     * The new socket will be storage in this communication list.
     *
     * @param socket Socket to add.
     * @throws IOException Exception while trying to open the I/O streams.
     */
    public void handlePeer(Socket socket) throws IOException {

        //Creates the streams for I/O between sockets.
        PrintWriter socketSend = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader socketReceived = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //Creates a new socket to establish the connection between Peers.
        ClientSocket newClientSocket = new ClientSocket(socket, socketSend, socketReceived, this);

        peers.add(newClientSocket);
        
        Thread newSocketThread = new Thread(newClientSocket, name);
        
        newSocketThread.start();
    }

    /**
     * Handles the request that got send into an active socket of this
     * communication module. This handler deserializes the request first into a
     * request to send it to the communication handler.
     *
     * @param operation Request to handle.
     */
    public void handleOperation(String operation, ClientSocket peer) {

        Request request = serializer.deSerialize(operation);

        commHandler.handleOperation(request, peer);
    }

    /**
     * Removes the given peer of this communication list.
     *
     * @param peer Peer to remove
     */
    public void removePeer(ClientSocket peer) {
        peers.remove(peer);
        peer.shutdown();
    }

    /**
     * Sends the given request to the given Peer.
     *
     * @param request Request to send.
     * @param peer Peer to receive the request.
     */
    public void send(Request request, ClientSocket peer) {

        String requestSerialized = serializer.Serialize(request);

        ClientSocket peerToSend = null;

        for (ClientSocket socket : peers) {
            if (socket.equals(peer)) {
                peerToSend = socket;
                break;
            }
        }

        peerToSend.send(requestSerialized);

        this.removePeer(peerToSend);
    }
    
    /**
     * Sends the given request to the given Peer.
     *
     * @param request Request to send.
     * @param peer Peer to receive the request.
     */
    public void sendToDirectory(Request request, ClientSocket peer) {

        try {
            ClientSocket directoryPeer = createConnectionSocket(directoryPeerPort);
            
            directoryPeer.send(serializer.Serialize(request));
            
//        String requestSerialized = serializer.Serialize(request);
//
//        ClientSocket peerToSend = null;
//
//        for (ClientSocket socket : peers) {
//            if (socket.equals(peer)) {
//                peerToSend = socket;
//                break;
//            }
//        }
//
//        peerToSend.send(requestSerialized);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendToAllPeers(Request request){
        
        try {
            
            
            Request requestAllPeers = new Request("getactivepeers", "Send peers");
            
            ClientSocket directoryPeer = createConnectionSocket(directoryPeerPort);

            Thread newSocketThread = new Thread(directoryPeer, name);

            newSocketThread.start();

            sendToDirectory(requestAllPeers, directoryPeer);
            
            System.out.println("Waiting the thread to finish");
            newSocketThread.join();
            
            
        } catch (IOException ex) {
            System.out.println("Error while sending operation to all peers");
        } 
        catch (InterruptedException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Sending to all peers");
        
        for(Peer peer : activePeers){
            
            try {
                ClientSocket peerClient = createConnectionSocket(peer.getPort());
                
                Thread newSocketThread = new Thread(peerClient , name);
                
                newSocketThread.start();
                
                send(request, peerClient);
                
                newSocketThread.join();
                
                System.out.println("Sended to a peer");
                
            } catch (IOException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        activePeers = null;
    }
    
    
    public ClientSocket createConnectionSocket(int port) throws IOException{
        
        //Creates a new socket to connect
        Socket socket = new Socket("localhost", port);
        
        //Creates the streams for I/O between sockets.
        PrintWriter socketSend = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader socketReceived = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        //Creates a new socket to establish the connection between Peers.
        ClientSocket newClientSocket = new ClientSocket(socket , socketSend, socketReceived, this);

        peers.add(newClientSocket);
        
        return newClientSocket;
    }
    
    public void setActivePeers(ArrayList<Peer> connectedPeers){
        this.activePeers = connectedPeers;
    }
    
    public ICommHandler getCommHandler(){
        return this.commHandler;
    }
    
    /**
     * Configures this Peer with the credentials given as parameter. Those
     * credentials should be given by the Directory peer and must be assigned
     * immediately once the peer is started.
     *
     * @param peer Credentials to configure into this peer.
     */
    public void configurePeer(Peer peer) {
        this.name = peer.getName();
        this.serverPort = peer.getPort();
        this.peer = peer;
    }
    
}
