package com.masa.utils;

import com.masa.domain.Comment;

/**
 *
 * @author luisg
 */
public interface ICommentObserver {
    
    public void updateComment(Comment comment);
}
