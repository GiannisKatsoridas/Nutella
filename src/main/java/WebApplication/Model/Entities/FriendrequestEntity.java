package WebApplication.Model.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friendrequest", schema = "nutella", catalog = "")
@IdClass(FriendrequestEntityPK.class)
public class FriendrequestEntity {
    private long sender;
    private long receiver;

    @Id
    @Column(name = "Sender")
    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    @Id
    @Column(name = "Receiver")
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
        FriendrequestEntity that = (FriendrequestEntity) o;
        return sender == that.sender &&
                receiver == that.receiver;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver);
    }
}
