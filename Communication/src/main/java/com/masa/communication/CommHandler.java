package com.masa.communication;

import domain.Request;
import com.masa.businesslogic.IBusinessLogic;
import com.masa.domain.Comment;
import com.masa.domain.Post;
import com.masa.domain.PostTransferObject;
import com.masa.domain.Tag;
import com.masa.domain.User;
import com.masa.utils.ICommentNotifier;
import com.masa.utils.IObserver;
import com.masa.utils.IPostNotifier;
import domain.Peer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that controls the request received and to be send to redirect them into
 * their respective method or component.
 *
 * @author Luis Angel Marin
 */
public class CommHandler implements ICommHandler, IPostNotifier, ICommentNotifier {

    private Communication communication;

    private Serializer serializer;

    private IBusinessLogic businessLogic;

    private ArrayList<IObserver> postObservers;

    private ArrayList<IObserver> commentObservers;

    /**
     * Creates a new Communication Handler.
     *
     * @param communication Communication object to handle his operations.
     * @param businessLogic BusinessLogic to communicate the App operations.
     */
    public CommHandler(IBusinessLogic businessLogic) {
//        this.communication = communication;
        this.serializer = new Serializer();
        this.businessLogic = businessLogic;
        this.postObservers = new ArrayList<IObserver>();
        this.commentObservers = new ArrayList<IObserver>();

    }

    /**
     * Initializes the Communication component of this subsystem. Should be the
     * first method called once this object is constructed
     */
    @Override
    public void initCommunication() {
        this.communication = new Communication("Peer", this);
        communication.startUp();
    }

    /**
     * Prints the given Message in console. For Debug Purposes.
     *
     * @param message Message to print.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Handles the Request given as parameter.
     *
     * The operation parameter in the request is read and the asociated
     * methods/operation are called to handle the request.
     *
     * If neccesariy, this method uses the ClientSocket given as parameter to
     * send a response that was generated in the handle process.
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

                ArrayList<Peer> connectedPeers = (ArrayList<Peer>) request.getParam("peerlist");

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

                Post post = postTransferObject.getPostToSave();
                
                post.setUser(businessLogic.getUser(post.getUser().getId()));

                if (tag != null) {
                    try {
                        businessLogic.createPost(post, tag, false);
                    } catch (IOException ex) {
                        Logger.getLogger(CommHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        businessLogic.createPost(post, false);
                    } catch (IOException ex) {
                        Logger.getLogger(CommHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                communication.removePeer(peer);

                
                notifyPost(post);

                break;

            //Can Send: Peer
            //Can Receive: Peer.
            //Response to: N/A.
            //Register into the database the given user who is registered with an 3rd party.
            case "registerexternaluser":

                User user = (User) request.getParam("user");

                businessLogic.registerExternalUser(user, false);

                communication.removePeer(peer);

                break;
                
            //Can Send: Peer
            //Can Receive: Peer.
            //Response to: N/A.
            //Register a login of an user into faceboot.
            case "addOnlineUser":

                User onlineUser = (User) request.getParam("user");

                //businessLogic.registerExternalUser(user, false);

                communication.removePeer(peer);

                break;
                
            //Can Send: Peer
            //Can Receive: Peer.
            //Response to: N/A.
            //Register the loggoff of an user into faceboot.
            case "addOfflineUser":

                User offlineUser = (User) request.getParam("user");

                //businessLogic.registerExternalUser(user, false);

                communication.removePeer(peer);

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
    public void sendRequest(Request request, ClientSocket peer) {
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
    public void addPostObserver(IObserver postObserver) {
        postObservers.add(postObserver);
    }

    @Override
    public void removePostObserver(IObserver postObserver) {
        postObservers.remove(postObserver);
    }

    @Override
    public void notifyPost(Post post) {
        
        for (IObserver postObserver : postObservers) {
            postObserver.update(post);
        }
    }

    @Override
    public void addCommentObserver(IObserver commentObserver) {
        commentObservers.add(commentObserver);
    }

    @Override
    public void removeCommentObserver(IObserver commentObserver) {
        commentObservers.remove(commentObserver);
    }

    @Override
    public void notifyComment(Comment comment) {
        
        for(IObserver commentObserver : commentObservers){
            commentObserver.update(comment);
        }
    }
}
