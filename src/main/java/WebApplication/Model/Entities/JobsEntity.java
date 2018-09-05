package WebApplication.Model.Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "jobs", schema = "nutella", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "JobsEntity.getJobsFrom1",
                query = "select j from UsersEntity u join FriendsEntity f on u.id = f.user1 join JobsEntity j on j.creator = f.user2 where f.user1 = :userId and j.date > :date"
        ),
        @NamedQuery(
                name = "JobsEntity.getJobsFrom2",
                query = "select j from UsersEntity u join FriendsEntity f on u.id = f.user2 join JobsEntity j on j.creator = f.user1 where f.user2 = :userId and j.date > :date"
        ),
        @NamedQuery(
                name = "JobsEntity.getJobApplicants",
                query = "select u from JobsEntity j join JobapplicationsEntity a on j.id = a.job join UsersEntity u on a.applicant = u.id where j.id = :jobId"
        ),
        @NamedQuery(
                name = "JobsEntity.getJobByUser",
                query = "select j from JobsEntity j where j.creator = :userId"
        ),
        @NamedQuery(
                name = "JobsEntity.selectAllJobs",
                query = "select j from JobsEntity j"
        ),
        @NamedQuery(
                name = "JobsEntity.getJobsCount",
                query = "select count(j.id) from JobsEntity j"
        ),
        @NamedQuery(
                name = "JobsEntity.getFiveSpecificJobs",
                query = "select j from JobsEntity j where j.id = :id1 or j.id = :id2 or j.id = :id3  or j.id = :id4 or j.id = :id5"
        ),
        @NamedQuery(
                name = "JobsEntity.getDifferentJobsFromNeighbors",
                query = "select j from JobapplicationsEntity ja join JobsEntity j on j.id = ja.job where ja.applicant = :neighborId and ja.job not in (select j.job from JobapplicationsEntity j where j.applicant = :userId)"
        )
})
@IdClass(JobsEntityPK.class)
public class JobsEntity {
    private long id;
    private long creator;
    private String title;
    private String description;
    private Date date;

    @Id
    @Column(name = "Date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Creator")
    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobsEntity that = (JobsEntity) o;
        return id == that.id &&
                creator == that.creator &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creator, title, description);
    }
}
