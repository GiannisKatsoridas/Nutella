package WebApplication.Model.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "notifications", schema = "nutella")
public class NotificationsEntity {
    private long id;
    private long userFrom;
    private long userTo;
    private java.sql.Timestamp timestamp;
    private long post;
    private int category;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserFrom")
    public long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(long userFrom) {
        this.userFrom = userFrom;
    }

    @Basic
    @Column(name = "UserTo")
    public long getUserTo() {
        return userTo;
    }

    public void setUserTo(long userTo) {
        this.userTo = userTo;
    }

    @Basic
    @Column(name = "Timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Basic
    @Column(name = "Post")
    public long getPost() {
        return post;
    }

    public void setPost(long post) {
        this.post = post;
    }

    @Basic
    @Column(name = "Category")
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationsEntity that = (NotificationsEntity) o;
        return id == that.id &&
                post == that.post &&
                userFrom == that.userFrom &&
                userTo == that.userTo &&
                category == that.category &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userFrom, userTo, timestamp, post, category);
    }
}
