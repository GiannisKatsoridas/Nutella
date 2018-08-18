package WebApplication.Model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CommentsEntityPK implements Serializable {
    private long userId;
    private long postId;

    @Column(name = "UserID")
    @Id
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Column(name = "PostID")
    @Id
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentsEntityPK that = (CommentsEntityPK) o;
        return userId == that.userId &&
                postId == that.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }
}
