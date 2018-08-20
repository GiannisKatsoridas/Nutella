package WebApplication.Model.Helpers;

import WebApplication.Model.Entities.CommentsEntity;
import WebApplication.Model.Entities.LikesEntity;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {

    private String text;
    private List<LikesEntity> likes;
    private List<CommentsEntity> comments;

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

    public Article(String text, List<LikesEntity> likes, List<CommentsEntity> comments) {
        this.text = text;
        this.likes = likes;
        this.comments = comments;
    }
}
