package com.masa.communication;

import domain.Request;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.Post;
import com.masa.domain.PostTransferObject;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.utils.IObservable;
import com.masa.utils.IObserver;
import domain.Peer;
import java.util.ArrayList;

/**
 * Class that controls the request received and to be send to redirect them into
 * their respective method or component.
 *  
 * @author Luis Angel Marin
 */
public class CommHandler implements ICommHandler, IObservable{

    private Communication communication;
    
    private Serializer serializer;
    
    private IBusinessLogic businessLogic;
    
    private ArrayList<IObserver> observers;
    
    /**
     * Creates a new Communication Handler.
     * 
     * @param communication Communication object to handle his operations.
     * @param businessLogic BusinessLogic to communicate the App operations.
     */
    public CommHandler(IBusinessLogic businessLogic){
//        this.communication = communication;
        this.serializer = new Serializer();
        this.businessLogic = businessLogic;
        this.observers = new ArrayList<IObserver>();
    }

    /**
     * Initializes the Communication component of this subsystem.
     * Should be the first method called once this object is constructed
     */
    @Override
    public void initCommunication(){
        this.communication = new Communication("Peer", this);
        communication.startUp();
    }
    
    /**
     * Prints the given Message in console.
     * For Debug Purposes.
     * @param message Message to print.
     */
    public void print(String message){
        System.out.println(message);
    }

    /**
     * Handles the Request given as parameter.
     * 
     * The operation parameter in the request is read and the asociated methods/operation
     * are called to handle the request.
     * 
     * If neccesariy, this method uses the ClientSocket given as parameter to send
     * a response that was generated in the handle process.
     * 
     * If no response is needed, every operation should remove the ClientSocket
     * of this communication component to end the Connection.
     * 
     * @param request Request to handle.
     * @param peer Peer who sended the request.
     */
    @Override
    public void handleOperation(Request request, ClientSocket peer) {
        
        switch (request.getOperation().toLowerCase()) {
            
            //Can Send: Peer , Directory Peer.
            //Can Receive: Peer, Directory Peer.
            //Response to: N/A.
            //For Debug Purposes, prints the message of the request in console.
            case "print":
                print(request.getMessage());
                break;
            
            //Can Send: Peer , Directory Peer.
            //Can Receive: Peer, Directory Peer.
            //Response to: N/A.
            //For Debug Purposes, used to test if a connection is established between two peers.
            case "greetings":
                print(request.getMessage());
                sendRequest(request, peer);
                break;
                
            //Can Send: Directory Peer.
            //Can Receive: Peer.
            //Response to: Netregister.
            //Register the Credentials given by the Directory Peer.
            case "netregistersuccess":
                
                Peer peerCredentials = (Peer) request.getParam("peer");
                
                communication.configurePeer(peerCredentials);
                
                break;

            //Can Send: Peer.
            //Can Receive: Peer.
            //Response to: N/A
            //Pass the information to BusinessLogic to replicate the register of a new User in this Peer DB.
            case "registeruser":
                
                User userToAdd = (User) request.getParam("user");
                
                businessLogic.registerUser(userToAdd, false);
                
                communication.removePeer(peer);
                break;
                
            //Can Send: Directory Peer.
            //Can Receive: Peer.
            //Response to: GetActivePeers.
            //Register in this Communication component the list of all Peers Connected into the network.
            case "registeractivepeers":
                
                System.out.println(request);
                
                ArrayList<Peer> connectedPeers = (ArrayList<Peer>)request.getParam("peerlist");
                
                communication.setActivePeers(connectedPeers);
                
                System.out.println("Active peers registered");
                
                communication.removePeer(peer);
                break;
                
            //Can Send: Peer
            //Can Receive: Peer.
            //Response to: N/A.
            //Register into the database the given post inside the request.
            case "registerpost":
                
                System.out.println(request);
                
                PostTransferObject postTransferObject = (PostTransferObject) request.getParam("post");
                
                Tag tag = (Tag) request.getParam("tag");
                
                Post post = postTransferObject.revertPost();
                
                if(tag != null){
                    businessLogic.createPost(post, tag, false);
                }else{
                    businessLogic.createPost(post, false);
                }
                
                
                communication.removePeer(peer);
                
                
                notify(post , "post");
                
                break;
                
            //Can Send: 
            //Can Receive: 
            //Response to: 
            //
            default:
                break;

        }

    }
    
    /**
     * Sends a request to the given ClientSocket.
     * 
     * @param request Request to send.
     * @param peer ClientSocket to send the request.
     */
    @Override
    public void sendRequest(Request request, ClientSocket peer){
        communication.send(request, peer);
    }

    @Override
    public void sendRequest(Request request) {
        
    }

    @Override
    public void sendRequestAllPeers(Request request) {
        communication.sendToAllPeers(request);
    }

    @Override
    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notify(Object o, String s) {
        
        for(IObserver observer : observers){
            observer.update(o , s);
        }
    }
}
