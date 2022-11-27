package com.masa.utils;

import com.masa.domain.Post;

/**
 * Class that gets notified every time a new post has been received throught the
 * communication component.
 * 
 * @author Luis Marin
 */
public interface IPostObserver {
    
    public void updatePost(Post post);
}
