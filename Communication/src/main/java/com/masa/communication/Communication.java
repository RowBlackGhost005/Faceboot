package com.masa.communication;

import com.masa.domain.Peer;
import com.masa.domain.Request;
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
 * Class that controls the in and out connections to this Peer
 *
 * @author Luis Angel Marin
 */
public class Communication {

    //Port where DirectoryPeer is.
    private int directoryPeerPort = 4444;

    //--Information about this Communication Component--//
    //Port of where this Communication is Listening.
    private int serverPort;

    private String name;

    private Peer peer;
    //--Information about this Communication Component--//

    private ServerSocketHandler serverHandler;

    //Holds all current connections.
    private static ArrayList<ClientSocket> peers = new ArrayList<>();

    private Serializer serializer;

    private ICommHandler commHandler;

    private ArrayList<Peer> activePeers = new ArrayList<>();
    
    private static int CONNECTION_COUNTER = 1;

    /**
     * Creates a Communication object. This object will establish a first
     * connection to the Directory Peer to get register itself as connected Peer
     * to get an ID and Port to start listen for incoming connections from other
     * Peers.
     *
     * @param name Name of this communication component.
     * @param businessLogic BusinessLogic to use to communicate.
     */
    public Communication(String name, ICommHandler commHandler) {

        this.name = name;
        this.serializer = new Serializer();
        this.commHandler = commHandler;
    }
    
    public void startUp(){
        
        Socket socket = null;
        ClientSocket firstConnection = null;

        //Connects into Directory Peer to register.
        try {

            firstConnection = createConnectionSocket(directoryPeerPort);

            Thread newSocketThread = new Thread(firstConnection, name);

            newSocketThread.start();

            Request request = new Request("netregister", "Peersito");

            firstConnection.send(serializer.Serialize(request));

            //Waits for the operation to complete.
            newSocketThread.join();

        } catch (InterruptedException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.err.println("Error while trying to establish the first connection to the directory Peer.");
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            firstConnection.shutdown();
        }

        System.out.println("Register Successfull in port: " + serverPort);

        //TODO connection into Redudancy Peer to fetch all new information in the BDs.
        //END TODO.
        
        //Starts the server socket.
        startListen();
    }

    /**
     * Starts a Server Socket Handler to accept connection in the given port by
     * the directory Peer.
     */
    public void startListen() {
        System.out.println("Initializing ServerSocket. . .");

        try {
            serverHandler = new ServerSocketHandler(new ServerSocket(serverPort), this);

            Thread serverHandlerThread = new Thread(serverHandler);

            serverHandlerThread.start();

            System.out.println("Server Socket Ready!");

        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the connection of the new socket given as parameter. The socket
     * will be wrapped in a ClientSocket object that handles the I/O streams and
     * runs in a individual thread to keep the connection alive.
     *
     * The new socket will be storage in the Peer list of this communication until
     * the I/O interaction are done.
     *
     * @param socket Socket to add.
     * @throws IOException Exception while trying to open the I/O streams.
     */
    public void handlePeer(Socket socket) throws IOException {

        //Creates the streams for I/O between sockets.
        PrintWriter socketSend = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader socketReceived = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //Creates a new socket to establish the connection between Peers.
        ClientSocket newClientSocket = new ClientSocket(socket, socketSend, socketReceived, this, CONNECTION_COUNTER);
        
        CONNECTION_COUNTER++;

        peers.add(newClientSocket);

        Thread newSocketThread = new Thread(newClientSocket);

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
     * Removes the given peer of this communication list and then shutsdown
     * the connection.
     *
     * @param peer Peer to remove
     */
    public void removePeer(ClientSocket peer) {
        peers.remove(peer);
        peer.shutdown();
    }
    
    /**
     * Sends the given request to the socket who is in the given port.
     * 
     * @param request Request to send.
     * @param port Port to connect and send the request.
     */
    public void send(Request request , int port){
        
        ClientSocket socketToSend = null;
        
        String requestSerialized = serializer.Serialize(request);
        
        try {
            socketToSend = createConnectionSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        socketToSend.send(requestSerialized);
        
        this.removePeer(socketToSend);
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
     * Sends the given request to all connected Peers registered in the
     * Directory Peer.
     * 
     * This method first call the Directory Peer to request all active peers
     * in the network and then sends the request to those Peers.
     *
     * @param request Request to send.
     */
    public void sendToAllPeers(Request request) {

        ClientSocket firstConnection = null;
        
        request.setFrom(serverPort);

        try {
            Request requestAllPeers = new Request("getactivepeers", "Send peers");
            firstConnection = createConnectionSocket(directoryPeerPort);
            Thread newSocketThread = new Thread(firstConnection, name);
            newSocketThread.start();
            firstConnection.send(serializer.Serialize(requestAllPeers));
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

        System.out.println("Sending request to all peers. . . " + activePeers.size());

        for (Peer peerToConnnect : activePeers) {

            if (peerToConnnect.getPort() != serverPort) {

                System.out.println("Connecting to: " + peerToConnnect.getPort());
                
                try {
                    ClientSocket peerClient = createConnectionSocket(peerToConnnect.getPort());

                    request.setTo(peerToConnnect.getPort());

                    Thread newSocketThread = new Thread(peerClient, name);

                    newSocketThread.start();

                    send(request, peerClient);

                    newSocketThread.join();

                    System.out.println("Request Sended.");

                } catch (IOException ex) {
                    Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        
        activePeers = null;
    }

    /**
     * Creates a ClientSocket to connect at the given port.
     *
     * @param port Port to connect.
     * @return ClientSocket connected.
     * @throws IOException Exception if thers an error while creating Socket.
     */
    public ClientSocket createConnectionSocket(int port) throws IOException {

        //Creates a new socket to connect
        Socket socket = new Socket("localhost", port);

        //Creates the streams for I/O between sockets.
        PrintWriter socketSend = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader socketReceived = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //Creates a new socket to establish the connection between Peers.
        ClientSocket newClientSocket = new ClientSocket(socket, socketSend, socketReceived, this , CONNECTION_COUNTER);

        CONNECTION_COUNTER++;
        
        peers.add(newClientSocket);

        return newClientSocket;
    }

    /**
     * Sets the list of active Peers into the network.
     * @param connectedPeers List of connected peers to set.
     */
    public void setActivePeers(ArrayList<Peer> connectedPeers) {
        this.activePeers = connectedPeers;
    }

    /**
     * Returns the communication handler of this Communication.
     * @return CommunicationHandler.
     */
    public ICommHandler getCommHandler() {
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
