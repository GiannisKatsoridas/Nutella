package WebApplication.Model.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "messages", schema = "nutella", catalog = "")
@IdClass(MessagesEntityPK.class)
public class MessagesEntity {
    private long userFrom;
    private long userTo;
    private String content;
    private Timestamp timestamp;

    @Id
    @Column(name = "UserFrom")
    public long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(long userFrom) {
        this.userFrom = userFrom;
    }

    @Id
    @Column(name = "UserTo")
    public long getUserTo() {
        return userTo;
    }

    public void setUserTo(long userTo) {
        this.userTo = userTo;
    }

    @Basic
    @Column(name = "Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Id
    @Column(name = "Timestamp")
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesEntity that = (MessagesEntity) o;
        return userFrom == that.userFrom &&
                userTo == that.userTo &&
                Objects.equals(content, that.content) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userFrom, userTo, content, timestamp);
    }
}
