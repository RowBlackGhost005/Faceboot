package com.masa.utils;

import com.masa.domain.Post;

/**
 * Interface that represents the activities of a new post notifier.
 * 
 * @author Luis Marin
 */
public interface IPostNotifier {
    
    /**
     * Adds a new observer to notify with the new post that had been made.
     * @param observer Obserer to subscribe to this notifier.
     */
    public void addPostObserver(IObserver observer);
    
    /**
     * Removes the observer of the notify list.
     * @param observer Observer to unsubscribe.
     */
    public void removePostObserver(IObserver observer);
    
    /**
     * Notifies all subscribers that a new post has been made and sends said post.
     */
    public void notifyPost(Post post);
}
