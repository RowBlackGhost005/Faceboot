package com.masa.businesslogic;

import com.masa.communication.ICommHandler;
import domain.Request;

/**
 * Class thats runs a thread to send a request into the communication component.
 * 
 * @author Luis Marin
 */
public class CommunicationThread implements Runnable{

    private ICommHandler communication;
    private Request request;
    
    /**
     * Creates a new CommunicationThread with the given request and communication handler.
     * Once this thread is run, it will send the given request with the given communication handler.
     * The request will be send to all connected peers in the network.
     * @param request Request to send to all peers.
     * @param communication Communication to send the given request.
     */
    public CommunicationThread(Request request , ICommHandler communication){
        this.communication = communication;
        this.request = request;
    }
    
    /**
     * Sends the request using a new thread.
     */
    @Override
    public void run() {
        
        communication.sendRequestAllPeers(request);
    }
    
}
