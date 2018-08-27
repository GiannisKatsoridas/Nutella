package WebApplication.Model.Helpers;

public class JobSkillsAlike {

    private long jobId;
    private int skillsAlike;

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public int getSkillsAlike() {
        return skillsAlike;
    }

    public void setSkillsAlike(int skillsAlike) {
        this.skillsAlike = skillsAlike;
    }

    public JobSkillsAlike(long jobId, int skillsAlike) {
        this.jobId = jobId;
        this.skillsAlike = skillsAlike;
    }
}
