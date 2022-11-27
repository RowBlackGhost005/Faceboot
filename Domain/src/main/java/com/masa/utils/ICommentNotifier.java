package com.masa.utils;

import com.masa.domain.Comment;

/**
 *
 * @author Luis Marin
 */
public interface ICommentNotifier {
    
    /**
     * Adds a new observer to notify with the new post that had been made.
     * @param observer Obserer to subscribe to this notifier.
     */
    public void addCommentObserver(IObserver commentObserver);
    
    /**
     * Removes the observer of the notify list.
     * @param observer Observer to unsubscribe.
     */
    public void removeCommentObserver(IObserver commentObserver);
    
    /**
     * Notifies all subscribers that a new comment has been made and sends said comment.
     */
    public void notifyComment(Comment comment);
}
