package WebApplication.Model.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "likes", schema = "nutella", catalog = "")
@IdClass(LikesEntityPK.class)
@NamedQueries({
        @NamedQuery(
                name = "LikesEntity.GetLikes",
                query = "select l from LikesEntity l where l.postId = :postId"
        )
})
public class LikesEntity {
    private long userId;
    private long postId;

    @Id
    @Column(name = "UserID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "PostID")
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
        LikesEntity that = (LikesEntity) o;
        return userId == that.userId &&
                postId == that.postId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, postId);
    }
}
