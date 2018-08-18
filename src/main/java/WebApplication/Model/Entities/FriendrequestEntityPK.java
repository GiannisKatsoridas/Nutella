package WebApplication.Model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FriendrequestEntityPK implements Serializable {
    private long sender;
    private long receiver;

    @Column(name = "Sender")
    @Id
    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    @Column(name = "Receiver")
    @Id
    public long getReceiver() {
        return receiver;
    }

    public void setReceiver(long receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendrequestEntityPK that = (FriendrequestEntityPK) o;
        return sender == that.sender &&
                receiver == that.receiver;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver);
    }
}
