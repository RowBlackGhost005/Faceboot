package com.masa.communication;

import domain.Request;
import Logic.Directory;
import Logic.IDirectory;
import com.masa.businesslogic.BusinessLogic;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.User;
import domain.Peer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that controls the operations that were send or received into the Communication component.
 *  
 * @author Luis Angel Marin
 */
public class CommHandler implements ICommHandler{

    private IDirectory directoryPeer;
    
    private Communication communication;
    
    private Serializer serializer;
    
    private IBusinessLogic businessLogic;
    
    public CommHandler(Communication communication, IBusinessLogic businessLogic){
        this.directoryPeer = new Directory();
        this.communication = communication;
        this.serializer = new Serializer();
        this.businessLogic = businessLogic;
    }
    
    @Override
    public Peer registerPeer(){
        return directoryPeer.registerPeer();
    }
    
    @Override
    public void addPeer(Peer peer) {
        directoryPeer.addPeer(peer);
    }

    @Override
    public void removePeer(int port) {
        directoryPeer.removePeer(port);
    }

    @Override
    public Peer getPeer(int port) {
        return directoryPeer.getPeer(port);
    }

    @Override
    public List<Peer> getActivePeers() {
        return directoryPeer.getActivePeers();
    }

    @Override
    public void addPort(int port) {
        directoryPeer.addPort(port);
    }

    @Override
    public void removePort(int port) {
        directoryPeer.removePort(port);
    }
    
    public void print(String message){
        directoryPeer.print(message);
    }

    @Override
    public void handleOperation(Request request, ClientSocket peer) {
        
        switch (request.getOperation().toLowerCase()) {
            case "print":
                print(request.getMessage());
                break;
            case "greetings":
                print(request.getMessage());
                sendRequest(request, peer);
                break;
            case "netregister":
                
                Peer registeredPeer = registerPeer();
                
                print("Successfully registed Peer as: " + registeredPeer.getName()+ "in port: " + registeredPeer.getPort());
                
                Request response = new Request();
                response.setOperation("netregistersuccess");
                response.append(registeredPeer, registeredPeer.getClass().getSimpleName());
                
                sendRequest(response, peer);
                break;
                
            case "netregistersuccess":
                
                Peer peerCredentials = (Peer) request.getParam("peer");
                
                communication.configurePeer(peerCredentials);
                
                break;
                
            case "getactivepeers":
                
                List<Peer> peers = getActivePeers();
                
                response = new Request();
                
                response.append(peers, "peerlist");
                
                System.out.println("Requesting Active Peers to");
                sendRequest(response, peer);
                break;
                
            case "registeruser":
                
                User userToAdd = (User) request.getParam("User");
                
                businessLogic.registerUser(userToAdd);
                
                communication.removePeer(peer);
                break;
                
            case "registeractivepeers":
                
                ArrayList<Peer> connectedPeers = (ArrayList<Peer>)request.getParam("peerlist");
                
                communication.setActivePeers(connectedPeers);
                
                System.out.println("Active peers registered");
                
                communication.removePeer(peer);
                break;
            default:
                break;

        }

    }
    
    @Override
    public void sendRequest(Request request, ClientSocket peer){
        communication.send(request, peer);
    }

    @Override
    public void sendRequest(Request request) {
        
    }

    @Override
    public void sendRequestAllPeers(Request request) {
        
    }
}
