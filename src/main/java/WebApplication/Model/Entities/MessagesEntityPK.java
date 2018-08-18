package WebApplication.Model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class MessagesEntityPK implements Serializable {
    private long userFrom;
    private long userTo;
    private Timestamp timestamp;

    @Column(name = "UserFrom")
    @Id
    public long getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(long userFrom) {
        this.userFrom = userFrom;
    }

    @Column(name = "UserTo")
    @Id
    public long getUserTo() {
        return userTo;
    }

    public void setUserTo(long userTo) {
        this.userTo = userTo;
    }

    @Column(name = "Timestamp")
    @Id
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
        MessagesEntityPK that = (MessagesEntityPK) o;
        return userFrom == that.userFrom &&
                userTo == that.userTo &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userFrom, userTo, timestamp);
    }
}
