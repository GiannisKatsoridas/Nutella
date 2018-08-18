package WebApplication.Model.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "friends", schema = "nutella", catalog = "")
@IdClass(FriendsEntityPK.class)
public class FriendsEntity {
    private long user1;
    private long user2;

    @Id
    @Column(name = "User1")
    public long getUser1() {
        return user1;
    }

    public void setUser1(long user1) {
        this.user1 = user1;
    }

    @Id
    @Column(name = "User2")
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
        FriendsEntity that = (FriendsEntity) o;
        return user1 == that.user1 &&
                user2 == that.user2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1, user2);
    }
}
