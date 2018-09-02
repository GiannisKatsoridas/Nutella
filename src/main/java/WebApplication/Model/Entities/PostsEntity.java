package WebApplication.Model.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "posts", schema = "nutella", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "PostsEntity.GetPostsFromFriend1",
                query = "select p from UsersEntity u join FriendsEntity f on u.id = f.user2 join PostsEntity p on p.userId = f.user1 where f.user2 = :userId"
        ),
        @NamedQuery(
                name = "PostsEntity.GetPostsFromFriend2",
                query = "select p from UsersEntity u join FriendsEntity f on u.id = f.user1 join PostsEntity p on p.userId = f.user2 where f.user1 = :userId"
        ),
        @NamedQuery(
                name = "PostsEntity.GetAllPosts",
                query = "select p from PostsEntity p"
        ),
        @NamedQuery(
                name = "PostsEntity.getDifferentPostsFromNeighborsLikes",
                query = "select p from PostsEntity p join LikesEntity l on p.id = l.postId where l.userId = :neighborId and l.timestamp > :timestamp and l.postId not in (select pa.id from PostsEntity pa join LikesEntity la on pa.id = la.postId where la.userId = :userId)"
        ),
        @NamedQuery(
                name = "PostsEntity.getDifferentPostsFromNeighborsComments",
                query = "select p from PostsEntity p join CommentsEntity c on p.id = c.postId where c.userId = :neighborId and c.timeStamp > :timestamp and c.postId not in (select pa.id from PostsEntity pa join CommentsEntity ca on pa.id = ca.postId where ca.userId = :userId)"
        )
})
public class PostsEntity {
    private long id;
    private long userId;
    private String text;
    private Date date;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "Date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostsEntity that = (PostsEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, text, date);
    }
}
