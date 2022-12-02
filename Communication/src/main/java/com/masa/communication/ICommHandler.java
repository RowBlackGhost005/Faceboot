package com.masa.communication;

import com.masa.domain.Request;
/**
 * Interface that define all the operations that can be done from the
 * Communication module.
 *
 * @author Luis Angel Marin
 */
public interface ICommHandler{

    public void initCommunication();
    
    /**
     * Handles the request given.
     *
     * @param request Request to handle.
     * @param peer Peer to be attended.
     */
    public void handleOperation(Request request, ClientSocket peer);

    /**
     * Sends the given request to the given Peer.
     * @param request Request to send.
     */
    public void sendRequest(Request request);
    
    /**
     * Sends the given request to the given peer.
     * @param request Request to send.
     * @param peer Peer to send the request.
     */
    public void sendRequest(Request request, ClientSocket peer);
    
    /**
     * Sends a request to all the current peers online
     * @param request Request to send to all peers online.
     */
    public void sendRequestAllPeers(Request request);
}