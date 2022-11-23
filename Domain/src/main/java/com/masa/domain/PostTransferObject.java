package com.masa.domain;

public class PostTransferObject {

    private Post post;
    private String imagePathToCopy;

    public PostTransferObject(Post post, String imagePathToCopy) {
        this.post = post;
        this.imagePathToCopy = imagePathToCopy;
    }

    public Post getPostToSave(){
        
        post.setImagePath(imagePathToCopy);
        
        return post;
    }
    
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getImagePathToCopy() {
        return imagePathToCopy;
    }

    public void setImagePathToCopy(String imagePathToCopy) {
        this.imagePathToCopy = imagePathToCopy;
    }
}
