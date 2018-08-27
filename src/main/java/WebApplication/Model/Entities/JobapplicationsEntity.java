package WebApplication.Model.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "jobapplications", schema = "nutella", catalog = "")
@NamedQueries({
        @NamedQuery(
                name = "JobapplicationsEntity.GetJobsByUser",
                query = "select j from JobapplicationsEntity j where j.applicant = :userId"
        ),
        @NamedQuery(
                name = "JobapplicationsEntity.GetAllJobApplications",
                query = "select j from JobapplicationsEntity j"
        )
})
@IdClass(JobapplicationsEntityPK.class)
public class JobapplicationsEntity {
    private long applicant;
    private long job;

    @Id
    @Column(name = "Applicant")
    public long getApplicant() {
        return applicant;
    }

    public void setApplicant(long applicant) {
        this.applicant = applicant;
    }

    @Id
    @Column(name = "Job")
    public long getJob() {
        return job;
    }

    public void setJob(long job) {
        this.job = job;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobapplicationsEntity that = (JobapplicationsEntity) o;
        return applicant == that.applicant &&
                job == that.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicant, job);
    }
}
