package WebApplication.Model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class JobrequirementsEntityPK implements Serializable {

    private long jobId;
    private String skill;

    @Id
    @Column(name = "JobID")
    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    @Id
    @Column(name = "Skill")
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobrequirementsEntityPK that = (JobrequirementsEntityPK) o;
        return jobId == that.jobId &&
                skill == that.skill;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, skill);
    }


}
