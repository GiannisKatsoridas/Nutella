package WebApplication.Model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class JobapplicationsEntityPK implements Serializable {
    private long applicant;
    private long job;

    @Column(name = "Applicant")
    @Id
    public long getApplicant() {
        return applicant;
    }

    public void setApplicant(long applicant) {
        this.applicant = applicant;
    }

    @Column(name = "Job")
    @Id
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
        JobapplicationsEntityPK that = (JobapplicationsEntityPK) o;
        return applicant == that.applicant &&
                job == that.job;
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicant, job);
    }
}
