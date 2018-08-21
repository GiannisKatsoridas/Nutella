package WebApplication.Model.Responses;


import WebApplication.Model.Entities.JobsEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetMyJobsResponse {

    private List<JobsEntity> jobs;

    public List<JobsEntity> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobsEntity> jobs) {
        this.jobs = jobs;
    }

    public GetMyJobsResponse(List<JobsEntity> jobs) {
        this.jobs = jobs;
    }
}
