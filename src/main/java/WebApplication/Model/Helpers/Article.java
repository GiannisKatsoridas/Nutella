package WebApplication.Model.Helpers;

import WebApplication.Model.Entities.CommentsEntity;
import WebApplication.Model.Entities.LikesEntity;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {

    private long postId;
    private long creator;
    private String text;
    private List<LikesEntity> likes;
    private List<CommentsEntity> comments;

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<LikesEntity> getLikes() {
        return likes;
    }

    public void setLikes(List<LikesEntity> likes) {
        this.likes = likes;
    }

    public List<CommentsEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentsEntity> comments) {
        this.comments = comments;
    }

    public Article(long postId, long creator, String text, List<LikesEntity> likes, List<CommentsEntity> comments) {
        this.postId = postId;
        this.creator = creator;
        this.text = text;
        this.likes = likes;
        this.comments = comments;
    }
}
