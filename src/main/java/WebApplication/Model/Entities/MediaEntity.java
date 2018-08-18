package WebApplication.Model.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "media", schema = "nutella", catalog = "")
@IdClass(MediaEntityPK.class)
public class MediaEntity {
    private long postId;
    private String link;

    @Id
    @Column(name = "PostID")
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Id
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
        MediaEntity that = (MediaEntity) o;
        return postId == that.postId &&
                Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, link);
    }
}
