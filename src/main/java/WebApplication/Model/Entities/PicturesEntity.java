package WebApplication.Model.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pictures", schema = "nutella", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "PicturesEntity.getPictureFromUser",
                query = "select p from PicturesEntity p where p.userId = :userId"
        )
})
public class PicturesEntity {
    private long userId;
    private String link;

    @Id
    @Column(name = "UserID")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "Link")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PicturesEntity that = (PicturesEntity) o;
        return userId == that.userId &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, link);
    }
}
