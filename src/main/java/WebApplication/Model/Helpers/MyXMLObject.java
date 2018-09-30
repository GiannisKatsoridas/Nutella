package WebApplication.Model.Helpers;

import WebApplication.Model.Entities.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class MyXMLObject {

    private long userId;
    private List<EducationEntity> education;
    private List<ExperienceEntity> experience;
    private List<UserInfo> connections;
    private List<PostsEntity> posts;
    private List<LikesEntity> likes;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<EducationEntity> getEducation() {
        return education;
    }

    public void setEducation(List<EducationEntity> education) {
        this.education = education;
    }

    public List<ExperienceEntity> getExperience() {
        return experience;
    }

    public void setExperience(List<ExperienceEntity> experience) {
        this.experience = experience;
    }

    public List<UserInfo> getConnections() {
        return connections;
    }

    public void setConnections(List<UserInfo> connections) {
        this.connections = connections;
    }

    public List<PostsEntity> getPosts() {
        return posts;
    }

    public void setPosts(List<PostsEntity> posts) {
        this.posts = posts;
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

    public MyXMLObject(long userId, List<EducationEntity> education, List<ExperienceEntity> experience, List<UserInfo> connections, List<PostsEntity> posts, List<LikesEntity> likes, List<CommentsEntity> comments) {
        this.userId = userId;
        this.education = education;
        this.experience = experience;
        this.connections = connections;
        this.posts = posts;
        this.likes = likes;
        this.comments = comments;
    }

    public MyXMLObject(){}

    private List<CommentsEntity> comments;

}
