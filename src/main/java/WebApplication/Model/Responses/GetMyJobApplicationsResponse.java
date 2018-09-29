package WebApplication.Model.Responses;

import WebApplication.Model.Entities.JobapplicationsEntity;
import WebApplication.Model.Entities.JobsEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetMyJobApplicationsResponse {

    private List<JobapplicationsEntity> jobs;

    public List<JobapplicationsEntity> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobapplicationsEntity> jobs) {
        this.jobs = jobs;
    }

    public GetMyJobApplicationsResponse(List<JobapplicationsEntity> jobs) {
        this.jobs = jobs;
    }
}
