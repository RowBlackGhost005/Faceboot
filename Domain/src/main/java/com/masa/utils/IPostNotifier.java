package com.masa.utils;

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
}
