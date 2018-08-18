package WebApplication.Model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FriendsEntityPK implements Serializable {
    private long user1;
    private long user2;

    @Column(name = "User1")
    @Id
    public long getUser1() {
        return user1;
    }

    public void setUser1(long user1) {
        this.user1 = user1;
    }

    @Column(name = "User2")
    @Id
    public long getUser2() {
        return user2;
    }

    public void setUser2(long user2) {
        this.user2 = user2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendsEntityPK that = (FriendsEntityPK) o;
        return user1 == that.user1 &&
                user2 == that.user2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1, user2);
    }
}
